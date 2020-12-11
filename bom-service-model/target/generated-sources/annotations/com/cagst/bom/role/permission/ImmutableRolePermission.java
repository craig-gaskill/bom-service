package com.cagst.bom.role.permission;

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
 * Immutable implementation of {@link RolePermission}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new RolePermission.Builder()}.
 */
@Generated(from = "RolePermission", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableRolePermission implements RolePermission {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long rolePermissionId;
  private final long permissionId;
  private final String code;
  private final String display;
  private final @Nullable String description;
  private final boolean granted;

  private ImmutableRolePermission(ImmutableRolePermission.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.rolePermissionId = builder.rolePermissionId;
    this.permissionId = builder.permissionId;
    this.code = builder.code;
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
    if (builder.grantedIsSet()) {
      initShim.granted(builder.granted);
    }
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.granted = initShim.granted();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "RolePermission", generator = "Immutables")
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

    private byte grantedBuildStage = STAGE_UNINITIALIZED;
    private boolean granted;

    boolean granted() {
      if (grantedBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (grantedBuildStage == STAGE_UNINITIALIZED) {
        grantedBuildStage = STAGE_INITIALIZING;
        this.granted = grantedInitialize();
        grantedBuildStage = STAGE_INITIALIZED;
      }
      return this.granted;
    }

    void granted(boolean granted) {
      this.granted = granted;
      grantedBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      if (grantedBuildStage == STAGE_INITIALIZING) attributes.add("granted");
      return "Cannot build RolePermission, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return RolePermission.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return RolePermission.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return RolePermission.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return RolePermission.super.updatedCount();
  }

  private boolean grantedInitialize() {
    return RolePermission.super.granted();
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
   * @return The value of the {@code rolePermissionId} attribute
   */
  @JsonProperty("rolePermissionId")
  @Override
  public @Nullable Long rolePermissionId() {
    return rolePermissionId;
  }

  /**
   * @return The value of the {@code permissionId} attribute
   */
  @JsonProperty("permissionId")
  @Override
  public long permissionId() {
    return permissionId;
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
   * @return The value of the {@code display} attribute
   */
  @JsonProperty("display")
  @Override
  public String display() {
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
   * @return The value of the {@code granted} attribute
   */
  @JsonProperty("granted")
  @Override
  public boolean granted() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.granted()
        : this.granted;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableRolePermission} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableRolePermission
        && equalTo((ImmutableRolePermission) another);
  }

  private boolean equalTo(ImmutableRolePermission another) {
    return active == another.active
        && permissionId == another.permissionId
        && code.equals(another.code)
        && display.equals(another.display)
        && Objects.equals(description, another.description)
        && granted == another.granted;
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code permissionId}, {@code code}, {@code display}, {@code description}, {@code granted}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + Long.hashCode(permissionId);
    h += (h << 5) + code.hashCode();
    h += (h << 5) + display.hashCode();
    h += (h << 5) + Objects.hashCode(description);
    h += (h << 5) + Boolean.hashCode(granted);
    return h;
  }

  /**
   * Prints the immutable value {@code RolePermission} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "RolePermission{"
        + "active=" + active
        + ", permissionId=" + permissionId
        + ", code=" + code
        + ", display=" + display
        + ", description=" + description
        + ", granted=" + granted
        + "}";
  }

  /**
   * Builds instances of type {@link RolePermission RolePermission}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "RolePermission", generator = "Immutables")
  @JsonPropertyOrder({"rolePermissionId", "permissionId", "code", "display", "description", "granted", "display", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_PERMISSION_ID = 0x1L;
    private static final long INIT_BIT_CODE = 0x2L;
    private static final long INIT_BIT_DISPLAY = 0x4L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private static final long OPT_BIT_GRANTED = 0x4L;
    private long initBits = 0x7L;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long rolePermissionId;
    private long permissionId;
    private String code;
    private String display;
    private String description;
    private boolean granted;

    /**
     * Creates a builder for {@link RolePermission RolePermission} instances.
     * <pre>
     * new RolePermission.Builder()
     *    .createdId(Long | null) // nullable {@link RolePermission#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link RolePermission#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link RolePermission#active() active}
     *    .updatedId(Long | null) // nullable {@link RolePermission#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link RolePermission#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link RolePermission#updatedCount() updatedCount}
     *    .rolePermissionId(Long | null) // nullable {@link RolePermission#rolePermissionId() rolePermissionId}
     *    .permissionId(long) // required {@link RolePermission#permissionId() permissionId}
     *    .code(String) // required {@link RolePermission#code() code}
     *    .display(String) // required {@link RolePermission#display() display}
     *    .description(String | null) // nullable {@link RolePermission#description() description}
     *    .granted(boolean) // optional {@link RolePermission#granted() granted}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof RolePermission.Builder)) {
        throw new UnsupportedOperationException("Use: new RolePermission.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.role.permission.RolePermission} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final RolePermission.Builder from(RolePermission instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (RolePermission.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final RolePermission.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (RolePermission.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof RolePermission) {
        RolePermission instance = (RolePermission) object;
        Long rolePermissionIdValue = instance.rolePermissionId();
        if (rolePermissionIdValue != null) {
          rolePermissionId(rolePermissionIdValue);
        }
        permissionId(instance.permissionId());
        String descriptionValue = instance.description();
        if (descriptionValue != null) {
          description(descriptionValue);
        }
        code(instance.code());
        display(instance.display());
        granted(instance.granted());
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
     * Initializes the value for the {@link RolePermission#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final RolePermission.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link RolePermission#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final RolePermission.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link RolePermission#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final RolePermission.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final RolePermission.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link RolePermission#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final RolePermission.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link RolePermission#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final RolePermission.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#rolePermissionId() rolePermissionId} attribute.
     * @param rolePermissionId The value for rolePermissionId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("rolePermissionId")
    public final RolePermission.Builder rolePermissionId(@Nullable Long rolePermissionId) {
      this.rolePermissionId = rolePermissionId;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#permissionId() permissionId} attribute.
     * @param permissionId The value for permissionId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("permissionId")
    public final RolePermission.Builder permissionId(long permissionId) {
      this.permissionId = permissionId;
      initBits &= ~INIT_BIT_PERMISSION_ID;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#code() code} attribute.
     * @param code The value for code 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("code")
    public final RolePermission.Builder code(String code) {
      this.code = Objects.requireNonNull(code, "code");
      initBits &= ~INIT_BIT_CODE;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#display() display} attribute.
     * @param display The value for display 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("display")
    public final RolePermission.Builder display(String display) {
      this.display = Objects.requireNonNull(display, "display");
      initBits &= ~INIT_BIT_DISPLAY;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#description() description} attribute.
     * @param description The value for description (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("description")
    public final RolePermission.Builder description(@Nullable String description) {
      this.description = description;
      return (RolePermission.Builder) this;
    }

    /**
     * Initializes the value for the {@link RolePermission#granted() granted} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link RolePermission#granted() granted}.</em>
     * @param granted The value for granted 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("granted")
    public final RolePermission.Builder granted(boolean granted) {
      this.granted = granted;
      optBits |= OPT_BIT_GRANTED;
      return (RolePermission.Builder) this;
    }

    /**
     * Builds a new {@link RolePermission RolePermission}.
     * @return An immutable instance of RolePermission
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public RolePermission build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableRolePermission(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private boolean grantedIsSet() {
      return (optBits & OPT_BIT_GRANTED) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_PERMISSION_ID) != 0) attributes.add("permissionId");
      if ((initBits & INIT_BIT_CODE) != 0) attributes.add("code");
      if ((initBits & INIT_BIT_DISPLAY) != 0) attributes.add("display");
      return "Cannot build RolePermission, some of required attributes are not set " + attributes;
    }
  }
}
