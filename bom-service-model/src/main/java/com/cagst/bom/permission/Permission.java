package com.cagst.bom.permission;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = Permission.Builder.class)
@JsonPropertyOrder({
    "permissionId",
    "display",
    "code",
    "description",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface Permission extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long permissionId();

    String display();

    String code();

    @Nullable
    String description();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutablePermission.Builder implements BaseDTO.Builder {}
}
