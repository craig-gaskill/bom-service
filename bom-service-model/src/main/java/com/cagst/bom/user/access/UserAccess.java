package com.cagst.bom.user.access;

import java.time.OffsetDateTime;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonPropertyOrder({
    "userAccessId",
    "userId",
    "tenantId",
    "personId",
    "defaultIndicator",
    "lastLoginDateTime",
    "lastLoginIp",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface UserAccess extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long userAccessId();

    long userId();

    int tenantId();

    long personId();

    @Value.Default
    default boolean defaultIndicator() {
        return false;
    }

    @Nullable
    OffsetDateTime lastLoginDateTime();

    @Nullable
    String lastLoginIp();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableUserAccess.Builder implements BaseDTO.Builder {}
}
