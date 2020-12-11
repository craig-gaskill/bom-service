package com.cagst.bom.tenant.feature;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = TenantFeature.Builder.class)
@JsonPropertyOrder({
    "tenantFeatureId",
    "meaning",
    "display",
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
public interface TenantFeature extends BaseDTO {
    @Nullable
    Long tenantFeatureId();

    String meaning();

    @Nullable
    String display();

    @Nullable
    String description();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableTenantFeature.Builder implements BaseDTO.Builder { }
}
