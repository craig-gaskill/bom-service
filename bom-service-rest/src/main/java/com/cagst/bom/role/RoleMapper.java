package com.cagst.bom.role;

import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link Role}.
 *
 * @author Craig Gaskill
 */
/* package */ class RoleMapper implements Function<Row, Role> {
    @Override
    public Role apply(Row row) {
        var builder = new Role.Builder()
            .roleId(row.get("role_id", Long.class))
            .name(row.get("name", String.class))
            .fullAccess(row.get("full_access_ind", Boolean.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
