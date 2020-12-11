package com.cagst.bom.permission;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link Permission} records.
 *
 * @author Craig Gaskill
 */
public interface PermissionRepository {
    /**
     * Finds a {@link Permission} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param permissionId
     *      The unique identifier of the Permission to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Permission} if found.
     */
    @NonNull
    Mono<Permission> findById(@NonNull SecurityInfo securityInfo, long permissionId);

    /**
     * Finds a {@link Permission} by its coe.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param code
     *      The code of the Permission to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Permission} if found.
     */
    @NonNull
    Mono<Permission> findByCode(@NonNull SecurityInfo securityInfo, @NonNull String code);

    /**
     * Finds all {@link Permission Permissions} matching the specified criteria (if specified).
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Permission records on.
     *
     * @return A {@link Flux} that will emit all the Permissions matching the specified criteria.
     */
    @NonNull
    Flux<Permission> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);
}
