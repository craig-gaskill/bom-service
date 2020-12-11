package com.cagst.bom.person;

import java.time.LocalDate;
import java.util.Locale;
import java.util.function.Function;

import io.r2dbc.spi.Row;
import org.apache.commons.lang3.StringUtils;

/**
 * Will map a {@link Row} into a {@link Person}.
 *
 * @author Craig Gaskill
 */
/* package */ class PersonMapper implements Function<Row, Person> {
    @Override
    public Person apply(Row row) {
        String language = row.get("locale_language", String.class);
        String country  = row.get("locale_country", String.class);

        Locale locale = null;
        if (StringUtils.isNotEmpty(language)) {
            if (StringUtils.isNotEmpty(country)) {
                locale = new Locale(language, country);
            } else {
                locale = new Locale(language);
            }
        }

        var builder = new Person.Builder()
            .personId(row.get("person_id", Long.class))
            .givenName(row.get("given_name", String.class))
            .commonName(row.get("common_name", String.class))
            .middleName(row.get("middle_name", String.class))
            .familyName(row.get("family_name", String.class))
            .prefix(row.get("prefix", String.class))
            .suffix(row.get("suffix", String.class))
            .dateOfBirth(row.get("dob_dt", LocalDate.class))
            .genderCd(row.get("gender_cd", Long.class))
            .genderIdentityCd(row.get("gender_identity_cd", Long.class))
            .maritalStatusCd(row.get("marital_status_cd", Long.class))
            .ethnicityCd(row.get("ethnicity_cd", Long.class))
            .raceCd(row.get("race_cd", Long.class))
            .active(row.get("active_ind", Boolean.class))
            .updatedCount(row.get("updated_cnt", Integer.class));

        if (locale != null) {
            builder.locale(locale);
        }

        return builder.build();
    }
}
