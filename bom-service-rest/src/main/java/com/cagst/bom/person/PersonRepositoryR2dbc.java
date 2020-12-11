package com.cagst.bom.person;

import java.time.LocalDate;
import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
import com.cagst.bom.util.CommonStringUtils;
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
 * An R2DBC implementation of the {@link PersonRepository} class.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class PersonRepositoryR2dbc extends BaseRepositoryR2dbc implements PersonRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryR2dbc.class);

    private final PersonMapper PERSON_MAPPER = new PersonMapper();

    private final DatabaseClient databaseClient;

    private final String baseSelectQuery =
        "SELECT p.person_id" +
        "      ,p.given_name" +
        "      ,p.common_name" +
        "      ,p.middle_name" +
        "      ,p.family_name" +
        "      ,p.prefix" +
        "      ,p.suffix" +
        "      ,p.dob_dt" +
        "      ,p.gender_cd" +
        "      ,p.gender_identity_cd" +
        "      ,p.marital_status_cd" +
        "      ,p.ethnicity_cd" +
        "      ,p.race_cd" +
        "      ,p.locale_language" +
        "      ,p.locale_country" +
        "      ,p.active_ind" +
        "      ,p.updated_cnt" +
        "  FROM person p" +
        " WHERE p.tenant_id = :tenant_id";

    @Autowired
    public PersonRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @NonNull
    @Override
    public Mono<Person> findById(@NonNull SecurityInfo securityInfo, long personId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var sql = baseSelectQuery + " AND person_id = :person_id";

        return databaseClient.sql(sql)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("person_id", personId)
            .map(PERSON_MAPPER)
            .first();
    }

    @NonNull
    @Override
    public Flux<Person> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(ids, "Argument [ids] cannot be null");

        var sql = baseSelectQuery + " AND person_id IN (:person_ids)";

        return databaseClient.sql(sql)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("person_ids", ids)
            .map(PERSON_MAPPER)
            .all();
    }

    @NonNull
    @Override
    public Flux<Person> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var query = baseSelectQuery;

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());

        if (searchCriteria != null) {
            if (!searchCriteria.includeInactive()) {
                query += " AND p.active_ind = true";
            }

            String givenNameQuery = null;
            String familyNameQuery = null;

            if (StringUtils.contains(searchCriteria.searchText(), ',')) {
                // if there is a comma in the search text, we will assume the search text
                // is formatted as "family, given"
                String[] words = StringUtils.splitByWholeSeparator(searchCriteria.searchText(), ",");

                if (words != null) {
                    if (words.length == 1) {
                        familyNameQuery = words[0];
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

        return executeSpec.map(PERSON_MAPPER).all();
    }

    @NonNull
    @Override
    public Mono<Person> insert(@NonNull SecurityInfo securityInfo, @NonNull Person person) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(person, "Argument [person] cannot be null");

        if (!person.active()) {
            // if the person isn't active,
            // there is no point in persisting it
            return Mono.just(person);
        }

        var INSERT_PERSON =
            "INSERT INTO person (" +
            "  tenant_id" +
            " ,given_name" +
            " ,given_name_key" +
            " ,common_name" +
            " ,middle_name" +
            " ,family_name" +
            " ,family_name_key" +
            " ,prefix" +
            " ,suffix " +
            " ,dob_dt" +
            " ,gender_cd" +
            " ,gender_identity_cd" +
            " ,marital_status_cd" +
            " ,ethnicity_cd" +
            " ,race_cd " +
            " ,locale_language" +
            " ,locale_country" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") VALUES (" +
            "  :tenant_id" +
            " ,:given_name" +
            " ,:given_name_key" +
            " ,:common_name" +
            " ,:middle_name" +
            " ,:family_name" +
            " ,:family_name_key" +
            " ,:prefix" +
            " ,:suffix " +
            " ,:dob_dt" +
            " ,:gender_cd" +
            " ,:gender_identity_cd" +
            " ,:marital_status_cd" +
            " ,:ethnicity_cd" +
            " ,:race_cd " +
            " ,:locale_language" +
            " ,:locale_country" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            ") RETURNING person_id";

        var language = person.locale() != null ? person.locale().getLanguage() : "en";
        var country  = person.locale() != null ? person.locale().getCountry() : "US";

        var spec = databaseClient.sql(INSERT_PERSON);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("given_name", person.givenName())
            .addValue("given_name_key", CommonStringUtils.normalizeToKey(person.givenName()))
            .addValue("common_name", person.commonName(), String.class)
            .addValue("middle_name", person.middleName(), String.class)
            .addValue("family_name", person.familyName())
            .addValue("family_name_key", CommonStringUtils.normalizeToKey(person.familyName()))
            .addValue("prefix", person.prefix(), String.class)
            .addValue("suffix", person.suffix(), String.class)
            .addValue("dob_dt", person.dateOfBirth(), LocalDate.class)
            .addValue("gender_cd", person.genderCd(), Long.class)
            .addValue("gender_identity_cd", person.genderIdentityCd(), Long.class)
            .addValue("marital_status_cd", person.maritalStatusCd(), Long.class)
            .addValue("ethnicity_cd", person.ethnicityCd(), Long.class)
            .addValue("race_cd", person.raceCd(), Long.class)
            .addValue("locale_language", language)
            .addValue("locale_country", country)
            .addValue("active_ind", person.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch().one()
            .map(result -> (Long)result.get("person_id"))
            .map(personId -> new Person.Builder()
                .from(person)
                .personId(personId)
                .build()
            );
    }

    @NonNull
    @Override
    public Mono<Person> update(@NonNull SecurityInfo securityInfo, @NonNull Person person) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(person, "Argument [person] cannot be null");

        var UPDATE_PERSON =
            "UPDATE person" +
            "   SET given_name = :given_name" +
            "      ,given_name_key = :given_name_key" +
            "      ,common_name = :common_name" +
            "      ,middle_name = :middle_name" +
            "      ,family_name = :family_name" +
            "      ,family_name_key = :family_name_key" +
            "      ,prefix = :prefix" +
            "      ,suffix = :suffix" +
            "      ,dob_dt = :dob_dt" +
            "      ,gender_cd = :gender_cd" +
            "      ,gender_identity_cd = :gender_identity_cd" +
            "      ,marital_status_cd = :marital_status_cd" +
            "      ,ethnicity_cd = :ethnicity_cd" +
            "      ,race_cd = :race_cd" +
            "      ,locale_country = :locale_country" +
            "      ,locale_language = :locale_language" +
            "      ,active_ind = :active_ind" +
            "      ,updated_id = :updated_id" +
            "      ,updated_source = :updated_source" +
            " WHERE tenant_id = :tenant_id" +
            "   AND person_id = :person_id" +
            "   AND updated_cnt = :updated_cnt";

        var language = person.locale() != null ? person.locale().getLanguage() : "en";
        var country  = person.locale() != null ? person.locale().getCountry() : "US";

        var spec = databaseClient.sql(UPDATE_PERSON);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("given_name", person.givenName())
            .addValue("given_name_key", CommonStringUtils.normalizeToKey(person.givenName()))
            .addValue("common_name", person.commonName(), String.class)
            .addValue("middle_name", person.middleName(), String.class)
            .addValue("family_name", person.familyName())
            .addValue("family_name_key", CommonStringUtils.normalizeToKey(person.familyName()))
            .addValue("prefix", person.prefix(), String.class)
            .addValue("suffix", person.suffix(), String.class)
            .addValue("dob_dt", person.dateOfBirth(), LocalDate.class)
            .addValue("gender_cd", person.genderCd(), Long.class)
            .addValue("gender_identity_cd", person.genderIdentityCd(), Long.class)
            .addValue("marital_status_cd", person.maritalStatusCd(), Long.class)
            .addValue("ethnicity_cd", person.ethnicityCd(), Long.class)
            .addValue("race_cd", person.raceCd(), Long.class)
            .addValue("locale_language", language)
            .addValue("locale_country", country)
            .addValue("active_ind", person.active())
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .addValue("person_id", person.personId(), Long.class)
            .addValue("updated_cnt", person.updatedCount())
            .bind(spec);

        return spec.fetch().rowsUpdated()
            .map(__ -> new Person.Builder()
                .from(person)
                .updatedCount(person.updatedCount() + 1)
                .build()
            );
    }
}
