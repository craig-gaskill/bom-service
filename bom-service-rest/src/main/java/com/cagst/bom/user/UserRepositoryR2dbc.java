package com.cagst.bom.user;

import java.time.OffsetDateTime;
import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
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
 * An R2DBC implementation of the {@link UserRepository} interface.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class UserRepositoryR2dbc extends BaseRepositoryR2dbc implements UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryR2dbc.class);

    private final UserMapper USER_MAPPER = new UserMapper();

    private final DatabaseClient databaseClient;

    private final String simpleQuery =
        "SELECT u.USER_id" +
            "      ,u.username" +
            "      ,u.password" +
            "      ,u.temporary_password_ind" +
            "      ,u.login_attempts" +
            "      ,u.account_locked_dt_tm" +
            "      ,u.account_locked_type" +
            "      ,u.account_expired_dt_tm" +
            "      ,u.password_changed_dt_tm" +
            "      ,u.password_expired_dt_tm" +
            "      ,u.admin_ind" +
            "      ,u.active_ind" +
            "      ,u.updated_cnt" +
            "  FROM users u" +
            " WHERE u.user_id IS NOT NULL";

    private final String baseQuery =
        "SELECT u.USER_id" +
        "      ,u.username" +
        "      ,u.password" +
        "      ,u.temporary_password_ind" +
        "      ,u.login_attempts" +
        "      ,u.account_locked_dt_tm" +
        "      ,u.account_locked_type" +
        "      ,u.account_expired_dt_tm" +
        "      ,u.password_changed_dt_tm" +
        "      ,u.password_expired_dt_tm" +
        "      ,u.admin_ind" +
        "      ,u.active_ind" +
        "      ,u.updated_cnt" +
        "  FROM users u" +
        " INNER JOIN users_access ua ON (ua.user_id = u.user_id" +
        "                            AND ua.active_ind = true" +
        "                            AND ua.tenant_id = :tenant_id)" +
        " INNER JOIN person p ON (p.person_id = ua.person_id" +
        "                     AND p.tenant_id = ua.tenant_id)" +
        " WHERE u.user_id IS NOT NULL";

    @Autowired
    public UserRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<UserEntity> findById(@NonNull SecurityInfo securityInfo, long userId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var sql = baseQuery + " AND u.user_id = :user_id";

        return databaseClient.sql(sql)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("user_id", userId)
            .map(USER_MAPPER)
            .first();
    }

    @Override
    public Flux<UserEntity> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> userIds) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(userIds, "Argument [userIds] cannot be null or empty");

        var sql = baseQuery + " AND u.user_id IN (:ids)";

        return databaseClient.sql(sql)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("ids", userIds)
            .map(USER_MAPPER)
            .all();
    }

    @Override
    public Mono<UserEntity> findByUsername(@NonNull String username) {
        Assert.hasText(username, "Argument [username] cannot be null");

        var sql = simpleQuery + " AND u.username = :username";

        return databaseClient.sql(sql)
            .bind("username", username.toLowerCase())
            .map(USER_MAPPER)
            .first();
    }

    @Override
    public Flux<UserEntity> findByCriteria(@NonNull SecurityInfo securityInfo,
                                           @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var query = baseQuery;

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());

        if (searchCriteria != null) {
            if (!searchCriteria.includeInactive()) {
                query += " AND u.active_ind = true";
            }

            String givenNameQuery = null;
            String familyNameQuery = null;
            String userName = null;

            if (StringUtils.contains(searchCriteria.searchText(), ',')) {
                // if there is a comma in the search text, we will assume the search text
                // is formatted as "family, given"
                String[] words = StringUtils.splitByWholeSeparator(searchCriteria.searchText(), ",");

                if (words != null) {
                    if (words.length == 1) {
                        familyNameQuery = words[0];
                        userName = words[0];
                    } else {
                        familyNameQuery = words[0];
                        givenNameQuery = words[1];
                    }
                }
            } else {
                // if there is no comma in the search text, we will assume the search text
                // is formatted as "given family"
                String[] words = StringUtils.splitByWholeSeparator(searchCriteria.searchText(), null);

                if (words != null) {
                    if (words.length == 1) {
                        givenNameQuery = words[0];
                        userName = words[0];
                    } else {
                        givenNameQuery = words[0];
                        familyNameQuery = words[1];
                    }
                }
            }

            givenNameQuery = StringUtils.trim(givenNameQuery);
            familyNameQuery = StringUtils.trim(familyNameQuery);

            if (StringUtils.isNotEmpty(givenNameQuery) && StringUtils.isEmpty(familyNameQuery)) {
                // if we only have a "given" name
                // we will use the "given" name to search both the "given" OR the "family" name
                // the user simply entered 1 word like "adam"
                query += " AND (";
                query += appendSearchText(givenNameQuery, "p.given_name_key", parameters, null);
                query += appendSearchText(givenNameQuery, "p.family_name_key", parameters, "OR");
                query += appendSearchText(userName, "u.username", parameters, "OR");
                query += ")";
            } else if (StringUtils.isEmpty(givenNameQuery) && StringUtils.isNotEmpty(familyNameQuery)) {
                // if we only have a "family" name, we will search for all people with that family name
                // the user simply entered 1 word with a comma like "adam,"
                query += appendSearchText(familyNameQuery, "p.family_name_key", parameters, "AND");
            } else if (StringUtils.isNotEmpty(givenNameQuery) && StringUtils.isNotEmpty(familyNameQuery)) {
                // if we have both the "given" name and "family" name queries
                // we will search for people with that given name AND the family name
                query += appendSearchText(givenNameQuery, "p.given_name_key", parameters, "AND");
                query += appendSearchText(familyNameQuery, "p.family_name_key", parameters, "AND");
            }
        }

        query += " ORDER BY p.family_name, p.given_name";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.debug("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(USER_MAPPER).all();
    }

    @Override
    public Mono<UserEntity> insert(@NonNull SecurityInfo securityInfo, @NonNull UserEntity user) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");

        if (!user.active()) {
            // if the user isn't active,
            // there is no point in persisting it
            return Mono.just(user);
        }

        var INSERT_USER =
            "INSERT INTO users (" +
            "  username" +
            " ,temporary_password_ind" +
            " ,login_attempts" +
            " ,account_locked_dt_tm" +
            " ,account_locked_type" +
            " ,account_expired_dt_tm" +
            " ,password" +
            " ,password_changed_dt_tm" +
            " ,password_expired_dt_tm" +
            " ,admin_ind" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") VALUES (" +
            "  :username" +
            " ,:temporary_password_ind" +
            " ,:login_attempts" +
            " ,:account_locked_dt_tm" +
            " ,:account_locked_type" +
            " ,:account_expired_dt_tm" +
            " ,:password" +
            " ,:password_changed_dt_tm" +
            " ,:password_expired_dt_tm" +
            " ,:admin_ind" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            ") RETURNING user_id";

        var spec = databaseClient.sql(INSERT_USER);
        spec = new SqlParameterMap()
            .addValue("username", user.username().toLowerCase(), String.class)
            .addValue("temporary_password_ind", user.temporaryPassword(), Boolean.class)
            .addValue("login_attempts", user.loginAttempts(), Long.class)
            .addValue("account_locked_dt_tm", user.accountLockedDateTime(), OffsetDateTime.class)
            .addValue("account_locked_type", user.accountLockedType() != null ? user.accountLockedType().ordinal() : null, Integer.class)
            .addValue("account_expired_dt_tm", user.accountExpiredDateTime(), OffsetDateTime.class)
            .addValue("password", user.password(), String.class)
            .addValue("password_changed_dt_tm", user.passwordChangedDateTime(), OffsetDateTime.class)
            .addValue("password_expired_dt_tm", user.passwordExpiredDateTime(), OffsetDateTime.class)
            .addValue("admin_ind", user.admin(), Boolean.class)
            .addValue("active_ind", user.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch().one()
            .map(result -> (Long)result.get("user_id"))
            .map(userId -> new UserEntity.Builder()
                .from(user)
                .userId(userId)
                .build()
            );
    }

    @Override
    public Mono<UserEntity> update(@NonNull SecurityInfo securityInfo, @NonNull UserEntity user) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");

        var UPDATE_USER =
            "UPDATE users" +
            "   SET username = :username" +
            "      ,temporary_password_ind = :temporary_password_ind" +
            "      ,login_attempts = :login_attempts" +
            "      ,account_locked_dt_tm = :account_locked_dt_tm" +
            "      ,account_locked_type = :account_locked_type" +
            "      ,account_expired_dt_tm = :account_expired_dt_tm" +
            "      ,password_changed_dt_tm = :password_changed_dt_tm" +
            "      ,password_expired_dt_tm = :password_expired_dt_tm" +
            "      ,admin_ind = :admin_ind" +
            "      ,active_ind = :active_ind" +
            "      ,updated_id = :updated_id" +
            "      ,updated_source = :updated_source" +
            " WHERE user_id = :user_id" +
            "   AND updated_cnt = :updated_cnt";

        var spec = databaseClient.sql(UPDATE_USER);
        spec = new SqlParameterMap()
            .addValue("username", user.username().toLowerCase(), String.class)
            .addValue("temporary_password_ind", user.temporaryPassword(), Boolean.class)
            .addValue("login_attempts", user.loginAttempts(), Long.class)
            .addValue("account_locked_dt_tm", user.accountLockedDateTime(), OffsetDateTime.class)
            .addValue("account_locked_type", user.accountLockedType() != null ? user.accountLockedType().ordinal() : null, Integer.class)
            .addValue("account_expired_dt_tm", user.accountExpiredDateTime(), OffsetDateTime.class)
            .addValue("password_changed_dt_tm", user.passwordChangedDateTime(), OffsetDateTime.class)
            .addValue("password_expired_dt_tm", user.passwordExpiredDateTime(), OffsetDateTime.class)
            .addValue("admin_ind", user.admin(), Boolean.class)
            .addValue("active_ind", user.active())
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .addValue("user_id", user.userId(), Long.class)
            .addValue("updated_cnt", user.updatedCount())
            .bind(spec);

        return spec.fetch().one()
            .map(result -> (Long)result.get("user_id"))
            .map(userId -> new UserEntity.Builder()
                .from(user)
                .userId(userId)
                .build()
            );
    }

    /*
     * These methods should only be used by the Authentication Service since they don't require
     * a PlatformSecurityInfo and are intended to used by the login workflow.
     */

    @Override
    public Mono<UserEntity> incrementAttempt(@NonNull UserEntity user) {
        Assert.notNull(user, "Argument [user] cannot be null");

        return databaseClient.sql(
            "UPDATE users" +
            "   SET login_attempts = login_attempts + 1" +
            " WHERE user_id = :user_id"
        )
            .bind("user_id", user.userId())
            .fetch()
            .one()
            .thenReturn(new UserEntity.Builder()
                .from(user)
                .loginAttempts(user.loginAttempts() + 1)
                .build()
            );
    }

    @Override
    public Mono<UserEntity> resetAttempts(@NonNull UserEntity user) {
        Assert.notNull(user, "Argument [user] cannot be null");

        return databaseClient.sql(
            "UPDATE users" +
            "   SET login_attempts = 0" +
            " WHERE user_id = :user_id"
        )
            .bind("user_id", user.userId())
            .fetch()
            .one()
            .thenReturn(new UserEntity.Builder()
                .from(user)
                .loginAttempts(user.loginAttempts() + 1)
                .build()
            );
    }

    @Override
    public Mono<UserEntity> lockUser(@NonNull UserEntity user) {
        Assert.notNull(user, "Argument [user] cannot be null");

        return databaseClient.sql(
            "UPDATE users" +
            "   SET account_locked_dt_tm = current_timestamp" +
            "      ,account_locked_type = :account_locked_type" +
            "      ,updated_id = 1" +
            "      ,updated_source = 'SYSTEM'" +
            " WHERE user_id = :user_id"
        )
            .bind("account_locked_type", AccountLockedType.Automatic.ordinal())
            .bind("user_id", user.userId())
            .fetch()
            .one()
            .thenReturn(new UserEntity.Builder()
                .from(user)
                .accountLockedType(AccountLockedType.Automatic)
                .accountLockedDateTime(OffsetDateTime.now())
                .updatedId(1L)
                .build()
            );
    }

    @Override
    public Mono<UserEntity> unlockUser(@NonNull UserEntity user) {
        Assert.notNull(user, "Argument [user] cannot be null");

        return databaseClient.sql(
            "UPDATE users" +
            "   SET account_locked_dt_tm = null" +
            "      ,account_locked_type = null" +
            "      ,updated_id = 1" +
            "      ,updated_source = 'SYSTEM'" +
            " WHERE user_id = :user_id"
        )
            .bind("user_id", user.userId())
            .fetch()
            .one()
            .thenReturn(new UserEntity.Builder()
                .from(user)
                .accountLockedType(null)
                .accountLockedDateTime(null)
                .updatedId(1L)
                .build()
            );
    }

    @Override
    public Mono<UserEntity> expirePassword(@NonNull UserEntity user) {
        Assert.notNull(user, "Argument [user] cannot be null");

        return databaseClient.sql(
            "UPDATE users" +
            "   SET password_expired_dt_tm = current_timestamp" +
            "      ,updated_id = 1" +
            "      ,updated_source = 'SYSTEM'" +
            " WHERE user_id = :user_id"
        )
            .bind("user_id", user.userId())
            .fetch()
            .one()
            .thenReturn(new UserEntity.Builder()
                .from(user)
                .passwordExpiredDateTime(OffsetDateTime.now())
                .updatedId(1L)
                .build()
            );
    }

    @Override
    public Mono<UserEntity> changePassword(@NonNull UserEntity user, @NonNull String encodedPassword) {
        Assert.notNull(user, "Argument [user] cannot be null");
        Assert.hasText(encodedPassword, "Argument [encodedPassword] cannot be null");

        return databaseClient.sql(
            "UPDATE users" +
            "   SET password_expired_dt_tm = null" +
            "      ,password = :password " +
            "      ,password_changed_dt_tm = current_timestamp" +
            "      ,updated_id = :user_id" +
            "      ,updated_source = 'SYSTEM'" +
            " WHERE user_id = :user_id"
        )
            .bind("user_id", user.userId())
            .bind("password", encodedPassword)
            .fetch()
            .one()
            .thenReturn(new UserEntity.Builder()
                .from(user)
                .passwordExpiredDateTime(null)
                .passwordChangedDateTime(OffsetDateTime.now())
                .updatedId(user.userId())
                .build()
            );
    }
}
