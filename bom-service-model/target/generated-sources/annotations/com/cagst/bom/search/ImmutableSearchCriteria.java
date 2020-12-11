package com.cagst.bom.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link SearchCriteria}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new SearchCriteria.Builder()}.
 */
@Generated(from = "SearchCriteria", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableSearchCriteria implements SearchCriteria {
  private final @Nullable Integer start;
  private final @Nullable Integer limit;
  private final @Nullable String searchText;
  private final boolean includeInactive;

  private ImmutableSearchCriteria(ImmutableSearchCriteria.Builder builder) {
    this.start = builder.start;
    this.limit = builder.limit;
    this.searchText = builder.searchText;
    this.includeInactive = builder.includeInactiveIsSet()
        ? builder.includeInactive
        : SearchCriteria.super.includeInactive();
  }

  /**
   * @return The value of the {@code start} attribute
   */
  @JsonProperty("start")
  @Override
  public @Nullable Integer start() {
    return start;
  }

  /**
   * @return The value of the {@code limit} attribute
   */
  @JsonProperty("limit")
  @Override
  public @Nullable Integer limit() {
    return limit;
  }

  /**
   * @return The value of the {@code searchText} attribute
   */
  @JsonProperty("searchText")
  @Override
  public @Nullable String searchText() {
    return searchText;
  }

  /**
   * @return The value of the {@code includeInactive} attribute
   */
  @JsonProperty("includeInactive")
  @Override
  public boolean includeInactive() {
    return includeInactive;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableSearchCriteria} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableSearchCriteria
        && equalTo((ImmutableSearchCriteria) another);
  }

  private boolean equalTo(ImmutableSearchCriteria another) {
    return Objects.equals(start, another.start)
        && Objects.equals(limit, another.limit)
        && Objects.equals(searchText, another.searchText)
        && includeInactive == another.includeInactive;
  }

  /**
   * Computes a hash code from attributes: {@code start}, {@code limit}, {@code searchText}, {@code includeInactive}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Objects.hashCode(start);
    h += (h << 5) + Objects.hashCode(limit);
    h += (h << 5) + Objects.hashCode(searchText);
    h += (h << 5) + Boolean.hashCode(includeInactive);
    return h;
  }

  /**
   * Prints the immutable value {@code SearchCriteria} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "SearchCriteria{"
        + "start=" + start
        + ", limit=" + limit
        + ", searchText=" + searchText
        + ", includeInactive=" + includeInactive
        + "}";
  }

  /**
   * Builds instances of type {@link SearchCriteria SearchCriteria}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "SearchCriteria", generator = "Immutables")
  @JsonPropertyOrder({"start", "limit", "searchText", "includeInactive"})
  public static class Builder {
    private static final long OPT_BIT_INCLUDE_INACTIVE = 0x1L;
    private long optBits;

    private Integer start;
    private Integer limit;
    private String searchText;
    private boolean includeInactive;

    /**
     * Creates a builder for {@link SearchCriteria SearchCriteria} instances.
     * <pre>
     * new SearchCriteria.Builder()
     *    .start(Integer | null) // nullable {@link SearchCriteria#start() start}
     *    .limit(Integer | null) // nullable {@link SearchCriteria#limit() limit}
     *    .searchText(String | null) // nullable {@link SearchCriteria#searchText() searchText}
     *    .includeInactive(boolean) // optional {@link SearchCriteria#includeInactive() includeInactive}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof SearchCriteria.Builder)) {
        throw new UnsupportedOperationException("Use: new SearchCriteria.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code SearchCriteria} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final SearchCriteria.Builder from(SearchCriteria instance) {
      Objects.requireNonNull(instance, "instance");
      Integer startValue = instance.start();
      if (startValue != null) {
        start(startValue);
      }
      Integer limitValue = instance.limit();
      if (limitValue != null) {
        limit(limitValue);
      }
      String searchTextValue = instance.searchText();
      if (searchTextValue != null) {
        searchText(searchTextValue);
      }
      includeInactive(instance.includeInactive());
      return (SearchCriteria.Builder) this;
    }

    /**
     * Initializes the value for the {@link SearchCriteria#start() start} attribute.
     * @param start The value for start (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("start")
    public final SearchCriteria.Builder start(@Nullable Integer start) {
      this.start = start;
      return (SearchCriteria.Builder) this;
    }

    /**
     * Initializes the value for the {@link SearchCriteria#limit() limit} attribute.
     * @param limit The value for limit (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("limit")
    public final SearchCriteria.Builder limit(@Nullable Integer limit) {
      this.limit = limit;
      return (SearchCriteria.Builder) this;
    }

    /**
     * Initializes the value for the {@link SearchCriteria#searchText() searchText} attribute.
     * @param searchText The value for searchText (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("searchText")
    public final SearchCriteria.Builder searchText(@Nullable String searchText) {
      this.searchText = searchText;
      return (SearchCriteria.Builder) this;
    }

    /**
     * Initializes the value for the {@link SearchCriteria#includeInactive() includeInactive} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SearchCriteria#includeInactive() includeInactive}.</em>
     * @param includeInactive The value for includeInactive 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("includeInactive")
    public final SearchCriteria.Builder includeInactive(boolean includeInactive) {
      this.includeInactive = includeInactive;
      optBits |= OPT_BIT_INCLUDE_INACTIVE;
      return (SearchCriteria.Builder) this;
    }

    /**
     * Builds a new {@link SearchCriteria SearchCriteria}.
     * @return An immutable instance of SearchCriteria
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public SearchCriteria build() {
      return new ImmutableSearchCriteria(this);
    }

    private boolean includeInactiveIsSet() {
      return (optBits & OPT_BIT_INCLUDE_INACTIVE) != 0;
    }
  }
}
