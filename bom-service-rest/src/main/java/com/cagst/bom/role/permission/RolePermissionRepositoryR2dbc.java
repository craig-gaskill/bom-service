package com.cagst.bom.role.permission;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

/**
 * An R2DBC implementation of the {@link RolePermissionRepository} interface.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class RolePermissionRepositoryR2dbc extends BaseRepositoryR2dbc implements RolePermissionRepository {
    private final RolePermissionMapper ROLE_PERMISSION_MAPPER = new RolePermissionMapper();

    private final DatabaseClient databaseClient;

    private final String SELECT_FOR_ROLE =
        "SELECT rp.role_permission_id" +
        "      ,p.permission_id" +
        "      ,p.code" +
        "      ,p.display" +
        "      ,p.description" +
        "      ,rp.granted_ind" +
        "      ,rp.active_ind" +
        "      ,rp.updated_cnt" +
        "  FROM permission p" +
        " INNER JOIN tenant_feature tf ON (tf.tenant_id = :tenant_id AND tf.feature_id = p.feature_id AND tf.active_ind = true)" +
        "  LEFT OUTER JOIN role_permission rp ON (rp.tenant_id = :tenant_id AND rp.permission_id = p.permission_id AND rp.active_ind = true)" +
        " WHERE rp.tenant_id = :tenant_id" +
        "   AND rp.role_id = :role_id" +
        " ORDER BY p.display";

    @Autowired
    public RolePermissionRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @NonNull
    @Override
    public Flux<RolePermission> findForRoleId(@NonNull SecurityInfo securityInfo, long roleId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());
        parameters.addValue("role_id", roleId);

        var executeSpec = databaseClient.sql(SELECT_FOR_ROLE);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(this.ROLE_PERMISSION_MAPPER).all();
    }
}
