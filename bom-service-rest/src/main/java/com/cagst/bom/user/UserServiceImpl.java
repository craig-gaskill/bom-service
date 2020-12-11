package com.cagst.bom.user;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.webflux.exception.ConflictResourceException;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import com.cagst.bom.user.event.UserEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An implementation of the {@link UserService} that provides the business rules needed to properly
 * retrieve and persist {@link User} resources.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEventPublisher userEventPublisher;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserEventPublisher userEventPublisher
    ) {
        this.userRepository = userRepository;
        this.userEventPublisher = userEventPublisher;
    }

    @Override
    public Mono<User> findById(@NonNull SecurityInfo securityInfo, long userId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return userRepository.findById(securityInfo, userId)
            .map(UserConverter::convert);
    }

    @Override
    public Mono<User> findByUsername(@NonNull String username) {
        Assert.hasText(username, "Argument [username] cannot be null");

        return userRepository.findByUsername(username)
            .map(UserConverter::convert);
    }

    @Override
    public Flux<User> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(ids, "Argument [ids] cannot be null or empty");

        return userRepository.findByIds(securityInfo, ids)
            .map(UserConverter::convert);
    }

    @Override
    public Flux<User> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return userRepository.findByCriteria(securityInfo, searchCriteria)
            .map(UserConverter::convert);
    }

    @Override
    public Mono<User> insert(@NonNull SecurityInfo securityInfo, @NonNull User user) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");

        return checkForDuplicates(securityInfo, user)
            .flatMap(__ -> userRepository.insert(securityInfo, UserConverter.convert(user)))
            .map(UserConverter::convert)
            .doOnSuccess(usr -> userEventPublisher.publishUserCreatedEvent(securityInfo, usr));
    }

    @Override
    public Mono<User> update(@NonNull SecurityInfo securityInfo, @NonNull User user) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");

        return checkForDuplicates(securityInfo, user)
            .flatMap(result -> {
                if (result == user) {
                    // if the 'checkForDuplicate' returned the same User we are trying to update
                    // we still want to ensure the User exists
                    return checkExists(securityInfo, user.userId());
                } else {
                    // if the 'checkForDuplicate' returned a different User
                    // then the User as found but won't vause a duplicate...so the User does exist
                    return Mono.just(result);
                }
            })
            .flatMap(found -> {
                if (found.equals(user)) {
                    // if the found User is the same as the specified User
                    // there is nothing to do (no save is necessary).
                    return Mono.just(user);
                } else {
                    return userRepository.update(securityInfo, UserConverter.convert(user))
                        .map(UserConverter::convert);
                }
            })
            .doOnSuccess(usr -> userEventPublisher.publishUserUpdatedEvent(securityInfo, usr));
    }

    @Override
    public Mono<Void> delete(@NonNull SecurityInfo securityInfo, long userId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return checkExists(securityInfo, userId)
            .flatMap(found -> {
                if (found.active()) {
                    return userRepository.update(
                        securityInfo,
                        new UserEntity.Builder().from(found).active(false).build()
                    )
                    .map(UserConverter::convert);
                } else {
                    return Mono.just(found);
                }
            })
            .doOnSuccess(usr -> userEventPublisher.publishUserDeletedEvent(securityInfo, usr))
            .then();
    }

    @Override
    public Flux<User> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<User> users) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(users, "Argument [users] cannot be null");

        return users.flatMap(user -> {
            if (user.userId() == null) {
                return insert(securityInfo, user);
            } else {
                return update(securityInfo, user);
            }
        });
    }

    @Override
    public Mono<User> lockUser(@NonNull SecurityInfo securityInfo,
                               long userId,
                               @NonNull AccountLockedType lockedType
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(lockedType, "Argument [lockedType] cannot be null");

        return userRepository.findById(securityInfo, userId)
            .flatMap(result -> userRepository.update(
                securityInfo,
                new UserEntity.Builder()
                    .from(result)
                    .accountLockedDateTime(OffsetDateTime.now())
                    .accountLockedType(lockedType)
                    .build()
            ))
            .map(UserConverter::convert)
            .doOnSuccess(usr -> userEventPublisher.publishUserLockedEvent(securityInfo, usr, lockedType))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundResourceException("User [" + userId + "] was not found"))));
    }

    @Override
    public Mono<User> unlockUser(@NonNull SecurityInfo securityInfo, long userId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return userRepository.findById(securityInfo, userId)
            .flatMap(result -> userRepository.update(
                securityInfo,
                new UserEntity.Builder()
                    .from(result)
                    .accountLockedDateTime(null)
                    .accountLockedType(null)
                    .build()
            ))
            .map(UserConverter::convert)
            .doOnSuccess(usr -> userEventPublisher.publishUserUnlockedEvent(securityInfo, usr))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundResourceException("User [" + userId + "] was not found"))));
    }

    /**
     * Will check to ensure the specified User won't cause a duplicate record.
     * Duplicate is determined if more than 1 record would have the same 'username'.
     *
     * @return A {@link Mono} that either emits an error (if it would cause a duplicate record),
     * emits the existing User (if found but no duplicate), or emits the specified User (if not found and no duplicate).
     */
    private Mono<User> checkForDuplicates(SecurityInfo securityInfo, User check) {
        return userRepository.findByUsername(check.username())
            .flatMap(found -> {
                if (check.userId() == null || !Objects.equals(check.userId(), found.userId())) {
                    return Mono.error(new ConflictResourceException("A User already exists with the username of [" + check.username() + "]"));
                } else {
                    return Mono.just(found);
                }
            })
            .map(UserConverter::convert)
            .defaultIfEmpty(check);
    }

    private Mono<User> checkExists(SecurityInfo securityInfo, long userId) {
        return findById(securityInfo, userId)
            .switchIfEmpty(Mono.defer(() -> Mono.error(new NotFoundResourceException("User [" + userId + "] was not found"))));
    }
}
