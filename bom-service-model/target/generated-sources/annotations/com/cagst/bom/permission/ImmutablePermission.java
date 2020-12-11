package com.cagst.bom.permission;

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
 * Immutable implementation of {@link Permission}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Permission.Builder()}.
 */
@Generated(from = "Permission", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutablePermission implements Permission {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long permissionId;
  private final String display;
  private final String code;
  private final @Nullable String description;

  private ImmutablePermission(ImmutablePermission.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.permissionId = builder.permissionId;
    this.display = builder.display;
    this.code = builder.code;
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

  @Generated(from = "Permission", generator = "Immutables")
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
      return "Cannot build Permission, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return Permission.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return Permission.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return Permission.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return Permission.super.updatedCount();
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
   * @return The value of the {@code permissionId} attribute
   */
  @JsonProperty("permissionId")
  @Override
  public @Nullable Long permissionId() {
    return permissionId;
  }

  /**
   * @return The value of the {@code display} attribute
   */
  @JsonProperty("display")
  @Override
  public String display() {
    return display;
  }

  /**
   * @return The value of the {@code code} attribute
   */
  @JsonProperty("code")
  @Override
  public String code() {
    return code;
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
   * This instance is equal to all instances of {@code ImmutablePermission} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutablePermission
        && equalTo((ImmutablePermission) another);
  }

  private boolean equalTo(ImmutablePermission another) {
    return active == another.active
        && display.equals(another.display)
        && code.equals(another.code)
        && Objects.equals(description, another.description);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code display}, {@code code}, {@code description}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + display.hashCode();
    h += (h << 5) + code.hashCode();
    h += (h << 5) + Objects.hashCode(description);
    return h;
  }

  /**
   * Prints the immutable value {@code Permission} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Permission{"
        + "active=" + active
        + ", display=" + display
        + ", code=" + code
        + ", description=" + description
        + "}";
  }

  /**
   * Builds instances of type {@link Permission Permission}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Permission", generator = "Immutables")
  @JsonPropertyOrder({"permissionId", "display", "code", "description", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_DISPLAY = 0x1L;
    private static final long INIT_BIT_CODE = 0x2L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private long initBits = 0x3L;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long permissionId;
    private String display;
    private String code;
    private String description;

    /**
     * Creates a builder for {@link Permission Permission} instances.
     * <pre>
     * new Permission.Builder()
     *    .createdId(Long | null) // nullable {@link Permission#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link Permission#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link Permission#active() active}
     *    .updatedId(Long | null) // nullable {@link Permission#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link Permission#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link Permission#updatedCount() updatedCount}
     *    .permissionId(Long | null) // nullable {@link Permission#permissionId() permissionId}
     *    .display(String) // required {@link Permission#display() display}
     *    .code(String) // required {@link Permission#code() code}
     *    .description(String | null) // nullable {@link Permission#description() description}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof Permission.Builder)) {
        throw new UnsupportedOperationException("Use: new Permission.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.permission.Permission} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Permission.Builder from(Permission instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Permission.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Permission.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Permission.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof Permission) {
        Permission instance = (Permission) object;
        Long permissionIdValue = instance.permissionId();
        if (permissionIdValue != null) {
          permissionId(permissionIdValue);
        }
        String descriptionValue = instance.description();
        if (descriptionValue != null) {
          description(descriptionValue);
        }
        code(instance.code());
        display(instance.display());
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
     * Initializes the value for the {@link Permission#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final Permission.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Permission#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final Permission.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Permission#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final Permission.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final Permission.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Permission#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final Permission.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Permission#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final Permission.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#permissionId() permissionId} attribute.
     * @param permissionId The value for permissionId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("permissionId")
    public final Permission.Builder permissionId(@Nullable Long permissionId) {
      this.permissionId = permissionId;
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#display() display} attribute.
     * @param display The value for display 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("display")
    public final Permission.Builder display(String display) {
      this.display = Objects.requireNonNull(display, "display");
      initBits &= ~INIT_BIT_DISPLAY;
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#code() code} attribute.
     * @param code The value for code 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("code")
    public final Permission.Builder code(String code) {
      this.code = Objects.requireNonNull(code, "code");
      initBits &= ~INIT_BIT_CODE;
      return (Permission.Builder) this;
    }

    /**
     * Initializes the value for the {@link Permission#description() description} attribute.
     * @param description The value for description (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("description")
    public final Permission.Builder description(@Nullable String description) {
      this.description = description;
      return (Permission.Builder) this;
    }

    /**
     * Builds a new {@link Permission Permission}.
     * @return An immutable instance of Permission
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public Permission build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutablePermission(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_DISPLAY) != 0) attributes.add("display");
      if ((initBits & INIT_BIT_CODE) != 0) attributes.add("code");
      return "Cannot build Permission, some of required attributes are not set " + attributes;
    }
  }
}
