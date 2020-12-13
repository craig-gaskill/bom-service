package com.cagst.bom.user.role;

import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link UserRole} record.
 *
 * @author Craig Gaskill
 */
/* package */ class UserRoleMapper implements Function<Row, UserRole> {
    @Override
    public UserRole apply(Row row) {
        var builder = new UserRole.Builder()
            .userRoleId(row.get("user_role_id", Long.class))
            .userId(row.get("user_id", Long.class))
            .roleId(row.get("role_id", Long.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
