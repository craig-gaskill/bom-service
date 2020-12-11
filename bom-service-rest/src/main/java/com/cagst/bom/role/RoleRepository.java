package com.cagst.bom.role;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link Role} records.
 * 
 * @author Craig Gaskill
 */
public interface RoleRepository {
    /**
     * Finds a {@link Role} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param roleId
     *      The unique identifier of the Role to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Role} if found.
     */
    @NonNull
    Mono<Role> findById(@NonNull SecurityInfo securityInfo, long roleId);

    /**
     * Finds all the {@link Role} for the given unique identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param ids
     *      A {@link Collection} of unique identifiers of Roles to retrieve.
     *
     * @return A {@link Flux} that emits all the Roles for the specified unique identifiers.
     */
    @NonNull
    Flux<Role> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids);

    /**
     * Finds all {@link Role Roles} matching the specified criteria (if specified).
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Role records on.
     *
     * @return A {@link Flux} that will emit all the Roles matching the specified criteria.
     */
    @NonNull
    Flux<Role> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link Role} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param role
     *      The {@link Role} to insert.
     *
     * @return A {@link Mono} that emits the Role after it was inserted.
     */
    @NonNull
    Mono<Role> insert(@NonNull SecurityInfo securityInfo, @NonNull Role role);

    /**
     * Updates an existing {@link Role} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param role
     *      The {@link Role} to update
     *
     * @return A {@link Mono} that emits the Role after it was updated.
     */
    @NonNull
    Mono<Role> update(@NonNull SecurityInfo securityInfo, @NonNull Role role);
}
