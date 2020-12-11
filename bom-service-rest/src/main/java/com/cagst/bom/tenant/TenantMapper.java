package com.cagst.bom.tenant;

import java.time.LocalDate;
import java.util.Locale;
import java.util.function.Function;

import io.r2dbc.spi.Row;
import org.apache.commons.lang3.StringUtils;

/**
 * Will map a {@link Row} into a {@link Tenant}.
 *
 * @author Craig Gaskill
 */
/* package */ class TenantMapper implements Function<Row, Tenant> {
    @Override
    public Tenant apply(Row row) {
        var language = row.get("locale_language", String.class);
        var country  = row.get("locale_country", String.class);

        Locale locale = null;
        if (StringUtils.isNotEmpty(language)) {
            if (StringUtils.isNotEmpty(country)) {
                locale = new Locale(language, country);
            } else {
                locale = new Locale(language);
            }
        }

        var builder = new Tenant.Builder()
            .tenantId(row.get("tenant_id", Integer.class))
            .name(row.get("name", String.class))
            .mnemonic(row.get("mnemonic", String.class))
            .subscriptionStartDate(row.get("subscription_start_dt", LocalDate.class))
            .subscriptionEndDate(row.get("subscription_end_dt", LocalDate.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        if (locale != null) {
            builder.locale(locale);
        }

        return builder.build();
    }
}
