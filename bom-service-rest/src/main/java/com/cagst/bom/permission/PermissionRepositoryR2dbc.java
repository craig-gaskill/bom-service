package com.cagst.bom.permission;

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
 * An R2EBC implementation of the {@link PermissionRepository} interface.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class PermissionRepositoryR2dbc extends BaseRepositoryR2dbc implements PermissionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionRepositoryR2dbc.class);

    private final PermissionMapper PERMISSION_MAPPER = new PermissionMapper();

    private final DatabaseClient databaseClient;

    private final String baseSelectQuery =
        "SELECT p.permission_id" +
        "      ,p.feature_id" +
        "      ,p.display" +
        "      ,p.code" +
        "      ,p.description" +
        "      ,p.active_ind" +
        "      ,p.updated_cnt" +
        "  FROM permission p" +
        " INNER JOIN tenant_feature tf ON (tf.feature_id = p.feature_id AND tf.active_ind = true)" +
        " WHERE tf.tenant_id = :tenant_id";

    @Autowired
    public PermissionRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @NonNull
    @Override
    public Mono<Permission> findById(@NonNull SecurityInfo securityInfo, long permissionId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var sql = baseSelectQuery + " AND permission_id = :permission_id";

        return databaseClient.sql(sql)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("permission_id", permissionId)
            .map(PERMISSION_MAPPER)
            .first();
    }

    @NonNull
    @Override
    public Mono<Permission> findByCode(@NonNull SecurityInfo securityInfo, @NonNull String code) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(code, "Argument [code] cannot be null or emptuy");

        var sql = baseSelectQuery + " AND code = :code";

        return databaseClient.sql(sql)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("code", code)
            .map(PERMISSION_MAPPER)
            .first();
    }

    @NonNull
    @Override
    public Flux<Permission> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());

        var query = baseSelectQuery;
        query += appendSearchText(searchCriteria, "p.display", parameters, "AND");

        if (searchCriteria == null || !searchCriteria.includeInactive()) {
            query += " AND p.active_ind = true";
        }

        query += " ORDER BY p.display";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.trace("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(this.PERMISSION_MAPPER).all();
    }
}
