package com.cagst.bom.user;

/**
 * Converter methods to transform between {@link User} and {@link UserEntity} objects.
 *
 * @author Craig Gaskill
 */
public abstract class UserConverter {
    public static User convert(UserEntity entity) {
        return new User.Builder()
            .userId(entity.userId())
            .username(entity.username())
            .temporaryPassword(entity.temporaryPassword())
            .accountLockedDateTime(entity.accountLockedDateTime())
            .passwordChangedDateTime(entity.passwordChangedDateTime())
            .passwordExpiredDateTime(entity.passwordExpiredDateTime())
            .admin(entity.admin())
            .access(entity.access())
            .createdId(entity.createdId())
            .createdDateTime(entity.createdDateTime())
            .active(entity.active())
            .updatedId(entity.updatedId())
            .updatedDateTime(entity.updatedDateTime())
            .updatedCount(entity.updatedCount())
            .build();
    }

    public static UserEntity convert(User user) {
        return new UserEntity.Builder()
            .userId(user.userId())
            .username(user.username())
            .temporaryPassword(user.temporaryPassword())
            .accountLockedDateTime(user.accountLockedDateTime())
            .passwordChangedDateTime(user.passwordChangedDateTime())
            .passwordExpiredDateTime(user.passwordExpiredDateTime())
            .admin(user.admin())
            .access(user.access())
            .createdId(user.createdId())
            .createdDateTime(user.createdDateTime())
            .active(user.active())
            .updatedId(user.updatedId())
            .updatedDateTime(user.updatedDateTime())
            .updatedCount(user.updatedCount())
            .build();
    }
}
