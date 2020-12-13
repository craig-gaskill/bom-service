package com.cagst.bom.user.role;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = UserRole.Builder.class)
@JsonPropertyOrder({
    "userRoleId",
    "userId",
    "roleId",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface UserRole extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long userRoleId();

    long userId();

    long roleId();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableUserRole.Builder implements BaseDTO.Builder {}
}
