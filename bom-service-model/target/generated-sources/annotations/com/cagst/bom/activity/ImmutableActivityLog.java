package com.cagst.bom.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link ActivityLog}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ActivityLog.Builder()}.
 */
@Generated(from = "ActivityLog", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableActivityLog implements ActivityLog {
  private final @Nullable Long activityLogId;
  private final @Nullable OffsetDateTime activityDateTime;
  private final ActivityCategory activityCategory;
  private final @Nullable String activitySubCategory;
  private final @Nullable String activityContext;
  private final @Nullable Integer tenantId;
  private final @Nullable Long instigatingUserId;

  private ImmutableActivityLog(ImmutableActivityLog.Builder builder) {
    this.activityLogId = builder.activityLogId;
    this.activityDateTime = builder.activityDateTime;
    this.activityCategory = builder.activityCategory;
    this.activitySubCategory = builder.activitySubCategory;
    this.activityContext = builder.activityContext;
    this.tenantId = builder.tenantId;
    this.instigatingUserId = builder.instigatingUserId;
  }

  /**
   * @return The value of the {@code activityLogId} attribute
   */
  @JsonProperty("activityLogId")
  @Override
  public @Nullable Long activityLogId() {
    return activityLogId;
  }

  /**
   * @return The value of the {@code activityDateTime} attribute
   */
  @JsonProperty("activityDateTime")
  @Override
  public @Nullable OffsetDateTime activityDateTime() {
    return activityDateTime;
  }

  /**
   * @return The value of the {@code activityCategory} attribute
   */
  @JsonProperty("activityCategory")
  @Override
  public ActivityCategory activityCategory() {
    return activityCategory;
  }

  /**
   * @return The value of the {@code activitySubCategory} attribute
   */
  @JsonProperty("activitySubCategory")
  @Override
  public @Nullable String activitySubCategory() {
    return activitySubCategory;
  }

  /**
   * @return The value of the {@code activityContext} attribute
   */
  @JsonProperty("activityContext")
  @Override
  public @Nullable String activityContext() {
    return activityContext;
  }

  /**
   * @return The value of the {@code tenantId} attribute
   */
  @JsonProperty("tenantId")
  @Override
  public @Nullable Integer tenantId() {
    return tenantId;
  }

  /**
   * @return The value of the {@code instigatingUserId} attribute
   */
  @JsonProperty("instigatingUserId")
  @Override
  public @Nullable Long instigatingUserId() {
    return instigatingUserId;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableActivityLog} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableActivityLog
        && equalTo((ImmutableActivityLog) another);
  }

  private boolean equalTo(ImmutableActivityLog another) {
    return Objects.equals(activityDateTime, another.activityDateTime)
        && activityCategory.equals(another.activityCategory)
        && Objects.equals(activitySubCategory, another.activitySubCategory)
        && Objects.equals(activityContext, another.activityContext)
        && Objects.equals(tenantId, another.tenantId)
        && Objects.equals(instigatingUserId, another.instigatingUserId);
  }

  /**
   * Computes a hash code from attributes: {@code activityDateTime}, {@code activityCategory}, {@code activitySubCategory}, {@code activityContext}, {@code tenantId}, {@code instigatingUserId}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Objects.hashCode(activityDateTime);
    h += (h << 5) + activityCategory.hashCode();
    h += (h << 5) + Objects.hashCode(activitySubCategory);
    h += (h << 5) + Objects.hashCode(activityContext);
    h += (h << 5) + Objects.hashCode(tenantId);
    h += (h << 5) + Objects.hashCode(instigatingUserId);
    return h;
  }

  /**
   * Prints the immutable value {@code ActivityLog} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "ActivityLog{"
        + "activityDateTime=" + activityDateTime
        + ", activityCategory=" + activityCategory
        + ", activitySubCategory=" + activitySubCategory
        + ", activityContext=" + activityContext
        + ", tenantId=" + tenantId
        + ", instigatingUserId=" + instigatingUserId
        + "}";
  }

  /**
   * Builds instances of type {@link ActivityLog ActivityLog}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "ActivityLog", generator = "Immutables")
  @JsonPropertyOrder({"activityLogId", "activityDateTime", "activitySubCategory", "activityContext", "tenantId", "instigatingUserId"})
  public static class Builder {
    private static final long INIT_BIT_ACTIVITY_CATEGORY = 0x1L;
    private long initBits = 0x1L;

    private Long activityLogId;
    private OffsetDateTime activityDateTime;
    private ActivityCategory activityCategory;
    private String activitySubCategory;
    private String activityContext;
    private Integer tenantId;
    private Long instigatingUserId;

    /**
     * Creates a builder for {@link ActivityLog ActivityLog} instances.
     * <pre>
     * new ActivityLog.Builder()
     *    .activityLogId(Long | null) // nullable {@link ActivityLog#activityLogId() activityLogId}
     *    .activityDateTime(java.time.OffsetDateTime | null) // nullable {@link ActivityLog#activityDateTime() activityDateTime}
     *    .activityCategory(com.cagst.bom.activity.ActivityCategory) // required {@link ActivityLog#activityCategory() activityCategory}
     *    .activitySubCategory(String | null) // nullable {@link ActivityLog#activitySubCategory() activitySubCategory}
     *    .activityContext(String | null) // nullable {@link ActivityLog#activityContext() activityContext}
     *    .tenantId(Integer | null) // nullable {@link ActivityLog#tenantId() tenantId}
     *    .instigatingUserId(Long | null) // nullable {@link ActivityLog#instigatingUserId() instigatingUserId}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof ActivityLog.Builder)) {
        throw new UnsupportedOperationException("Use: new ActivityLog.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code ActivityLog} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final ActivityLog.Builder from(ActivityLog instance) {
      Objects.requireNonNull(instance, "instance");
      Long activityLogIdValue = instance.activityLogId();
      if (activityLogIdValue != null) {
        activityLogId(activityLogIdValue);
      }
      OffsetDateTime activityDateTimeValue = instance.activityDateTime();
      if (activityDateTimeValue != null) {
        activityDateTime(activityDateTimeValue);
      }
      activityCategory(instance.activityCategory());
      String activitySubCategoryValue = instance.activitySubCategory();
      if (activitySubCategoryValue != null) {
        activitySubCategory(activitySubCategoryValue);
      }
      String activityContextValue = instance.activityContext();
      if (activityContextValue != null) {
        activityContext(activityContextValue);
      }
      Integer tenantIdValue = instance.tenantId();
      if (tenantIdValue != null) {
        tenantId(tenantIdValue);
      }
      Long instigatingUserIdValue = instance.instigatingUserId();
      if (instigatingUserIdValue != null) {
        instigatingUserId(instigatingUserIdValue);
      }
      return (ActivityLog.Builder) this;
    }

    /**
     * Initializes the value for the {@link ActivityLog#activityLogId() activityLogId} attribute.
     * @param activityLogId The value for activityLogId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("activityLogId")
    public final ActivityLog.Builder activityLogId(@Nullable Long activityLogId) {
      this.activityLogId = activityLogId;
      return (ActivityLog.Builder) this;
    }

    /**
     * Initializes the value for the {@link ActivityLog#activityDateTime() activityDateTime} attribute.
     * @param activityDateTime The value for activityDateTime (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("activityDateTime")
    public final ActivityLog.Builder activityDateTime(@Nullable OffsetDateTime activityDateTime) {
      this.activityDateTime = activityDateTime;
      return (ActivityLog.Builder) this;
    }

    /**
     * Initializes the value for the {@link ActivityLog#activityCategory() activityCategory} attribute.
     * @param activityCategory The value for activityCategory 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("activityCategory")
    public final ActivityLog.Builder activityCategory(ActivityCategory activityCategory) {
      this.activityCategory = Objects.requireNonNull(activityCategory, "activityCategory");
      initBits &= ~INIT_BIT_ACTIVITY_CATEGORY;
      return (ActivityLog.Builder) this;
    }

    /**
     * Initializes the value for the {@link ActivityLog#activitySubCategory() activitySubCategory} attribute.
     * @param activitySubCategory The value for activitySubCategory (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("activitySubCategory")
    public final ActivityLog.Builder activitySubCategory(@Nullable String activitySubCategory) {
      this.activitySubCategory = activitySubCategory;
      return (ActivityLog.Builder) this;
    }

    /**
     * Initializes the value for the {@link ActivityLog#activityContext() activityContext} attribute.
     * @param activityContext The value for activityContext (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("activityContext")
    public final ActivityLog.Builder activityContext(@Nullable String activityContext) {
      this.activityContext = activityContext;
      return (ActivityLog.Builder) this;
    }

    /**
     * Initializes the value for the {@link ActivityLog#tenantId() tenantId} attribute.
     * @param tenantId The value for tenantId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("tenantId")
    public final ActivityLog.Builder tenantId(@Nullable Integer tenantId) {
      this.tenantId = tenantId;
      return (ActivityLog.Builder) this;
    }

    /**
     * Initializes the value for the {@link ActivityLog#instigatingUserId() instigatingUserId} attribute.
     * @param instigatingUserId The value for instigatingUserId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("instigatingUserId")
    public final ActivityLog.Builder instigatingUserId(@Nullable Long instigatingUserId) {
      this.instigatingUserId = instigatingUserId;
      return (ActivityLog.Builder) this;
    }

    /**
     * Builds a new {@link ActivityLog ActivityLog}.
     * @return An immutable instance of ActivityLog
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ActivityLog build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableActivityLog(this);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_ACTIVITY_CATEGORY) != 0) attributes.add("activityCategory");
      return "Cannot build ActivityLog, some of required attributes are not set " + attributes;
    }
  }
}
