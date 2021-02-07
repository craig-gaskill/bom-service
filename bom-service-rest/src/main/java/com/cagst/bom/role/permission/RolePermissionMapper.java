package com.cagst.bom.role.permission;

import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link RolePermission}.
 *
 * @author Craig Gaskill
 */
/* package */ class RolePermissionMapper implements Function<Row, RolePermission> {
    @Override
    public RolePermission apply(Row row) {
        var builder = new RolePermission.Builder()
            .rolePermissionId(row.get("role_permission_id", Long.class))
            .permissionId(row.get("permission_id", Long.class))
            .featureId(row.get("feature_id", Long.class))
            .code(row.get("code", String.class))
            .display(row.get("display", String.class))
            .description(row.get("description", String.class))
            .granted(row.get("granted_ind", Boolean.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
