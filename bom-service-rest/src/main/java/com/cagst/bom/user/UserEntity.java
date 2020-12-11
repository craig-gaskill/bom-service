package com.cagst.bom.user;

import java.time.OffsetDateTime;
import java.util.List;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.security.SecurityPolicy;
import com.cagst.bom.user.access.UserAccess;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface UserEntity extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long userId();

    String username();

    @Nullable
    @Value.Redacted
    String password();

    @Value.Default
    default boolean temporaryPassword() {
        return true;
    }

    @Value.Auxiliary
    @Value.Default
    default int loginAttempts() {
        return 0;
    }

    @Nullable
    OffsetDateTime accountLockedDateTime();

    @Nullable
    AccountLockedType accountLockedType();

    @Nullable
    OffsetDateTime accountExpiredDateTime();

    @Nullable
    OffsetDateTime passwordChangedDateTime();

    @Nullable
    OffsetDateTime passwordExpiredDateTime();

    @Value.Default
    default boolean admin() {
        return false;
    }

    @Nullable
    List<UserAccess> access();

    @Nullable
    @Value.Auxiliary
    SecurityPolicy securityPolicy();

    @Nullable
    @Value.Derived
    default OffsetDateTime passwordExpireDateTime() {
        if (securityPolicy() == null || securityPolicy().expiryInDays() <= 0) {
            return null;
        } else if (passwordExpiredDateTime() != null) {
            return passwordExpiredDateTime();
        } else {
            // calculate when the user's password will expire
            OffsetDateTime changedOn = (passwordChangedDateTime() != null ? passwordChangedDateTime() : createdDateTime());
            return changedOn.plusDays(securityPolicy().expiryInDays());
        }
    }

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableUserEntity.Builder implements BaseDTO.Builder {}
}
