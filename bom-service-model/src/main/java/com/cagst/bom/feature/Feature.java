package com.cagst.bom.feature;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = Feature.Builder.class)
@JsonPropertyOrder({
    "featureId",
    "display",
    "meaning",
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
public interface Feature extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Short featureId();

    String display();

    String meaning();

    @Nullable
    String description();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableFeature.Builder implements BaseDTO.Builder {}
}
