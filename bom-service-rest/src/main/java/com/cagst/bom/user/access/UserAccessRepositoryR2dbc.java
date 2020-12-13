package com.cagst.bom.user.access;

import java.time.OffsetDateTime;

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

/**
 * An R2DBC implementation of {@link UserAccessRepository}.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class UserAccessRepositoryR2dbc extends BaseRepositoryR2dbc implements UserAccessRepository {

    private final DatabaseClient databaseClient;
    private final UserAccessMapper USER_ACCESS_MAPPER = new UserAccessMapper();

    private final String baseQuery =
        "SELECT ua.user_access_id" +
        "      ,ua.user_id" +
        "      ,ua.tenant_id" +
        "      ,ua.person_id" +
        "      ,ua.default_ind" +
        "      ,ua.last_login_dt_tm" +
        "      ,ua.last_login_ip" +
        "      ,ua.active_ind" +
        "      ,ua.updated_cnt" +
        "  FROM users_access ua" +
        " WHERE ua.active_ind = true";

    @Autowired
    public UserAccessRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<UserAccess> findDefault(long userId) {
        var query = baseQuery + " AND ua.user_id = :user_id";
        query += " ORDER BY ua.default_ind DESC, ua.created_dt_tm";

        return databaseClient.sql(query)
            .bind("user_id", userId)
            .map(this.USER_ACCESS_MAPPER)
            .first();
    }

    @Override
    public Flux<UserAccess> findForTenant(long tenantId) {
        var query = baseQuery + " AND ua.tenant_id = :tenant_id";

        return databaseClient.sql(query)
            .bind("tenant_id", tenantId)
            .map(this.USER_ACCESS_MAPPER)
            .all();
    }

    @Override
    public Flux<UserAccess> findForUser(long userId) {
        var query = baseQuery + " AND ua.user_id = :user_id";

        return databaseClient.sql(query)
            .bind("user_id", userId)
            .map(USER_ACCESS_MAPPER)
            .all();
    }

    @Override
    public Mono<UserAccess> merge(@NonNull SecurityInfo securityInfo, @NonNull UserAccess userAccess) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null or empty.");

        var query =
            "INSERT INTO users_access (" +
            "  user_id" +
            " ,tenant_id" +
            " ,person_id" +
            " ,default_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") VALUES (" +
            "  :user_id" +
            " ,:tenant_id" +
            " ,:person_id" +
            " ,:default_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            ") ON CONFLICT (user_id, tenant_id) DO UPDATE SET" +
            "  default_ind      = :default_ind" +
            " ,last_login_dt_tm = :last_login_dt_tm" +
            " ,last_login_ip    = :last_login_ip" +
            " ,updated_id       = :updated_id" +
            " ,updated_source   = :updated_source" +
            " RETURNING user_access_id";

        var params = new SqlParameterMap()
            .addValue("user_id", userAccess.userId(), Long.class)
            .addValue("tenant_id", userAccess.tenantId(), Long.class)
            .addValue("person_id", userAccess.personId(), Long.class)
            .addValue("default_ind", userAccess.defaultIndicator(), Boolean.class)
            .addValue("created_id", securityInfo.userId(), Long.class)
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId(), Long.class)
            .addValue("updated_source", securityInfo.source(), String.class)
            .addValue("last_login_dt_tm", userAccess.lastLoginDateTime(), OffsetDateTime.class)
            .addValue("last_login_ip", userAccess.lastLoginIp(), String.class);

        return params.bind(databaseClient.sql(query))
            .fetch()
            .one()
            .map(__ -> userAccess);
    }
}
