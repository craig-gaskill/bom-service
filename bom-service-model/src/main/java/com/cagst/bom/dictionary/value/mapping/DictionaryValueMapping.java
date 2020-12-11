package com.cagst.bom.dictionary.value.mapping;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = DictionaryValueMapping.Builder.class)
@JsonPropertyOrder({
    "dictionaryValueMapping",
    "dictionaryValueId",
    "display",
    "meaning",
    "containingSystem",
    "viewable",
    "editable",
    "deletable",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface DictionaryValueMapping extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long dictionaryValueMappingId();

    Long dictionaryValueId();

    String display();

    String meaning();

    String containingSystem();

    @Value.Default
    default boolean viewable() {
        return true;
    }

    @Value.Default
    default boolean editable() {
        return true;
    }

    @Value.Default
    default boolean deletable() {
        return true;
    }

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableDictionaryValueMapping.Builder implements BaseDTO.Builder {}
}
