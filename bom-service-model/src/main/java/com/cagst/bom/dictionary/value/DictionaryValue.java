package com.cagst.bom.dictionary.value;

import java.util.List;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.dictionary.value.attribute.DictionaryValueAttribute;
import com.cagst.bom.dictionary.value.mapping.DictionaryValueMapping;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = DictionaryValue.Builder.class)
@JsonPropertyOrder({
    "dictionaryValueId",
    "display",
    "meaning",
    "description",
    "viewable",
    "editable",
    "deletable",
    "sortOrder",
    "mappings",
    "attributes",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface DictionaryValue extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long dictionaryValueId();

    String display();

    String meaning();

    @Nullable
    String description();

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

    @Nullable
    List<DictionaryValueMapping> mappings();

    @Nullable
    List<DictionaryValueAttribute> attributes();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableDictionaryValue.Builder implements BaseDTO.Builder {}
}
