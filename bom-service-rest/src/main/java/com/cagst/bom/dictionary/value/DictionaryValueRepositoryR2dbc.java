package com.cagst.bom.dictionary.value;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
import com.cagst.bom.util.CommonStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

/**
 * An R2DBC implementation of {@link DictionaryValueRepository}.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class DictionaryValueRepositoryR2dbc extends BaseRepositoryR2dbc implements DictionaryValueRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryValueRepositoryR2dbc.class);

    private final DatabaseClient databaseClient;
    private final DictionaryValueMapper DICTIONARY_VALUE_MAPPER = new DictionaryValueMapper();

    private final String baseQuery =
        "SELECT dv.dictionary_value_id" +
        "      ,dv.display" +
        "      ,dv.meaning" +
        "      ,dv.description" +
        "      ,dv.viewable_ind" +
        "      ,dv.editable_ind" +
        "      ,dv.deletable_ind" +
        "      ,dv.sort_order" +
        "      ,dv.active_ind" +
        "      ,dv.updated_cnt" +
        "  FROM dictionary_value dv" +
        " WHERE dv.tenant_id = :tenant_id" +
        "   AND dv.dictionary_id = :dictionary_id";

    private final String baseJoinQuery =
        "SELECT dv.dictionary_value_id" +
        "      ,dv.display" +
        "      ,dv.meaning" +
        "      ,dv.description" +
        "      ,dv.viewable_ind" +
        "      ,dv.editable_ind" +
        "      ,dv.deletable_ind" +
        "      ,dv.sort_order" +
        "      ,dv.active_ind" +
        "      ,dv.updated_cnt" +
        "  FROM dictionary_value dv" +
        " INNER JOIN dictionary d ON (d.dictionary_id = dv.dictionary_id AND d.meaning = :dictionary_meaning)" +
        " WHERE dv.tenant_id = :tenant_id";

    @Autowired
    public DictionaryValueRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<DictionaryValue> findById(@NonNull SecurityInfo securityInfo,
                                          long dictionaryId,
                                          long dictionaryValueId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var query = baseQuery + " AND dv.dictionary_value_id = :dictionary_value_id";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_id", dictionaryId)
            .bind("dictionary_value_id", dictionaryValueId)
            .map(DICTIONARY_VALUE_MAPPER)
            .one();
    }

    @Override
    public Mono<DictionaryValue> findById(@NonNull SecurityInfo securityInfo,
                                          @NonNull String dictionaryMeaning,
                                          long dictionaryValueId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        var query = baseJoinQuery + " AND dv.dictionary_value_id = :dictionary_value_id";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_meaning", dictionaryMeaning)
            .bind("dictionary_value_id", dictionaryValueId)
            .map(DICTIONARY_VALUE_MAPPER)
            .one();
    }

    @Override
    public Mono<DictionaryValue> findByMeaning(@NonNull SecurityInfo securityInfo,
                                               long dictionaryId,
                                               @NonNull String valueMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(valueMeaning, "Argument [valueMeaning] cannot be null or empty");

        var query = baseQuery + " AND dv.meaning = :value_meaning";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_id", dictionaryId)
            .bind("value_meaning", valueMeaning)
            .map(DICTIONARY_VALUE_MAPPER)
            .one();
    }

    @Override
    public Mono<DictionaryValue> findByMeaning(@NonNull SecurityInfo securityInfo,
                                               @NonNull String dictionaryMeaning,
                                               @NonNull String valueMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.hasText(valueMeaning, "Argument [valueMeaning] cannot be null or empty");

        var query = baseJoinQuery + " AND dv.meaning = :value_meaning";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_meaning", dictionaryMeaning)
            .bind("value_meaning", valueMeaning)
            .map(DICTIONARY_VALUE_MAPPER)
            .one();
    }

    @Override
    public Flux<DictionaryValue> findByCriteria(@NonNull SecurityInfo securityInfo,
                                                long dictionaryId,
                                                @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());
        parameters.addValue("dictionary_id", dictionaryId);

        var query = baseQuery;
        query += appendSearchText(searchCriteria, "dv.display_key", parameters, "AND");

        if (searchCriteria == null || !searchCriteria.includeInactive()) {
            query += " AND dv.active_ind = true";
        }

        query += " ORDER BY dv.sort_order NULLS LAST, dv.display";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.trace("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(DICTIONARY_VALUE_MAPPER).all();
    }

    @Override
    public Flux<DictionaryValue> findByCriteria(@NonNull SecurityInfo securityInfo,
                                                @NonNull String dictionaryMeaning,
                                                @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());
        parameters.addValue("dictionary_meaning", dictionaryMeaning);

        var query = baseJoinQuery;
        query += appendSearchText(searchCriteria, "dv.display_key", parameters, "AND");

        if (searchCriteria == null || !searchCriteria.includeInactive()) {
            query += " AND dv.active_ind = true";
        }

        query += " ORDER BY dv.sort_order NULLS LAST, dv.display";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.trace("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(DICTIONARY_VALUE_MAPPER).all();
    }

    @Override
    public Mono<DictionaryValue> insert(@NonNull SecurityInfo securityInfo,
                                        @NonNull DictionaryValue dictionaryValue,
                                        long dictionaryId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionaryValue, "Argument [dictionaryValue] cannot be null");

        if (!dictionaryValue.active()) {
            // if the value isn't active,
            // there is no point in persisting it
            return Mono.just(dictionaryValue);
        }

        var INSERT_DICTIONARY_VALUE =
            "INSERT INTO dictionary_value (" +
            "  tenant_id" +
            " ,dictionary_id" +
            " ,display" +
            " ,display_key" +
            " ,meaning" +
            " ,description" +
            " ,viewable_ind" +
            " ,editable_ind" +
            " ,deletable_ind" +
            " ,sort_order" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") VALUES (" +
            "  :tenant_id" +
            " ,:dictionary_id" +
            " ,:display" +
            " ,:display_key" +
            " ,:meaning" +
            " ,:description" +
            " ,:viewable_ind" +
            " ,:editable_ind" +
            " ,:deletable_ind" +
            " ,:sort_order" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            ") RETURNING dictionary_value_id";

        var spec = databaseClient.sql(INSERT_DICTIONARY_VALUE);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("dictionary_id", dictionaryId)
            .addValue("display", dictionaryValue.display())
            .addValue("display_key", CommonStringUtils.normalizeToKey(dictionaryValue.display()))
            .addValue("meaning", dictionaryValue.meaning())
            .addValue("description", dictionaryValue.description(), String.class)
            .addValue("viewable_ind", dictionaryValue.viewable())
            .addValue("editable_ind", dictionaryValue.editable())
            .addValue("deletable_ind", dictionaryValue.deletable())
            .addValue("sort_order", dictionaryValue.sortOrder(), Integer.class)
            .addValue("active_ind", dictionaryValue.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch().one()
            .map(result -> (Long)result.get("dictionary_value_id"))
            .map(dictionaryValueId -> new DictionaryValue.Builder()
                .from(dictionaryValue)
                .dictionaryValueId(dictionaryValueId)
                .build()
            );
    }

    @Override
    public Mono<DictionaryValue> insert(@NonNull SecurityInfo securityInfo,
                                        @NonNull DictionaryValue dictionaryValue,
                                        @NonNull String dictionaryMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionaryValue, "Argument [dictionaryValue] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        if (!dictionaryValue.active()) {
            // if the value isn't active,
            // there is no point in persisting it
            return Mono.just(dictionaryValue);
        }

        var INSERT_DICTIONARY_VALUE =
            "INSERT INTO dictionary_value (" +
            "  tenant_id" +
            " ,dictionary_id" +
            " ,display" +
            " ,display_key" +
            " ,meaning" +
            " ,description" +
            " ,viewable_ind" +
            " ,editable_ind" +
            " ,deletable_ind" +
            " ,sort_order" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") SELECT " +
            "  :tenant_id" +
            " ,d.dictionary_id" +
            " ,:display" +
            " ,:display_key" +
            " ,:meaning" +
            " ,:description" +
            " ,:viewable_ind" +
            " ,:editable_ind" +
            " ,:deletable_ind" +
            " ,:sort_order" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            "  FROM dictionary d " +
            " WHERE d.meaning = :dictionary_meaning" +
            "   AND d.tenant_id = :tenant_id" +
            " RETURNING dictionary_value_id";

        var spec = databaseClient.sql(INSERT_DICTIONARY_VALUE);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("dictionary_meaning", dictionaryMeaning)
            .addValue("display", dictionaryValue.display())
            .addValue("display_key", CommonStringUtils.normalizeToKey(dictionaryValue.display()))
            .addValue("meaning", dictionaryValue.meaning())
            .addValue("description", dictionaryValue.description(), String.class)
            .addValue("viewable_ind", dictionaryValue.viewable())
            .addValue("editable_ind", dictionaryValue.editable())
            .addValue("deletable_ind", dictionaryValue.deletable())
            .addValue("sort_order", dictionaryValue.sortOrder(), Integer.class)
            .addValue("active_ind", dictionaryValue.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch().one()
            .map(result -> (Long)result.get("dictionary_value_id"))
            .map(dictionaryValueId -> new DictionaryValue.Builder()
                .from(dictionaryValue)
                .dictionaryValueId(dictionaryValueId)
                .build()
            );
    }

    @Override
    public Mono<DictionaryValue> update(@NonNull SecurityInfo securityInfo, @NonNull DictionaryValue dictionaryValue) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionaryValue, "Argument [dictionaryValue] cannot be null");

        var UPDATE_DICTIONARY_VALUE =
            "UPDATE dictionary_value" +
            "   SET display = :display" +
            "      ,display_key = :display_key" +
            "      ,description = :description" +
            "      ,viewable_ind = :viewable_ind" +
            "      ,editable_ind = :editable_ind" +
            "      ,deletable_ind = :deletable_ind" +
            "      ,sort_order = :sort_order" +
            "      ,active_ind = :active_ind" +
            "      ,updated_id = :updated_id" +
            "      ,updated_source = :updated_source" +
            " WHERE tenant_id = :tenant_id" +
            "   AND dictionary_value_id = :dictionary_value_id" +
            "   AND updated_cnt = :updated_cnt";

        var spec = databaseClient.sql(UPDATE_DICTIONARY_VALUE);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("display", dictionaryValue.display())
            .addValue("display_key", CommonStringUtils.normalizeToKey(dictionaryValue.display()))
            .addValue("description", dictionaryValue.description(), String.class)
            .addValue("viewable_ind", dictionaryValue.viewable())
            .addValue("editable_ind", dictionaryValue.editable())
            .addValue("deletable_ind", dictionaryValue.deletable())
            .addValue("sort_order", dictionaryValue.sortOrder(), Integer.class)
            .addValue("active_ind", dictionaryValue.active())
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .addValue("dictionary_value_id", dictionaryValue.dictionaryValueId())
            .addValue("updated_cnt", dictionaryValue.updatedCount())
            .bind(spec);

        return spec.fetch().rowsUpdated()
            .map(__ -> new DictionaryValue.Builder()
                .from(dictionaryValue)
                .updatedCount(dictionaryValue.updatedCount() + 1)
                .build()
            );
    }
}
