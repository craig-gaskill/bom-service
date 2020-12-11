package com.cagst.bom.imports;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link ImportCriteria}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ImportCriteria.Builder()}.
 */
@Generated(from = "ImportCriteria", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableImportCriteria implements ImportCriteria {
  private final ImportType importType;

  private ImmutableImportCriteria(ImmutableImportCriteria.Builder builder) {
    this.importType = builder.importType != null
        ? builder.importType
        : Objects.requireNonNull(ImportCriteria.super.importType(), "importType");
  }

  /**
   * @return The value of the {@code importType} attribute
   */
  @JsonProperty("importType")
  @Override
  public ImportType importType() {
    return importType;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableImportCriteria} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableImportCriteria
        && equalTo((ImmutableImportCriteria) another);
  }

  private boolean equalTo(ImmutableImportCriteria another) {
    return importType.equals(another.importType);
  }

  /**
   * Computes a hash code from attributes: {@code importType}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + importType.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code ImportCriteria} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "ImportCriteria{"
        + "importType=" + importType
        + "}";
  }

  /**
   * Builds instances of type {@link ImportCriteria ImportCriteria}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "ImportCriteria", generator = "Immutables")
  @JsonPropertyOrder("importType")
  public static class Builder {
    private ImportType importType;

    /**
     * Creates a builder for {@link ImportCriteria ImportCriteria} instances.
     * <pre>
     * new ImportCriteria.Builder()
     *    .importType(com.cagst.bom.imports.ImportType) // optional {@link ImportCriteria#importType() importType}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof ImportCriteria.Builder)) {
        throw new UnsupportedOperationException("Use: new ImportCriteria.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code ImportCriteria} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final ImportCriteria.Builder from(ImportCriteria instance) {
      Objects.requireNonNull(instance, "instance");
      importType(instance.importType());
      return (ImportCriteria.Builder) this;
    }

    /**
     * Initializes the value for the {@link ImportCriteria#importType() importType} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link ImportCriteria#importType() importType}.</em>
     * @param importType The value for importType 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("importType")
    public final ImportCriteria.Builder importType(ImportType importType) {
      this.importType = Objects.requireNonNull(importType, "importType");
      return (ImportCriteria.Builder) this;
    }

    /**
     * Builds a new {@link ImportCriteria ImportCriteria}.
     * @return An immutable instance of ImportCriteria
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ImportCriteria build() {
      return new ImmutableImportCriteria(this);
    }
  }
}
