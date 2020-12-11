package com.cagst.bom.user;

import java.time.OffsetDateTime;
import java.util.List;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.user.access.UserAccess;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = User.Builder.class)
@JsonPropertyOrder({
    "userId",
    "username",
    "temporaryPassword",
    "accountLockedDateTime",
    "accountLockType",
    "accountExpiredDateTime",
    "passwordChangedDateTime",
    "passwordExpiredDateTime",
    "admin",
    "access",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface User extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long userId();

    String username();

    @Value.Default
    default boolean temporaryPassword() {
        return true;
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

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableUser.Builder implements BaseDTO.Builder {}
}
