package com.cagst.bom.user;

import java.time.OffsetDateTime;
import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link UserEntity}.
 *
 * @author Craig Gaskill
 */
/* package */ class UserMapper implements Function<Row, UserEntity> {
    @Override
    public UserEntity apply(Row row) {
        AccountLockedType accountLockedType = null;
        Short lockedType = row.get("account_locked_type", Short.class);
        if (lockedType != null) {
            accountLockedType = AccountLockedType.values()[lockedType];
        }

        var builder = new UserEntity.Builder()
            .userId(row.get("user_id", Long.class))
            .username(row.get("username", String.class))
            .password(row.get("password", String.class))
            .temporaryPassword(row.get("temporary_password_ind", Boolean.class))
            .loginAttempts(row.get("login_attempts", Integer.class))
            .accountLockedDateTime(row.get("account_locked_dt_tm", OffsetDateTime.class))
            .accountLockedType(accountLockedType)
            .accountExpiredDateTime(row.get("account_expired_dt_tm", OffsetDateTime.class))
            .passwordChangedDateTime(row.get("password_changed_dt_tm", OffsetDateTime.class))
            .passwordExpiredDateTime(row.get("password_expired_dt_tm", OffsetDateTime.class))
            .admin(row.get("admin_ind", Boolean.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
