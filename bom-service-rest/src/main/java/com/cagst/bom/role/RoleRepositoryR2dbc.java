package com.cagst.bom.role;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
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
 * An R2DBC implementation of the {@link RoleRepository} interface.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class RoleRepositoryR2dbc extends BaseRepositoryR2dbc implements RoleRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleRepositoryR2dbc.class);

    private final RoleMapper ROLE_MAPPER = new RoleMapper();

    private final DatabaseClient databaseClient;

    private final String baseSelectQuery =
        "SELECT r.role_id" +
        "      ,r.name" +
        "      ,r.full_access_ind" +
        "      ,r.active_ind" +
        "      ,r.updated_cnt" +
        "  FROM role r" +
        " WHERE r.tenant_id = :tenant_id";

    private final String INSERT_ROLE =
        " INSERT INTO role (" +
        "  tenant_id" +
        " ,name" +
        " ,full_access_ind" +
        " ,active_ind" +
        " ,created_id" +
        " ,created_source" +
        " ,updated_id" +
        " ,updated_source" +
        ") VALUES (" +
        "  :tenant_id" +
        " ,:name" +
        " ,:full_access_ind" +
        " ,:active_ind" +
        " ,:created_id" +
        " ,:created_source" +
        " ,:updated_id" +
        " ,:updated_source" +
        ") RETURNING role_id";

    private final String UPDATE_ROLE =
        "UPDATE role" +
        "   SET name = :name" +
        "      ,full_access_ind = :full_access_ind" +
        "      ,active_ind = :active_ind" +
        "      ,updated_id = :updated_id" +
        "      ,updated_source = :updated_source" +
        " WHERE tenant_id = :tenant_id" +
        "   AND role_id = :role_id" +
        "   AND updated_cnt = :updated_cnt";

    @Autowired
    public RoleRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @NonNull
    @Override
    public Mono<Role> findById(@NonNull SecurityInfo securityInfo, long roleId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var sql = baseSelectQuery + " AND r.role_id = :role_id";

        return databaseClient.sql(sql)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("role_id", roleId)
            .map(ROLE_MAPPER)
            .first();
    }

    @NonNull
    @Override
    public Flux<Role> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(ids, "Argument [ids] cannot be null or empty.");

        var sql = baseSelectQuery + " AND r.role_id IN (:ids)";

        return databaseClient.sql(sql)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("ids", ids)
            .map(ROLE_MAPPER)
            .all();
    }

    @NonNull
    @Override
    public Flux<Role> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());

        var query = baseSelectQuery;
        query += appendSearchText(searchCriteria, "r.name", parameters, "AND");

        if (searchCriteria == null || !searchCriteria.includeInactive()) {
            query += " AND r.active_ind = true";
        }

        query += " ORDER BY r.name";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.trace("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(this.ROLE_MAPPER).all();
    }

    @NonNull
    @Override
    public Mono<Role> insert(@NonNull SecurityInfo securityInfo, @NonNull Role role) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(role, "Argument [role] cannot be null");

        if (!role.active()) {
            // if the role isn't active,
            // there is no point in persisting it
            return Mono.just(role);
        }

        var spec = databaseClient.sql(INSERT_ROLE);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("name", role.name())
            .addValue("full_access_ind", role.fullAccess())
            .addValue("active_ind", role.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch().one()
            .map(result -> (Long)result.get("role_id"))
            .map(roleId -> new Role.Builder()
                .from(role)
                .roleId(roleId)
                .build()
            );
    }

    @NonNull
    @Override
    public Mono<Role> update(@NonNull SecurityInfo securityInfo, @NonNull Role role) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(role, "Argument [role] cannot be null");

        var spec = databaseClient.sql(UPDATE_ROLE);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("name", role.name())
            .addValue("full_access_ind", role.fullAccess())
            .addValue("active_ind", role.active())
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .addValue("role_id", role.roleId())
            .addValue("updated_cnt", role.updatedCount())
            .bind(spec);

        return spec.fetch().rowsUpdated()
            .map(__ -> new Role.Builder()
                .from(role)
                .updatedCount(role.updatedCount() + 1)
                .build()
            );
    }
}
