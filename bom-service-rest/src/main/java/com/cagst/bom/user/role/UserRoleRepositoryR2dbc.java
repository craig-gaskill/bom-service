package com.cagst.bom.user.role;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
/* package */ class UserRoleRepositoryR2dbc extends BaseRepositoryR2dbc implements UserRoleRepository {
    private final DatabaseClient databaseClient;
    private final UserRoleMapper USER_ROLE_MAPPER = new UserRoleMapper();

    private final String baseQuery =
        "SELECT ur.user_role_id" +
        "      ,ur.user_id" +
        "      ,ur.person_id" +
        "      ,ur.active_ind" +
        "      ,ur.updated_cnt" +
        "  FROM users_role ur" +
        " WHERE ur.active_ind = true" +
        "   AND ur.tenant_id = :tenant_id";

    @Autowired
    public UserRoleRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Flux<UserRole> findForUser(@NonNull SecurityInfo securityInfo, long userId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null or empty.");

        var query = baseQuery + " AND ur.user_id = :user_id";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("user_id", userId)
            .map(USER_ROLE_MAPPER)
            .all();
    }

    @Override
    public Mono<UserRole> merge(@NonNull SecurityInfo securityInfo, @NonNull UserRole userRole) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null or empty.");
        Assert.notNull(userRole, "Argument [userRole] cannot be null or empty.");

        var query =
            "INSERT INTO users_role (" +
            "  user_id" +
            " ,tenant_id" +
            " ,role_id" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") VALUES (" +
            "  :user_id" +
            " ,:tenant_id" +
            " ,:role_id" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            ") ON CONFLICT (tenant_id, user_id, role_id) DO UPDATE SET" +
            "  active_ind      = :active_ind" +
            " ,updated_id      = :updated_id" +
            " ,updated_source  = :updated_source" +
            " RETURNING user_role_id";

        var params = new SqlParameterMap()
            .addValue("user_id", userRole.userId(), Long.class)
            .addValue("tenant_id", securityInfo.tenantId(), Long.class)
            .addValue("role_id", userRole.roleId(), Long.class)
            .addValue("active_ind", userRole.active(), Boolean.class)
            .addValue("created_id", securityInfo.userId(), Long.class)
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId(), Long.class)
            .addValue("updated_source", securityInfo.source(), String.class);

        return params.bind(databaseClient.sql(query))
            .fetch()
            .one()
            .map(result -> new UserRole.Builder()
                .from(userRole)
                .userRoleId((Long)result.get("user_role_id"))
                .build()
            );
    }
}
