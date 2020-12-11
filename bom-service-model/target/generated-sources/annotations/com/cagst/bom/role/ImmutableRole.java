package com.cagst.bom.role;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.role.permission.RolePermission;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link Role}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Role.Builder()}.
 */
@Generated(from = "Role", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableRole implements Role {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long roleId;
  private final String name;
  private final boolean fullAccess;
  private final @Nullable Collection<RolePermission> permissions;

  private ImmutableRole(ImmutableRole.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.roleId = builder.roleId;
    this.name = builder.name;
    this.permissions = builder.permissions;
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
    if (builder.fullAccessIsSet()) {
      initShim.fullAccess(builder.fullAccess);
    }
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.fullAccess = initShim.fullAccess();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "Role", generator = "Immutables")
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

    private byte fullAccessBuildStage = STAGE_UNINITIALIZED;
    private boolean fullAccess;

    boolean fullAccess() {
      if (fullAccessBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (fullAccessBuildStage == STAGE_UNINITIALIZED) {
        fullAccessBuildStage = STAGE_INITIALIZING;
        this.fullAccess = fullAccessInitialize();
        fullAccessBuildStage = STAGE_INITIALIZED;
      }
      return this.fullAccess;
    }

    void fullAccess(boolean fullAccess) {
      this.fullAccess = fullAccess;
      fullAccessBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      if (fullAccessBuildStage == STAGE_INITIALIZING) attributes.add("fullAccess");
      return "Cannot build Role, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return Role.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return Role.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return Role.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return Role.super.updatedCount();
  }

  private boolean fullAccessInitialize() {
    return Role.super.fullAccess();
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
   * @return The value of the {@code roleId} attribute
   */
  @JsonProperty("roleId")
  @Override
  public @Nullable Long roleId() {
    return roleId;
  }

  /**
   * @return The value of the {@code name} attribute
   */
  @JsonProperty("name")
  @Override
  public String name() {
    return name;
  }

  /**
   * @return The value of the {@code fullAccess} attribute
   */
  @JsonProperty("fullAccess")
  @Override
  public boolean fullAccess() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.fullAccess()
        : this.fullAccess;
  }

  /**
   * @return The value of the {@code permissions} attribute
   */
  @JsonProperty("permissions")
  @Override
  public @Nullable Collection<RolePermission> permissions() {
    return permissions;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableRole} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableRole
        && equalTo((ImmutableRole) another);
  }

  private boolean equalTo(ImmutableRole another) {
    return active == another.active
        && name.equals(another.name)
        && fullAccess == another.fullAccess
        && Objects.equals(permissions, another.permissions);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code name}, {@code fullAccess}, {@code permissions}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + name.hashCode();
    h += (h << 5) + Boolean.hashCode(fullAccess);
    h += (h << 5) + Objects.hashCode(permissions);
    return h;
  }

  /**
   * Prints the immutable value {@code Role} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Role{"
        + "active=" + active
        + ", name=" + name
        + ", fullAccess=" + fullAccess
        + ", permissions=" + permissions
        + "}";
  }

  /**
   * Builds instances of type {@link Role Role}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Role", generator = "Immutables")
  @JsonPropertyOrder({"roleId", "name", "fullAccess", "permissions", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_NAME = 0x1L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private static final long OPT_BIT_FULL_ACCESS = 0x4L;
    private long initBits = 0x1L;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long roleId;
    private String name;
    private boolean fullAccess;
    private Collection<RolePermission> permissions;

    /**
     * Creates a builder for {@link Role Role} instances.
     * <pre>
     * new Role.Builder()
     *    .createdId(Long | null) // nullable {@link Role#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link Role#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link Role#active() active}
     *    .updatedId(Long | null) // nullable {@link Role#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link Role#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link Role#updatedCount() updatedCount}
     *    .roleId(Long | null) // nullable {@link Role#roleId() roleId}
     *    .name(String) // required {@link Role#name() name}
     *    .fullAccess(boolean) // optional {@link Role#fullAccess() fullAccess}
     *    .permissions(Collection&amp;lt;com.cagst.bom.role.permission.RolePermission&amp;gt; | null) // nullable {@link Role#permissions() permissions}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof Role.Builder)) {
        throw new UnsupportedOperationException("Use: new Role.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.role.Role} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Role.Builder from(Role instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Role.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Role.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Role.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof Role) {
        Role instance = (Role) object;
        name(instance.name());
        fullAccess(instance.fullAccess());
        Long roleIdValue = instance.roleId();
        if (roleIdValue != null) {
          roleId(roleIdValue);
        }
        Collection<RolePermission> permissionsValue = instance.permissions();
        if (permissionsValue != null) {
          permissions(permissionsValue);
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
     * Initializes the value for the {@link Role#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final Role.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Role#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final Role.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Role#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final Role.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final Role.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Role#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final Role.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Role#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final Role.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#roleId() roleId} attribute.
     * @param roleId The value for roleId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("roleId")
    public final Role.Builder roleId(@Nullable Long roleId) {
      this.roleId = roleId;
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#name() name} attribute.
     * @param name The value for name 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("name")
    public final Role.Builder name(String name) {
      this.name = Objects.requireNonNull(name, "name");
      initBits &= ~INIT_BIT_NAME;
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#fullAccess() fullAccess} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Role#fullAccess() fullAccess}.</em>
     * @param fullAccess The value for fullAccess 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("fullAccess")
    public final Role.Builder fullAccess(boolean fullAccess) {
      this.fullAccess = fullAccess;
      optBits |= OPT_BIT_FULL_ACCESS;
      return (Role.Builder) this;
    }

    /**
     * Initializes the value for the {@link Role#permissions() permissions} attribute.
     * @param permissions The value for permissions (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("permissions")
    public final Role.Builder permissions(@Nullable Collection<RolePermission> permissions) {
      this.permissions = permissions;
      return (Role.Builder) this;
    }

    /**
     * Builds a new {@link Role Role}.
     * @return An immutable instance of Role
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public Role build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableRole(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private boolean fullAccessIsSet() {
      return (optBits & OPT_BIT_FULL_ACCESS) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_NAME) != 0) attributes.add("name");
      return "Cannot build Role, some of required attributes are not set " + attributes;
    }
  }
}
