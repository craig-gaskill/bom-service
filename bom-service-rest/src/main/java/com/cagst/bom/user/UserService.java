package com.cagst.bom.user;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods to retrieve / persist {@link User} resources.
 *
 * @author Craig Gaskill
 */
public interface UserService {
    /**
     * Finds a {@link User} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userId
     *      The unique identifier of the User to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link User} if found.
     */
    Mono<User> findById(@NonNull SecurityInfo securityInfo, long userId);

    /**
     * Finds a {@link User} by its unique identifier.
     *
     * @param username
     *      The username of the User to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link User} if found.
     */
    Mono<User> findByUsername(@NonNull String username);

    /**
     * Finds all the {@link User} for the given unique identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param ids
     *      A {@link Collection} of unique identifiers of Users to retrieve.
     *
     * @return A {@link Flux} that emits all the Users for the specified unique identifiers.
     */
    Flux<User> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids);

    /**
     * Finds all {@link User}s that match the specified {@link SearchCriteria} (if provided).
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify User records on.
     *
     * @return A {@link Flux} that will emit all the Users that match the specified search criteria (if provided).
     */
    Flux<User> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link User} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param user
     *      The {@link User} to insert.
     *
     * @return A {@link Mono} that emits the User after it was inserted.
     */
    Mono<User> insert(@NonNull SecurityInfo securityInfo, @NonNull User user);

    /**
     * Updates an existing {@link User} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param user
     *      The {@link User} to update.
     *
     * @return A {@link Mono} that emits the User after it was updated.
     */
    Mono<User> update(@NonNull SecurityInfo securityInfo, @NonNull User user);

    /**
     * Deletes an existing {@link User}, identified by its unique identifier, from persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userId
     *      The unique identifier of the User to delete.
     *
     * @return A {@link Mono} that emits when successfully deleted.
     */
    Mono<Void> delete(@NonNull SecurityInfo securityInfo, long userId);

    /**
     * Saves the {@link Flux} of Users into persistent storage. Will insert or update as needed.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param users
     *      The {@link Flux} of Users to save.
     *
     * @return The {@link Flux} of Users after they have been persisted.
     */
    Flux<User> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<User> users);

    /**
     * Will lock the {@link User} account.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userId
     *      The unique identifier of the User to lock.
     * @param lockedType
     *      The {@link AccountLockedType Type} of lock.
     *
     * @return A {@link Mono} that emits the User after it was locked.
     */
    Mono<User> lockUser(@NonNull SecurityInfo securityInfo,
                        long userId,
                        @NonNull AccountLockedType lockedType);

    /**
     * Will unlock the {@link User} account.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userId
     *      The unique identifier of the User to unlock.
     *
     * @return A {@link Mono} that emits the User after it was unlocked.
     */
    Mono<User> unlockUser(@NonNull SecurityInfo securityInfo, long userId);
}
