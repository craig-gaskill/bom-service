package com.cagst.bom.tenant.feature;

import java.util.List;

import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link TenantFeature} records.
 *
 * @author Craig Gaskill
 */
public interface TenantFeatureRepository {
    /**
     * Finds all the {@link TenantFeature}.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     *
     * @return A {@link Flux} that will emit all the Tenant Features.
     */
    Flux<TenantFeature> find(@NonNull SecurityInfo securityInfo);

    /**
     * Inserts a new {@link TenantFeature} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param tenantFeature
     *      The {@link TenantFeature} to insert.
     *
     * @return A {@link Mono} that emits the Tenant Feature after it was inserted.
     */
    Mono<TenantFeature> insert(@NonNull SecurityInfo securityInfo, @NonNull TenantFeature tenantFeature);

    /**
     * Updates an existing {@link TenantFeature} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param tenantFeature
     *      The {@link TenantFeature} to update.
     *
     * @return A {@link Mono} that emits the Tenant Feature after it was updated.
     */
    Mono<TenantFeature> update(@NonNull SecurityInfo securityInfo, @NonNull TenantFeature tenantFeature);

    /**
     * Merges (inserts or updates) the Tenant / Feature association.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param tenantFeatures
     *      A {@link List} of {@link TenantFeature Features} to save.
     *
     * @return A {@link Mono} that emits the Tenant Feature after it has been persisted.
     */
    Flux<TenantFeature> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<TenantFeature> tenantFeatures);
}
