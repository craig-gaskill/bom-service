package com.cagst.bom.tenant;

import java.time.OffsetDateTime;
import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
import com.cagst.bom.util.CommonStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An R2DBC implementation of {@link TenantRepository}.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class TenantRepositoryR2dbc extends BaseRepositoryR2dbc implements TenantRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(TenantRepositoryR2dbc.class);

    private final TenantMapper TENANT_MAPPER = new TenantMapper();

    private final DatabaseClient databaseClient;

    private final String baseSelect =
        "SELECT tenant_id" +
        "      ,name" +
        "      ,mnemonic" +
        "      ,locale_language" +
        "      ,locale_country" +
        "      ,subscription_type" +
        "      ,subscription_start_dt" +
        "      ,subscription_end_dt" +
        "      ,active_ind" +
        "      ,updated_cnt" +
        "  FROM tenant";

    @Autowired
    public TenantRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<Tenant> findById(@NonNull SecurityInfo securityInfo, int tenantId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var sql = baseSelect + " WHERE tenant_id = :tenant_id";

        return databaseClient.sql(sql)
            .bind("tenant_id", tenantId)
            .map(TENANT_MAPPER)
            .first();
    }

    @Override
    public Flux<Tenant> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Integer> ids) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(ids, "Argument [ids] cannot be null or empty");

        var sql = baseSelect + " WHERE tenant_id IN (:ids) ORDER BY name";

        return databaseClient.sql(sql)
            .bind("ids", ids)
            .map(TENANT_MAPPER)
            .all();
    }

    @Override
    public Mono<Tenant> findByMnemonic(@NonNull SecurityInfo securityInfo, @NonNull String mnemonic) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(mnemonic, "Argument [mnemonic] cannot be null");

        var sql = baseSelect + " WHERE mnemonic = :mnemonic AND active_ind = true";

        return databaseClient.sql(sql)
            .bind("mnemonic", mnemonic)
            .map(TENANT_MAPPER)
            .first();
    }

    @Override
    public Flux<Tenant> findByMnemonics(@NonNull SecurityInfo securityInfo, @NonNull Collection<String> mnemonics) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(mnemonics, "Argument [mnemonics] cannot be null or empty");

        var sql = baseSelect +
            " WHERE mnemonic IN (:mnemonics) " +
            "   AND active_ind = true" +
            " ORDER BY name";

        return databaseClient.sql(sql)
            .bind("mnemonics", mnemonics)
            .map(TENANT_MAPPER)
            .all();
    }

    @Override
    public Flux<Tenant> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var query = baseSelect;
        var parameters = new SqlParameterMap();
        var hasWhere = false;

        if (searchCriteria != null && StringUtils.isNotEmpty(searchCriteria.searchText())) {
            query += " WHERE ";
            hasWhere = true;
        }

        query += appendSearchText(searchCriteria, "name_key", parameters, null);

        if (searchCriteria == null || !searchCriteria.includeInactive()) {
            if (hasWhere) {
                query += " AND active_ind = true";
            } else {
                query += " WHERE active_ind = true";
                hasWhere = true;
            }
        }

        query += " ORDER BY name";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.trace("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(TENANT_MAPPER).all();
    }

    @Override
    public Mono<Tenant> insertTenant(@NonNull SecurityInfo securityInfo, @NonNull Tenant tenant) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(tenant, "Argument [tenant] cannot be null");

        if (!tenant.active()) {
            // if the tenant isn't active,
            // there is no point in persisting it
            return Mono.just(tenant);
        }

        var INSERT_TENANT =
            "INSERT INTO tenant (" +
            "  name" +
            " ,name_key" +
            " ,mnemonic" +
            " ,locale_language" +
            " ,locale_country" +
            " ,subscription_type" +
            " ,subscription_start_dt" +
            " ,subscription_end_dt" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") VALUES (" +
            "  :name" +
            " ,:name_key" +
            " ,:mnemonic" +
            " ,:locale_language" +
            " ,:locale_country" +
            " ,:subscription_type" +
            " ,:subscription_start_dt" +
            " ,:subscription_end_dt" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            ") RETURNING tenant_id";

        var spec = databaseClient.sql(INSERT_TENANT);

        spec = new SqlParameterMap()
            .addValue("name", tenant.name())
            .addValue("name_key", CommonStringUtils.normalizeToKey(tenant.name()))
            .addValue("mnemonic", tenant.mnemonic())
            .addValue("locale_language", tenant.locale() != null ? StringUtils.defaultIfEmpty(tenant.locale().getLanguage(), "en") : "en")
            .addValue("locale_country", tenant.locale() != null ? StringUtils.defaultIfEmpty(tenant.locale().getCountry(), "US") : "US")
            .addValue("subscription_type", tenant.subscriptionType().ordinal())
            .addValue("subscription_start_dt", tenant.subscriptionStartDate(), OffsetDateTime.class)
            .addValue("subscription_end_dt", tenant.subscriptionEndDate(), OffsetDateTime.class)
            .addValue("active_ind", tenant.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch()
            .one()
            .map(result -> (Integer)result.get("tenant_id"))
            .map(tenantId -> new Tenant.Builder()
                .from(tenant)
                .tenantId(tenantId)
                .build()
            );
    }

    @Override
    public Mono<Tenant> updateTenant(@NonNull SecurityInfo securityInfo, @NonNull Tenant tenant) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(tenant, "Argument [tenant] cannot be null");

        var UPDATE_TENANT =
            "UPDATE tenant" +
            "   SET name = :name" +
            "      ,name_key = :name_key" +
            "      ,mnemonic = :mnemonic" +
            "      ,locale_language = :locale_language" +
            "      ,locale_country = :locale_country" +
            "      ,subscription_type = :subscription_type" +
            "      ,subscription_start_dt = :subscription_start_dt" +
            "      ,subscription_end_dt = :subscription_end_dt" +
            "      ,active_ind = :active_ind" +
            "      ,updated_id = :updated_id" +
            "      ,updated_source = :updated_source" +
            " WHERE tenant_id = :tenant_id AND updated_cnt = :updated_cnt";

        var spec = databaseClient.sql(UPDATE_TENANT);

        spec = new SqlParameterMap()
            .addValue("name", tenant.name())
            .addValue("name_key", CommonStringUtils.normalizeToKey(tenant.name()))
            .addValue("mnemonic", tenant.mnemonic())
            .addValue("locale_language", tenant.locale() != null ? StringUtils.defaultIfEmpty(tenant.locale().getLanguage(), "en") : "en")
            .addValue("locale_country", tenant.locale() != null ? StringUtils.defaultIfEmpty(tenant.locale().getCountry(), "US") : "US")
            .addValue("subscription_start_dt", tenant.subscriptionStartDate())
            .addValue("subscription_end_dt", tenant.subscriptionEndDate())
            .addValue("active_ind", tenant.active())
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .addValue("tenant_id", tenant.tenantId())
            .addValue("updated_cnt", tenant.updatedCount())
            .bind(spec);

        return spec.then()
            .map(__ -> new Tenant.Builder()
                .from(tenant)
                .updatedCount(tenant.updatedCount() + 1)
                .build()
            );
    }
}
