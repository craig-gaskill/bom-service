package com.cagst.bom.tenant;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link Tenant} records.
 *
 * @author Craig Gaskill
 */
public interface TenantRepository {
    /**
     * Finds a {@link Tenant} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param tenantId
     *      The unique identifier of the Tenant to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Tenant} if found.
     */
    Mono<Tenant> findById(@NonNull SecurityInfo securityInfo, int tenantId);

    /**
     * Finds all {@link Tenant} for the given unique identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param ids
     *      A {@link Collection} of unique identifiers of Tenants to retrieve.
     *
     * @return A {@link Flux} that emits all the Tenants for the specified unique identifiers.
     */
    Flux<Tenant> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Integer> ids);

    /**
     * Finds a {@link Tenant} by its mnemonic.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param mnemonic
     *      The mnemonic of the Tenant to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Tenant} if found.
     */
    Mono<Tenant> findByMnemonic(@NonNull SecurityInfo securityInfo, @NonNull String mnemonic);

    /**
     * Finds all {@link Tenant} for the given mnemonics.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param mnemonics
     *      A {@link Collection} of mnemonics of Tenants to retrieve.
     *
     * @return A {@link Flux} that emits all the Tenants for the specified mnemonics.
     */
    Flux<Tenant> findByMnemonics(@NonNull SecurityInfo securityInfo, @NonNull Collection<String> mnemonics);

    /**
     * Finds all the {@link Tenant}s for the specified search criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Tenant records on.
     *
     * @return A {@link Flux} that will emit all the Tenants matching the specified search criteria.
     */
    Flux<Tenant> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link Tenant} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param tenant
     *      The {@link Tenant} to insert.
     *
     * @return A {@link Mono} that emits the Tenant after it was inserted.
     */
    Mono<Tenant> insertTenant(@NonNull SecurityInfo securityInfo, @NonNull Tenant tenant);

    /**
     * Updates an existing {@link Tenant} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param tenant
     *      The {@link Tenant} to update.
     *
     * @return A {@link Mono} that emits the Tenant after it was updated.
     */
    Mono<Tenant> updateTenant(@NonNull SecurityInfo securityInfo, @NonNull Tenant tenant);
}
