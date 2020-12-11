package com.cagst.bom.dictionary.attribute;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
import com.cagst.bom.util.CommonStringUtils;
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
 * An R2DBC implementation of {@link DictionaryAttributeRepository}.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class DictionaryAttributeRepositoryR2dbc extends BaseRepositoryR2dbc implements DictionaryAttributeRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryAttributeRepositoryR2dbc.class);

    private final DatabaseClient databaseClient;
    private final DictionaryAttributeMapper DICTIONARY_ATTRIBUTE_MAPPER = new DictionaryAttributeMapper();

    private final String baseQuery =
        "SELECT da.dictionary_attribute_id" +
        "      ,da.display" +
        "      ,da.meaning" +
        "      ,da.description" +
        "      ,da.attribute_type" +
        "      ,da.viewable_ind" +
        "      ,da.editable_ind" +
        "      ,da.deletable_ind" +
        "      ,da.sort_order" +
        "      ,da.active_ind" +
        "      ,da.updated_cnt" +
        "  FROM dictionary_attribute da" +
        " WHERE da.tenant_id = :tenant_id" +
        "   AND da.dictionary_id = :dictionary_id";

    private final String baseJoinQuery =
        "SELECT da.dictionary_attribute_id" +
        "      ,da.display" +
        "      ,da.meaning" +
        "      ,da.description" +
        "      ,da.attribute_type" +
        "      ,da.viewable_ind" +
        "      ,da.editable_ind" +
        "      ,da.deletable_ind" +
        "      ,da.sort_order" +
        "      ,da.active_ind" +
        "      ,da.updated_cnt" +
        "  FROM dictionary_attribute da" +
        " INNER JOIN dictionary d ON (d.dictionary_id = da.dictionary_id AND d.meaning = :dictionary_meaning)" +
        " WHERE da.tenant_id = :tenant_id";

    @Autowired
    public DictionaryAttributeRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<DictionaryAttribute> findById(@NonNull SecurityInfo securityInfo,
                                              long dictionaryId,
                                              long dictionaryAttributeId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var query = baseQuery + " AND da.dictionary_attribute_id = :dictionary_attribute_id";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_id", dictionaryId)
            .bind("dictionary_attribute_id", dictionaryAttributeId)
            .map(this.DICTIONARY_ATTRIBUTE_MAPPER)
            .one();
    }

    @Override
    public Mono<DictionaryAttribute> findById(@NonNull SecurityInfo securityInfo,
                                              @NonNull String dictionaryMeaning,
                                              long dictionaryAttributeId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        var query = baseJoinQuery + " AND da.dictionary_attribute_id = :dictionary_attribute_id";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_meaning", dictionaryMeaning)
            .bind("dictionary_attribute_id", dictionaryAttributeId)
            .map(this.DICTIONARY_ATTRIBUTE_MAPPER)
            .one();
    }

    @Override
    public Mono<DictionaryAttribute> findByMeaning(@NonNull SecurityInfo securityInfo,
                                                   long dictionaryId,
                                                   @NonNull String attributeMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(attributeMeaning, "Argument [attributeMeaning] cannot be null or empty");

        var query = baseQuery + " AND da.meaning = :attribute_meaning";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_id", dictionaryId)
            .bind("attribute_meaning", attributeMeaning)
            .map(this.DICTIONARY_ATTRIBUTE_MAPPER)
            .one();
    }

    @Override
    public Mono<DictionaryAttribute> findByMeaning(@NonNull SecurityInfo securityInfo,
                                                   @NonNull String dictionaryMeaning,
                                                   @NonNull String attributeMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.hasText(attributeMeaning, "Argument [attributeMeaning] cannot be null or empty");

        var query = baseJoinQuery + " AND da.meaning = :attribute_meaning";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_meaning", dictionaryMeaning)
            .bind("attribute_meaning", attributeMeaning)
            .map(this.DICTIONARY_ATTRIBUTE_MAPPER)
            .one();
    }

    @Override
    public Flux<DictionaryAttribute> findByCriteria(@NonNull SecurityInfo securityInfo,
                                                    long dictionaryId,
                                                    @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());
        parameters.addValue("dictionary_id", dictionaryId);

        var query = baseQuery;
        query += appendSearchText(searchCriteria, "da.display_key", parameters, "AND");

        if (searchCriteria == null || !searchCriteria.includeInactive()) {
            query += " AND da.active_ind = true";
        }

        query += " ORDER BY da.sort_order NULLS LAST, da.display";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.trace("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(this.DICTIONARY_ATTRIBUTE_MAPPER).all();
    }

    @Override
    public Flux<DictionaryAttribute> findByCriteria(@NonNull SecurityInfo securityInfo,
                                                    @NonNull String dictionaryMeaning,
                                                    @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());
        parameters.addValue("dictionary_meaning", dictionaryMeaning);

        var query = baseJoinQuery;
        query += appendSearchText(searchCriteria, "da.display_key", parameters, "AND");

        if (searchCriteria == null || !searchCriteria.includeInactive()) {
            query += " AND da.active_ind = true";
        }

        query += " ORDER BY da.display";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.trace("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(this.DICTIONARY_ATTRIBUTE_MAPPER).all();
    }

    @Override
    public Mono<DictionaryAttribute> insert(@NonNull SecurityInfo securityInfo,
                                            long dictionaryId,
                                            @NonNull DictionaryAttribute attribute
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(attribute, "Argument [attribute] cannot be null");

        if (!attribute.active()) {
            // if the attribute isn't active,
            // there is no point in persisting it
            return Mono.just(attribute);
        }

        var INSERT_DICTIONARY_ATTRIBUTE =
            "INSERT INTO dictionary_attribute (" +
            "  tenant_id" +
            " ,dictionary_id" +
            " ,display" +
            " ,display_key" +
            " ,meaning" +
            " ,description" +
            " ,attribute_type" +
            " ,viewable_ind" +
            " ,editable_ind" +
            " ,deletable_ind" +
            " ,sort_order" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") VALUES (" +
            "  :tenant_id" +
            " ,:dictionary_id" +
            " ,:display" +
            " ,:display_key" +
            " ,:meaning" +
            " ,:description" +
            " ,:attribute_type" +
            " ,:viewable_ind" +
            " ,:editable_ind" +
            " ,:deletable_ind" +
            " ,:sort_order" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            ") RETURNING dictionary_attribute_id";

        var spec = databaseClient.sql(INSERT_DICTIONARY_ATTRIBUTE);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("dictionary_id", dictionaryId)
            .addValue("display", attribute.display())
            .addValue("display_key", CommonStringUtils.normalizeToKey(attribute.display()))
            .addValue("meaning", attribute.meaning())
            .addValue("description", attribute.description(), String.class)
            .addValue("attribute_type", attribute.attributeType().ordinal())
            .addValue("viewable_ind", attribute.viewable())
            .addValue("editable_ind", attribute.editable())
            .addValue("deletable_ind", attribute.deletable())
            .addValue("sort_order", attribute.sortOrder(), Integer.class)
            .addValue("active_ind", attribute.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch()
            .one()
            .map(result -> (Long)result.get("dictionary_attribute_id"))
            .map(dictionaryAttributeId -> new DictionaryAttribute.Builder()
                .from(attribute)
                .dictionaryAttributeId(dictionaryAttributeId)
                .build()
            );
    }

    @Override
    public Mono<DictionaryAttribute> insert(@NonNull SecurityInfo securityInfo,
                                            @NonNull String dictionaryMeaning,
                                            @NonNull DictionaryAttribute attribute
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.notNull(attribute, "Argument [attribute] cannot be null");

        if (!attribute.active()) {
            // if the attribute isn't active,
            // there is no point in persisting it
            return Mono.just(attribute);
        }

        var INSERT_DICTIONARY_ATTRIBUTE =
            "INSERT INTO dictionary_attribute (" +
            "  tenant_id" +
            " ,dictionary_id" +
            " ,display" +
            " ,display_key" +
            " ,meaning" +
            " ,description" +
            " ,attribute_type" +
            " ,viewable_ind" +
            " ,editable_ind" +
            " ,deletable_ind" +
            " ,sort_order" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") SELECT " +
            "  :tenant_id" +
            " ,d.dictionary_id" +
            " ,:display" +
            " ,:display_key" +
            " ,:meaning" +
            " ,:description" +
            " ,:attribute_type" +
            " ,:viewable_ind" +
            " ,:editable_ind" +
            " ,:deletable_ind" +
            " ,:sort_order" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            " FROM dictionary d" +
            " WHERE d.meaning = :dictionary_meaning" +
            " RETURNING dictionary_attribute_id";

        var spec = databaseClient.sql(INSERT_DICTIONARY_ATTRIBUTE);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("dictionary_meaning", dictionaryMeaning)
            .addValue("display", attribute.display())
            .addValue("display_key", CommonStringUtils.normalizeToKey(attribute.display()))
            .addValue("meaning", attribute.meaning())
            .addValue("description", attribute.description(), String.class)
            .addValue("attribute_type", attribute.attributeType().ordinal())
            .addValue("viewable_ind", attribute.viewable())
            .addValue("editable_ind", attribute.editable())
            .addValue("deletable_ind", attribute.deletable())
            .addValue("sort_order", attribute.sortOrder(), Integer.class)
            .addValue("active_ind", attribute.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch()
            .one()
            .map(result -> (Long)result.get("dictionary_attribute_id"))
            .map(dictionaryAttributeId -> new DictionaryAttribute.Builder()
                .from(attribute)
                .dictionaryAttributeId(dictionaryAttributeId)
                .build()
            );
    }

    @Override
    public Mono<DictionaryAttribute> update(@NonNull SecurityInfo securityInfo,
                                            @NonNull DictionaryAttribute attribute
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(attribute, "Argument [attribute] cannot be null");

        var UPDATE_DICTIONARY =
            "UPDATE dictionary_attribute" +
            "   SET display = :display" +
            "      ,display_key = :display_key" +
            "      ,description = :description" +
            "      ,attribute_type = :attribute_type" +
            "      ,viewable_ind = :viewable_ind" +
            "      ,editable_ind = :editable_ind" +
            "      ,deletable_ind = :deletable_ind" +
            "      ,sort_order = :sort_order" +
            "      ,active_ind = :active_ind" +
            "      ,updated_id = :updated_id" +
            "      ,updated_source = :updated_source" +
            " WHERE tenant_id = :tenant_id" +
            "   AND dictionary_attribute_id = :dictionary_attribute_id" +
            "   AND updated_cnt = :updated_cnt";

        var spec = databaseClient.sql(UPDATE_DICTIONARY);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("display", attribute.display())
            .addValue("display_key", CommonStringUtils.normalizeToKey(attribute.display()))
            .addValue("description", attribute.description(), String.class)
            .addValue("attribute_type", attribute.attributeType().ordinal())
            .addValue("viewable_ind", attribute.viewable())
            .addValue("editable_ind", attribute.editable())
            .addValue("deletable_ind", attribute.deletable())
            .addValue("sort_order", attribute.sortOrder(), Integer.class)
            .addValue("active_ind", attribute.active())
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .addValue("dictionary_attribute_id", attribute.dictionaryAttributeId())
            .addValue("updated_cnt", attribute.updatedCount())
            .bind(spec);

        return spec.fetch().rowsUpdated()
            .map(__ -> new DictionaryAttribute.Builder()
                .from(attribute)
                .updatedCount(attribute.updatedCount() + 1)
                .build()
            );
    }
}
