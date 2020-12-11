package com.cagst.bom.dictionary;

import java.util.List;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.dictionary.value.DictionaryValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = Dictionary.Builder.class)
@JsonPropertyOrder({
    "dictionaryId",
    "display",
    "meaning",
    "description",
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
public interface Dictionary extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long dictionaryId();

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
    List<DictionaryValue> values();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableDictionary.Builder implements BaseDTO.Builder {}
}
