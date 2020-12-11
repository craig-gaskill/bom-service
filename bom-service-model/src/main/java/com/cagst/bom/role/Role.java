package com.cagst.bom.role;

import java.util.Collection;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.role.permission.RolePermission;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = Role.Builder.class)
@JsonPropertyOrder({
    "roleId",
    "name",
    "fullAccess",
    "permissions",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface Role extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long roleId();

    String name();

    @Value.Default
    default boolean fullAccess() {
        return false;
    }

    @Nullable
    Collection<RolePermission> permissions();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableRole.Builder implements BaseDTO.Builder {}
}
