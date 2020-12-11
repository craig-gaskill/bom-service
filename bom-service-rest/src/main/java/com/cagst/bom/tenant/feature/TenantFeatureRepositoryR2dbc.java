package com.cagst.bom.tenant.feature;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.r2dbc.BaseRepositoryR2dbc;
import com.cagst.bom.spring.r2dbc.SqlParameterMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An R2DBC implementation of {@link TenantFeatureRepository}.
 *
 * @author Craig Gaskill
 */
@Repository
/* package */ class TenantFeatureRepositoryR2dbc extends BaseRepositoryR2dbc implements TenantFeatureRepository {

    private final TenantFeatureMapper TENANT_FEATURE_MAPPER = new TenantFeatureMapper();

    private final DatabaseClient databaseClient;

    @Autowired
    public TenantFeatureRepositoryR2dbc(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Flux<TenantFeature> find(@NonNull SecurityInfo securityInfo) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        var FIND_FEATURES =
            "SELECT tf.tenant_feature_id" +
            "      ,f.meaning" +
            "      ,f.display" +
            "      ,f.description" +
            "      ,COALESCE(tf.active_ind, false) AS active_ind" +
            "      ,COALESCE(tf.updated_cnt, 0) AS updated_cnt" +
            "  FROM feature f" +
            "  LEFT OUTER JOIN tenant_feature tf ON (tf.feature_id = f.feature_id AND tf.tenant_id = :tenant_id)";

        var spec = databaseClient.sql(FIND_FEATURES);

        spec = new SqlParameterMap()
            .addValue("tenant_id", securityInfo.tenantId())
            .bind(spec);

        return spec.map(TENANT_FEATURE_MAPPER).all();
    }

    @Override
    public Mono<TenantFeature> insert(@NonNull SecurityInfo securityInfo, @NonNull TenantFeature tenantFeature) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(tenantFeature, "Argument [tenantFeature] cannot be null");

        if (!tenantFeature.active()) {
            // if the feature isn't being activated for the Tenant
            // there is no reason to persist it
            return Mono.just(tenantFeature);
        }

        var INSERT_FEATURE =
            "INSERT INTO tenant_feature (" +
            "  feature_id" +
            " ,tenant_id" +
            " ,active_ind" +
            " ,created_id" +
            " ,created_source" +
            " ,updated_id" +
            " ,updated_source" +
            ") SELECT" +
            "  f.feature_id" +
            " ,:tenant_id" +
            " ,:active_ind" +
            " ,:created_id" +
            " ,:created_source" +
            " ,:updated_id" +
            " ,:updated_source" +
            " FROM feature f WHERE f.meaning = :meaning" +
            " RETURNING tenant_feature_id";

        var spec = databaseClient.sql(INSERT_FEATURE);

        spec = new SqlParameterMap()
            .addValue("meaning", tenantFeature.meaning())
            .addValue("tenant_id", securityInfo.tenantId())
            .addValue("active_ind", tenantFeature.active())
            .addValue("created_id", securityInfo.userId())
            .addValue("created_source", securityInfo.source(), String.class)
            .addValue("updated_id", securityInfo.userId())
            .addValue("updated_source", securityInfo.source(), String.class)
            .bind(spec);

        return spec.fetch()
            .one()
            .map(result -> (Long)result.get("tenant_feature_id"))
            .map(tenantFeatureId -> new TenantFeature.Builder()
                .from(tenantFeature)
                .tenantFeatureId(tenantFeatureId)
                .build()
            );
    }

    @Override
    public Mono<TenantFeature> update(@NonNull SecurityInfo securityInfo, @NonNull TenantFeature tenantFeature) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(tenantFeature, "Argument [tenantFeature] cannot be null");

        var UPDATE_FEATURE =
            "UPDATE tenant_feature" +
            "   SET active_ind = :active" +
            "      ,updated_id = :updated_id" +
            "      ,updated_source = :updated_source" +
            " WHERE tenant_feature_id = :tenant_feature_id AND updated_cnt = :updated_cnt";

        var spec = databaseClient.sql(UPDATE_FEATURE);

        spec = new SqlParameterMap()
            .addValue("active_ind", tenantFeature.active())
            .bind(spec);

        return spec.then()
            .map(__ -> new TenantFeature.Builder()
                .from(tenantFeature)
                .updatedCount(tenantFeature.updatedCount() + 1)
                .build()
            );
    }

    @Override
    public Flux<TenantFeature> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<TenantFeature> tenantFeatures) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return tenantFeatures.flatMap(feature -> {
            if (feature.tenantFeatureId() == null) {
                return insert(securityInfo, feature);
            } else {
                return update(securityInfo, feature);
            }
        });
    }
}
