package com.cagst.bom.permission;

import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link Permission}.
 *
 * @author Craig Gaskill
 */
/* package */ class PermissionMapper implements Function<Row, Permission> {
    @Override
    public Permission apply(Row row) {
        var builder = new Permission.Builder()
            .permissionId(row.get("permission_id", Long.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
