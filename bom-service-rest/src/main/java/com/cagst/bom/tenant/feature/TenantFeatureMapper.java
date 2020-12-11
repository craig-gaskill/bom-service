package com.cagst.bom.tenant.feature;

import java.util.function.Function;

import io.r2dbc.spi.Row;

/**
 * Will map a {@link Row} into a {@link TenantFeature},
 *
 * @author Craig Gaskill
 */
/* package */ class TenantFeatureMapper implements Function<Row, TenantFeature> {
    @Override
    public TenantFeature apply(Row row) {
        var builder = new TenantFeature.Builder()
            .tenantFeatureId(row.get("tenant_feature_id", Long.class))
            .display(row.get("display", String.class))
            .meaning(row.get("meaning", String.class))
            .description(row.get("description", String.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        return builder.build();
    }
}
