package com.cagst.bom.user.access;

import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link UserAccess} records.
 *
 * @author Craig Gaskill
 */
public interface UserAccessRepository {
    /**
     * Finds the default access record for the given User. If no records is marked as the
     * default record, then the first on created will be returned.
     *
     * @param userId
     *      The unique identifier of the User to find the default access record for.
     *
     * @return A {@link Mono} that may contain the default {@link UserAccess} for the given User.
     */
    Mono<UserAccess> findDefault(long userId);

    /**
     * Finds all {@link UserAccess} records for the given Tenant.
     *
     * @param tenantId
     *      The unique identifier of the Tenant to retrieve access records for.
     *
     * @return A {@link Flux} that will emit all the {@link UserAccess} records for the given Tenant.
     */
    Flux<UserAccess> findForTenant(long tenantId);

    /**
     * Finds all {@link UserAccess} records for the given User.
     *
     * @param userId
     *      The unique identifier of the User to retrieve access records for.
     *
     * @return A {@link Flux} that will emit all the {@link UserAccess} records for the given User.
     */
    Flux<UserAccess> findForUser(long userId);

    /**
     * Will merge (insert or update) the given {@link UserAccess} to persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userAccess
     *      The {@link UserAccess} record to merge into persistent storage.
     *
     * @return The {@link UserAccess} record after it has been merged into persistent storage.
     */
    Mono<UserAccess> merge(@NonNull SecurityInfo securityInfo, @NonNull UserAccess userAccess);
}
