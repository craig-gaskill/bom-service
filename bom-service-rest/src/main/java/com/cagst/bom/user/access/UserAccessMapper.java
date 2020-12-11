package com.cagst.bom.user.access;

import java.time.OffsetDateTime;
import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link UserAccess} record.
 *
 * @author Craig Gaskill
 */
/* package */ class UserAccessMapper implements Function<Row, UserAccess> {
    @Override
    public UserAccess apply(Row row) {
        var builder = new UserAccess.Builder()
            .userAccessId(row.get("user_access_id", Long.class))
            .userId(row.get("user_id", Long.class))
            .tenantId(row.get("tenant_id", Integer.class))
            .personId(row.get("person_id", Long.class))
            .defaultIndicator(row.get("default_ind", Boolean.class))
            .lastLoginDateTime(row.get("last_login_dt_tm", OffsetDateTime.class))
            .lastLoginIp(row.get("last_login_ip", String.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
