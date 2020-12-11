package com.cagst.bom.dictionary.value.attribute;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.dictionary.attribute.DictionaryAttributeType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = DictionaryValueAttribute.Builder.class)
@JsonPropertyOrder({
    "dictionaryValueAttributeId",
    "dictionaryValueId",
    "dictionaryAttributeId",
    "display",
    "meaning",
    "description",
    "attributeType",
    "attributeValue",
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
public interface DictionaryValueAttribute extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long dictionaryValueAttributeId();

    Long dictionaryValueId();

    Long dictionaryAttributeId();

    String display();

    String meaning();

    @Nullable
    String description();

    DictionaryAttributeType attributeType();

    String attributeValue();

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
    class Builder extends ImmutableDictionaryValueAttribute.Builder implements BaseDTO.Builder {}
}
