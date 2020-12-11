package com.cagst.bom.dictionary.attribute;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = DictionaryAttribute.Builder.class)
@JsonPropertyOrder({
    "dictionaryAttributeId",
    "display",
    "meaning",
    "description",
    "attributeType",
    "viewable",
    "editable",
    "deletable",
    "sortOrder",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface DictionaryAttribute extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long dictionaryAttributeId();

    String display();

    String meaning();

    @Nullable
    String description();

    DictionaryAttributeType attributeType();

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

    @Nullable
    Integer sortOrder();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableDictionaryAttribute.Builder implements BaseDTO.Builder {}
}
