package com.cagst.bom.tenant;

import java.util.Collection;
import java.util.Set;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods to retrieve / persist {@link Tenant} resources.
 *
 * @author Craig Gaskill
 */
public interface TenantService {
    /**
     * Finds a {@link Tenant} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
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
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
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
     * Finds all the {@link Tenant}s that match the specified criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Tenant records on.
     *
     * @return A {@link Flux} that will emit all the Tenants that match the specified criteria.
     */
    Flux<Tenant> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);

    /**
     * Finds all the {@link Tenant}s for the specified identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param identifiers
     *      A {@link Collection} of identifiers to retrieve Tenant records for. If the identifier is numeric, it will
     *      be assumed to be the unique identifier, if the identifier is a string it will be assumed to be the mnemonic.
     *
     * @return A {@link Flux} that will emit all the 'distinct' Tenants for the the given identifiers.
     */
    Flux<Tenant> findByIdentifiers(@NonNull SecurityInfo securityInfo, @NonNull Collection<String> identifiers);

    /**
     * Inserts a new {@link Tenant} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param tenant
     *      The {@link Tenant} to insert.
     * @param featureMeanings
     *      A {@link Set} of {@link String} that indicate the meaning of the features ot associate with the new Tenant.
     *
     * @return A {@link Mono} that emits the Tenant after it was inserted.
     */
    Mono<Tenant> insert(@NonNull SecurityInfo securityInfo,
                        @NonNull Tenant tenant,
                        @Nullable Set<String> featureMeanings);

    /**
     * Updates an existing {@link Tenant} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param tenant
     *      The {@link Tenant} to update.
     *
     * @return A {@link Mono} that emits the Tenant after it was updated.
     */
    Mono<Tenant> update(@NonNull SecurityInfo securityInfo, @NonNull Tenant tenant);

    /**
     * Deletes an existing {@link Tenant} (identified by its unique identifier) from persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param tenantId
     *      The unique identifier of the Tenant to delete.
     *
     * @return A {@link Mono} that emits when complete.
     */
    Mono<Void> deleteById(@NonNull SecurityInfo securityInfo, int tenantId);

    /**
     * Deletes an existing {@link Tenant} (identified by its mnemonic) from persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param mnemonic
     *      The mnemonic of the Tenant to delete.
     *
     * @return A {@link Mono} that emits when complete.
     */
    Mono<Void> deleteByMnemonic(@NonNull SecurityInfo securityInfo, @NonNull String mnemonic);

    /**
     * Saves the {@link Flux} of Tenants into persistent storage. Will insert or update as needed.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param tenants
     *      The {@link Flux} of Tenants to save.
     *
     * @return The {@link Flux} of Tenants after they have been persisted.
     */
    Flux<Tenant> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<Tenant> tenants);
}
