package com.cagst.bom.user;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link UserEntity} records.
 *
 * @author Craig Gaskill
 */
public interface UserRepository {
    /**
     * Finds a {@link UserEntity} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userId
     *      The unique identifier of the User to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link UserEntity} if found.
     */
    Mono<UserEntity> findById(@NonNull SecurityInfo securityInfo, long userId);

    /**
     * Finds all the {@link UserEntity} for the given unique identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param userIds
     *      A {@link Collection} of unique identifiers of the Users to retrieve.
     *
     * @return A {@link Flux} that emits all the Users for the specified unique identifiers.
     */
    Flux<UserEntity> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> userIds);

    /**
     * Finds a {@link UserEntity} by its username.
     *
     * @param username
     *      The username of the User to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link UserEntity} if found for the given username.
     */
    Mono<UserEntity> findByUsername(@NonNull String username);

    /**
     * Finds all {@link UserEntity}s.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param searchCriteria
     *      The {@link SecurityInfo} to use to qualify User records on.
     *
     * @return A {@link Flux} that will emit all the Users.
     */
    Flux<UserEntity> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link UserEntity} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param user
     *      The {@link UserEntity} to insert.
     *
     * @return A {@link Mono} that emits the User after it was inserted.
     */
    Mono<UserEntity> insert(@NonNull SecurityInfo securityInfo, @NonNull UserEntity user);

    /**
     * Updates an existing {@link UserEntity} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param user
     *      The {@link UserEntity} to update.
     *
     * @return A {@link Mono} that emits the User after it was updated.
     */
    Mono<UserEntity> update(@NonNull SecurityInfo securityInfo, @NonNull UserEntity user);

    /*
     * These methods should only be used by the Authentication Service since they don't require
     * a PlatformSecurityInfo and are intended to used by the login workflow.
     */

    /**
     * Will increment the login attempts for the User.
     *
     * @param user
     *      The {@link UserEntity} to increment the login attempts for.
     *
     * @return A {@link Mono} that emits the User after its login attempts were incremented.
     */
    Mono<UserEntity> incrementAttempt(@NonNull UserEntity user);

    /**
     * Will reset the login attempts for the User.
     *
     * @param user
     *      The {@link UserEntity} to reset the login attempts for.
     *
     * @return A {@link Mono} that emits the User after its login attempts were reset.
     */
    Mono<UserEntity> resetAttempts(@NonNull UserEntity user);

    /**
     * Will lock the {@link UserEntity} account.
     *
     * @param user
     *      The {@link UserEntity} to lock.
     *
     * @return A {@link Mono} that emits the User after it was locked.
     */
    Mono<UserEntity> lockUser(@NonNull UserEntity user);

    /**
     * Will unlock the {@link UserEntity} account.
     *
     * @param user
     *      The {@link UserEntity} to unlock.
     *
     * @return A {@link Mono} that emits the User after it was unlocked.
     */
    Mono<UserEntity> unlockUser(@NonNull UserEntity user);

    /**
     * Will set the user's password as expired.
     *
     * @param user
     *      The {@link UserEntity} to set the password as expired for.
     *
     * @return A {@link Mono} that emits the User after it's password has been set as expired.
     */
    Mono<UserEntity> expirePassword(@NonNull UserEntity user);

    /**
     * Will change the {@link UserEntity} password.
     *
     * @param user
     *      The {@link UserEntity} to change the password for.
     * @param encodedPassword
     *      The new (encoded) password for the User.
     *
     * @return A {@link Mono} that emits after the User's passowrd has been changed.
     */
    Mono<UserEntity> changePassword(@NonNull UserEntity user, @NonNull String encodedPassword);
}
