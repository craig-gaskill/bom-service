package com.cagst.bom.dictionary;

import java.util.Collection;

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
 * An R2DBC implementation of {@link DictionaryRepository}.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class DictionaryRepositoryR2dbc extends BaseRepositoryR2dbc implements DictionaryRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryRepositoryR2dbc.class);

    private final DatabaseClient databaseClient;
    private final DictionaryMapper DICTIONARY_MAPPER = new DictionaryMapper();

    private final String baseSelectQuery =
        "SELECT d.dictionary_id" +
        "      ,d.display" +
        "      ,d.meaning" +
        "      ,d.description" +
        "      ,d.viewable_ind" +
        "      ,d.editable_ind" +
        "      ,d.deletable_ind" +
        "      ,d.active_ind" +
        "      ,d.updated_cnt" +
        "  FROM dictionary d" +
        " INNER JOIN tenant_feature tf ON (tf.tenant_id = d.tenant_id AND tf.feature_id = d.feature_id AND tf.active_ind = true)" +
        " WHERE d.tenant_id = :tenant_id";

    private final String INSERT_DICTIONARY =
        "INSERT INTO dictionary (" +
        "  tenant_id" +
        " ,feature_id" +
        " ,display" +
        " ,display_key" +
        " ,meaning" +
        " ,description" +
        " ,viewable_ind" +
        " ,editable_ind" +
        " ,deletable_ind" +
        " ,active_ind" +
        " ,created_id" +
        " ,created_source" +
        " ,updated_id" +
        " ,updated_source" +
        ") SELECT" +
        "  :tenant_id" +
        " ,f.feature_id" +
        " ,:display" +
        " ,:display_key" +
        " ,:meaning" +
        " ,:description" +
        " ,:viewable_ind" +
        " ,:editable_ind" +
        " ,:deletable_ind" +
        " ,:active_ind" +
        " ,:created_id" +
        " ,:created_source" +
        " ,:updated_id" +
        " ,:updated_source" +
        " FROM feature f WHERE f.meaning = :feature_meaning" +
        " RETURNING dictionary_id";

    private final String UPDATE_DICTIONARY =
        "UPDATE dictionary" +
        "   SET display = :display" +
        "      ,display_key = :display_key" +
        "      ,description = :description" +
        "      ,viewable_ind = :viewable_ind" +
        "      ,editable_ind = :editable_ind" +
        "      ,deletable_ind = :deletable_ind" +
        "      ,active_ind = :active_ind" +
        "      ,updated_id = :updated_id" +
        "      ,updated_source = :updated_source" +
        " WHERE tenant_id = :tenant_id" +
        "   AND dictionary_id = :dictionary_id" +
        "   AND updated_cnt = :updated_cnt";

    @Autowired
    public DictionaryRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<Dictionary> findById(@NonNull SecurityInfo securityInfo, long dictionaryId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var query = baseSelectQuery + " AND d.dictionary_id = :dictionary_id";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("dictionary_id", dictionaryId)
            .map(this.DICTIONARY_MAPPER)
            .one();
    }

    @Override
    public Flux<Dictionary> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(ids, "Argument [ids] cannot be null or empty.");

        var query = baseSelectQuery + " AND d.dictionary_id IN (:ids)";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("ids", ids)
            .map(this.DICTIONARY_MAPPER)
            .all();
    }

    @Override
    public Mono<Dictionary> findByMeaning(@NonNull SecurityInfo securityInfo, @NonNull String meaning) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(meaning, "Argument [meaning] cannot be null or empty");

        var query = baseSelectQuery + " AND d.meaning = :meaning";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("meaning", meaning)
            .map(this.DICTIONARY_MAPPER)
            .one();
    }

    @Override
    public Flux<Dictionary> findByMeanings(@NonNull SecurityInfo securityInfo, @NonNull Collection<String> meanings) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(meanings, "Argument [meanings] cannot be null or empty.");

        var query = baseSelectQuery + " AND d.meaning IN (:meanings)";

        return databaseClient.sql(query)
            .bind("tenant_id", securityInfo.tenantId())
            .bind("meanings", meanings)
            .map(DICTIONARY_MAPPER)
            .all();
    }

    @Override
    public Flux<Dictionary> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var parameters = new SqlParameterMap();
        parameters.addValue("tenant_id", securityInfo.tenantId());

        var query = baseSelectQuery;
        query += appendSearchText(searchCriteria, "d.display_key", parameters, "AND");

        if (searchCriteria == null || !searchCriteria.includeInactive()) {
            query += " AND d.active_ind = true";
        }

        query += " ORDER BY d.display";
        query += appendStartLimit(searchCriteria, parameters);

        LOGGER.trace("Final Query:\n{}", query);

        var executeSpec = databaseClient.sql(query);
        executeSpec = parameters.bind(executeSpec);

        return executeSpec.map(this.DICTIONARY_MAPPER).all();
    }

    @Override
    public Mono<Dictionary> insert(@NonNull SecurityInfo securityInfo,
                                   @NonNull Dictionary dictionary,
                                   @NonNull String featureMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionary, "Argument [dictionary] cannot be null");
        Assert.hasText(featureMeaning, "Argument [featureMeaning] cannot be null or empty");

        if (!dictionary.active()) {
            // if the dictionary isn't active,
            // there is no point in persisting it
            return Mono.just(dictionary);
        }

        LOGGER.info("Inserting Dictionary [{}] for Tenant [{}]", dictionary.meaning(), securityInfo.tenantId());

        var spec = databaseClient.sql(INSERT_DICTIONARY);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("feature_meaning", featureMeaning)
            .addValue("display", dictionary.display())
            .addValue("display_key", CommonStringUtils.normalizeToKey(dictionary.display()))
            .addValue("meaning", dictionary.meaning())
            .addValue("description", dictionary.description(), String.class)
            .addValue("viewable_ind", dictionary.viewable())
            .addValue("editable_ind", dictionary.editable())
            .addValue("deletable_ind", dictionary.deletable())
            .addValue("active_ind", dictionary.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch().one()
            .map(result -> (Long)result.get("dictionary_id"))
            .map(dictionaryId -> new Dictionary.Builder()
                .from(dictionary)
                .dictionaryId(dictionaryId)
                .build()
            );
    }

    @Override
    public Mono<Dictionary> update(@NonNull SecurityInfo securityInfo, @NonNull Dictionary dictionary) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionary, "Argument [dictionary] cannot be null");

        var spec = databaseClient.sql(UPDATE_DICTIONARY);
        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("display", dictionary.display())
            .addValue("display_key", CommonStringUtils.normalizeToKey(dictionary.display()))
            .addValue("description", dictionary.description(), String.class)
            .addValue("viewable_ind", dictionary.viewable())
            .addValue("editable_ind", dictionary.editable())
            .addValue("deletable_ind", dictionary.deletable())
            .addValue("active_ind", dictionary.active())
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .addValue("dictionary_id", dictionary.dictionaryId())
            .addValue("updated_cnt", dictionary.updatedCount())
            .bind(spec);

        return spec.fetch().rowsUpdated()
            .map(__ -> new Dictionary.Builder()
                .from(dictionary)
                .updatedCount(dictionary.updatedCount() + 1)
                .build()
            );
    }
}
