package com.cagst.bom.role.permission;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = RolePermission.Builder.class)
@JsonPropertyOrder({
    "rolePermissionId",
    "permissionId",
    "code",
    "display",
    "description",
    "granted",
    "display",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface RolePermission extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long rolePermissionId();

    long permissionId();

    String code();

    String display();

    @Nullable
    String description();

    @Value.Default
    default boolean granted() {
        return false;
    }

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableRolePermission.Builder implements BaseDTO.Builder {}
}
