package com.cagst.bom.tenant.feature;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link TenantFeature}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new TenantFeature.Builder()}.
 */
@Generated(from = "TenantFeature", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableTenantFeature implements TenantFeature {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long tenantFeatureId;
  private final String meaning;
  private final @Nullable String display;
  private final @Nullable String description;

  private ImmutableTenantFeature(ImmutableTenantFeature.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.tenantFeatureId = builder.tenantFeatureId;
    this.meaning = builder.meaning;
    this.display = builder.display;
    this.description = builder.description;
    if (builder.createdDateTime != null) {
      initShim.createdDateTime(builder.createdDateTime);
    }
    if (builder.activeIsSet()) {
      initShim.active(builder.active);
    }
    if (builder.updatedDateTime != null) {
      initShim.updatedDateTime(builder.updatedDateTime);
    }
    if (builder.updatedCountIsSet()) {
      initShim.updatedCount(builder.updatedCount);
    }
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "TenantFeature", generator = "Immutables")
  private final class InitShim {
    private byte createdDateTimeBuildStage = STAGE_UNINITIALIZED;
    private OffsetDateTime createdDateTime;

    OffsetDateTime createdDateTime() {
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (createdDateTimeBuildStage == STAGE_UNINITIALIZED) {
        createdDateTimeBuildStage = STAGE_INITIALIZING;
        this.createdDateTime = Objects.requireNonNull(createdDateTimeInitialize(), "createdDateTime");
        createdDateTimeBuildStage = STAGE_INITIALIZED;
      }
      return this.createdDateTime;
    }

    void createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = createdDateTime;
      createdDateTimeBuildStage = STAGE_INITIALIZED;
    }

    private byte activeBuildStage = STAGE_UNINITIALIZED;
    private boolean active;

    boolean active() {
      if (activeBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (activeBuildStage == STAGE_UNINITIALIZED) {
        activeBuildStage = STAGE_INITIALIZING;
        this.active = activeInitialize();
        activeBuildStage = STAGE_INITIALIZED;
      }
      return this.active;
    }

    void active(boolean active) {
      this.active = active;
      activeBuildStage = STAGE_INITIALIZED;
    }

    private byte updatedDateTimeBuildStage = STAGE_UNINITIALIZED;
    private OffsetDateTime updatedDateTime;

    OffsetDateTime updatedDateTime() {
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (updatedDateTimeBuildStage == STAGE_UNINITIALIZED) {
        updatedDateTimeBuildStage = STAGE_INITIALIZING;
        this.updatedDateTime = Objects.requireNonNull(updatedDateTimeInitialize(), "updatedDateTime");
        updatedDateTimeBuildStage = STAGE_INITIALIZED;
      }
      return this.updatedDateTime;
    }

    void updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = updatedDateTime;
      updatedDateTimeBuildStage = STAGE_INITIALIZED;
    }

    private byte updatedCountBuildStage = STAGE_UNINITIALIZED;
    private long updatedCount;

    long updatedCount() {
      if (updatedCountBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (updatedCountBuildStage == STAGE_UNINITIALIZED) {
        updatedCountBuildStage = STAGE_INITIALIZING;
        this.updatedCount = updatedCountInitialize();
        updatedCountBuildStage = STAGE_INITIALIZED;
      }
      return this.updatedCount;
    }

    void updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      updatedCountBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      return "Cannot build TenantFeature, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return TenantFeature.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return TenantFeature.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return TenantFeature.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return TenantFeature.super.updatedCount();
  }

  /**
   * @return The value of the {@code createdId} attribute
   */
  @JsonProperty("createdId")
  @Override
  public @Nullable Long createdId() {
    return createdId;
  }

  /**
   * @return The value of the {@code createdDateTime} attribute
   */
  @JsonProperty("createdDateTime")
  @Override
  public OffsetDateTime createdDateTime() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.createdDateTime()
        : this.createdDateTime;
  }

  /**
   * @return The value of the {@code active} attribute
   */
  @JsonProperty("active")
  @Override
  public boolean active() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.active()
        : this.active;
  }

  /**
   * @return The value of the {@code updatedId} attribute
   */
  @JsonProperty("updatedId")
  @Override
  public @Nullable Long updatedId() {
    return updatedId;
  }

  /**
   * @return The value of the {@code updatedDateTime} attribute
   */
  @JsonProperty("updatedDateTime")
  @Override
  public OffsetDateTime updatedDateTime() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.updatedDateTime()
        : this.updatedDateTime;
  }

  /**
   * @return The value of the {@code updatedCount} attribute
   */
  @JsonProperty("updatedCount")
  @Override
  public long updatedCount() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.updatedCount()
        : this.updatedCount;
  }

  /**
   * @return The value of the {@code tenantFeatureId} attribute
   */
  @JsonProperty("tenantFeatureId")
  @Override
  public @Nullable Long tenantFeatureId() {
    return tenantFeatureId;
  }

  /**
   * @return The value of the {@code meaning} attribute
   */
  @JsonProperty("meaning")
  @Override
  public String meaning() {
    return meaning;
  }

  /**
   * @return The value of the {@code display} attribute
   */
  @JsonProperty("display")
  @Override
  public @Nullable String display() {
    return display;
  }

  /**
   * @return The value of the {@code description} attribute
   */
  @JsonProperty("description")
  @Override
  public @Nullable String description() {
    return description;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableTenantFeature} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableTenantFeature
        && equalTo((ImmutableTenantFeature) another);
  }

  private boolean equalTo(ImmutableTenantFeature another) {
    return active == another.active
        && Objects.equals(tenantFeatureId, another.tenantFeatureId)
        && meaning.equals(another.meaning)
        && Objects.equals(display, another.display)
        && Objects.equals(description, another.description);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code tenantFeatureId}, {@code meaning}, {@code display}, {@code description}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + Objects.hashCode(tenantFeatureId);
    h += (h << 5) + meaning.hashCode();
    h += (h << 5) + Objects.hashCode(display);
    h += (h << 5) + Objects.hashCode(description);
    return h;
  }

  /**
   * Prints the immutable value {@code TenantFeature} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "TenantFeature{"
        + "active=" + active
        + ", tenantFeatureId=" + tenantFeatureId
        + ", meaning=" + meaning
        + ", display=" + display
        + ", description=" + description
        + "}";
  }

  /**
   * Builds instances of type {@link TenantFeature TenantFeature}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "TenantFeature", generator = "Immutables")
  @JsonPropertyOrder({"tenantFeatureId", "meaning", "display", "description", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_MEANING = 0x1L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private long initBits = 0x1L;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long tenantFeatureId;
    private String meaning;
    private String display;
    private String description;

    /**
     * Creates a builder for {@link TenantFeature TenantFeature} instances.
     * <pre>
     * new TenantFeature.Builder()
     *    .createdId(Long | null) // nullable {@link TenantFeature#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link TenantFeature#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link TenantFeature#active() active}
     *    .updatedId(Long | null) // nullable {@link TenantFeature#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link TenantFeature#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link TenantFeature#updatedCount() updatedCount}
     *    .tenantFeatureId(Long | null) // nullable {@link TenantFeature#tenantFeatureId() tenantFeatureId}
     *    .meaning(String) // required {@link TenantFeature#meaning() meaning}
     *    .display(String | null) // nullable {@link TenantFeature#display() display}
     *    .description(String | null) // nullable {@link TenantFeature#description() description}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof TenantFeature.Builder)) {
        throw new UnsupportedOperationException("Use: new TenantFeature.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.tenant.feature.TenantFeature} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final TenantFeature.Builder from(TenantFeature instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (TenantFeature.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final TenantFeature.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (TenantFeature.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof TenantFeature) {
        TenantFeature instance = (TenantFeature) object;
        Long tenantFeatureIdValue = instance.tenantFeatureId();
        if (tenantFeatureIdValue != null) {
          tenantFeatureId(tenantFeatureIdValue);
        }
        String descriptionValue = instance.description();
        if (descriptionValue != null) {
          description(descriptionValue);
        }
        meaning(instance.meaning());
        String displayValue = instance.display();
        if (displayValue != null) {
          display(displayValue);
        }
      }
      if (object instanceof BaseDTO) {
        BaseDTO instance = (BaseDTO) object;
        Long createdIdValue = instance.createdId();
        if (createdIdValue != null) {
          createdId(createdIdValue);
        }
        createdDateTime(instance.createdDateTime());
        active(instance.active());
        Long updatedIdValue = instance.updatedId();
        if (updatedIdValue != null) {
          updatedId(updatedIdValue);
        }
        updatedCount(instance.updatedCount());
        updatedDateTime(instance.updatedDateTime());
      }
    }

    /**
     * Initializes the value for the {@link TenantFeature#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final TenantFeature.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link TenantFeature#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final TenantFeature.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link TenantFeature#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final TenantFeature.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final TenantFeature.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link TenantFeature#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final TenantFeature.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link TenantFeature#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final TenantFeature.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#tenantFeatureId() tenantFeatureId} attribute.
     * @param tenantFeatureId The value for tenantFeatureId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("tenantFeatureId")
    public final TenantFeature.Builder tenantFeatureId(@Nullable Long tenantFeatureId) {
      this.tenantFeatureId = tenantFeatureId;
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#meaning() meaning} attribute.
     * @param meaning The value for meaning 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("meaning")
    public final TenantFeature.Builder meaning(String meaning) {
      this.meaning = Objects.requireNonNull(meaning, "meaning");
      initBits &= ~INIT_BIT_MEANING;
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#display() display} attribute.
     * @param display The value for display (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("display")
    public final TenantFeature.Builder display(@Nullable String display) {
      this.display = display;
      return (TenantFeature.Builder) this;
    }

    /**
     * Initializes the value for the {@link TenantFeature#description() description} attribute.
     * @param description The value for description (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("description")
    public final TenantFeature.Builder description(@Nullable String description) {
      this.description = description;
      return (TenantFeature.Builder) this;
    }

    /**
     * Builds a new {@link TenantFeature TenantFeature}.
     * @return An immutable instance of TenantFeature
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public TenantFeature build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableTenantFeature(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_MEANING) != 0) attributes.add("meaning");
      return "Cannot build TenantFeature, some of required attributes are not set " + attributes;
    }
  }
}
