package com.cagst.bom.imports;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@JsonDeserialize(builder = ImportCriteria.Builder.class)
@JsonPropertyOrder({
    "importType"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface ImportCriteria {
    @Value.Default
    default ImportType importType() {
        return ImportType.ADD_ONLY;
    }

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableImportCriteria.Builder {}
}
