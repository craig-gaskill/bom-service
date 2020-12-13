package com.cagst.bom.user.role;

import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting User Role records.
 *
 * @author Craig Gaskill
 */
public interface UserRoleRepository {
    /**
     * Finds all {@link UserRole} records for the given User.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userId
     *      The unique identifier of the User to retrieve Roles for.
     *
     * @return A {@link Flux} that will emit all the {@link UserRole} records for the given User.
     */
    Flux<UserRole> findForUser(@NonNull SecurityInfo securityInfo, long userId);

    /**
     * Will merge (insert or update) the given {@link UserRole} to persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userRole
     *      The {@link UserRole} record to merge into persistent storage.
     *
     * @return The {@link UserRole} record after it has been merged into persistent storage.
     */
    Mono<UserRole> merge(@NonNull SecurityInfo securityInfo, @NonNull UserRole userRole);
}
