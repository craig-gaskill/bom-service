package com.cagst.bom.user.access;

import com.cagst.bom.BaseDTO;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link UserAccess}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new UserAccess.Builder()}.
 */
@Generated(from = "UserAccess", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableUserAccess implements UserAccess {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long userAccessId;
  private final long userId;
  private final int tenantId;
  private final long personId;
  private final boolean defaultIndicator;
  private final @Nullable OffsetDateTime lastLoginDateTime;
  private final @Nullable String lastLoginIp;

  private ImmutableUserAccess(ImmutableUserAccess.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.userAccessId = builder.userAccessId;
    this.userId = builder.userId;
    this.tenantId = builder.tenantId;
    this.personId = builder.personId;
    this.lastLoginDateTime = builder.lastLoginDateTime;
    this.lastLoginIp = builder.lastLoginIp;
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
    if (builder.defaultIndicatorIsSet()) {
      initShim.defaultIndicator(builder.defaultIndicator);
    }
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.defaultIndicator = initShim.defaultIndicator();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "UserAccess", generator = "Immutables")
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

    private byte defaultIndicatorBuildStage = STAGE_UNINITIALIZED;
    private boolean defaultIndicator;

    boolean defaultIndicator() {
      if (defaultIndicatorBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (defaultIndicatorBuildStage == STAGE_UNINITIALIZED) {
        defaultIndicatorBuildStage = STAGE_INITIALIZING;
        this.defaultIndicator = defaultIndicatorInitialize();
        defaultIndicatorBuildStage = STAGE_INITIALIZED;
      }
      return this.defaultIndicator;
    }

    void defaultIndicator(boolean defaultIndicator) {
      this.defaultIndicator = defaultIndicator;
      defaultIndicatorBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      if (defaultIndicatorBuildStage == STAGE_INITIALIZING) attributes.add("defaultIndicator");
      return "Cannot build UserAccess, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return UserAccess.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return UserAccess.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return UserAccess.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return UserAccess.super.updatedCount();
  }

  private boolean defaultIndicatorInitialize() {
    return UserAccess.super.defaultIndicator();
  }

  /**
   * @return The value of the {@code createdId} attribute
   */
  @Override
  public @Nullable Long createdId() {
    return createdId;
  }

  /**
   * @return The value of the {@code createdDateTime} attribute
   */
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
  @Override
  public @Nullable Long updatedId() {
    return updatedId;
  }

  /**
   * @return The value of the {@code updatedDateTime} attribute
   */
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
  @Override
  public long updatedCount() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.updatedCount()
        : this.updatedCount;
  }

  /**
   * @return The value of the {@code userAccessId} attribute
   */
  @Override
  public @Nullable Long userAccessId() {
    return userAccessId;
  }

  /**
   * @return The value of the {@code userId} attribute
   */
  @Override
  public long userId() {
    return userId;
  }

  /**
   * @return The value of the {@code tenantId} attribute
   */
  @Override
  public int tenantId() {
    return tenantId;
  }

  /**
   * @return The value of the {@code personId} attribute
   */
  @Override
  public long personId() {
    return personId;
  }

  /**
   * @return The value of the {@code defaultIndicator} attribute
   */
  @Override
  public boolean defaultIndicator() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.defaultIndicator()
        : this.defaultIndicator;
  }

  /**
   * @return The value of the {@code lastLoginDateTime} attribute
   */
  @Override
  public @Nullable OffsetDateTime lastLoginDateTime() {
    return lastLoginDateTime;
  }

  /**
   * @return The value of the {@code lastLoginIp} attribute
   */
  @Override
  public @Nullable String lastLoginIp() {
    return lastLoginIp;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableUserAccess} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableUserAccess
        && equalTo((ImmutableUserAccess) another);
  }

  private boolean equalTo(ImmutableUserAccess another) {
    return active == another.active
        && userId == another.userId
        && tenantId == another.tenantId
        && personId == another.personId
        && defaultIndicator == another.defaultIndicator
        && Objects.equals(lastLoginDateTime, another.lastLoginDateTime)
        && Objects.equals(lastLoginIp, another.lastLoginIp);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code userId}, {@code tenantId}, {@code personId}, {@code defaultIndicator}, {@code lastLoginDateTime}, {@code lastLoginIp}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + Long.hashCode(userId);
    h += (h << 5) + tenantId;
    h += (h << 5) + Long.hashCode(personId);
    h += (h << 5) + Boolean.hashCode(defaultIndicator);
    h += (h << 5) + Objects.hashCode(lastLoginDateTime);
    h += (h << 5) + Objects.hashCode(lastLoginIp);
    return h;
  }

  /**
   * Prints the immutable value {@code UserAccess} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "UserAccess{"
        + "active=" + active
        + ", userId=" + userId
        + ", tenantId=" + tenantId
        + ", personId=" + personId
        + ", defaultIndicator=" + defaultIndicator
        + ", lastLoginDateTime=" + lastLoginDateTime
        + ", lastLoginIp=" + lastLoginIp
        + "}";
  }

  /**
   * Builds instances of type {@link UserAccess UserAccess}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "UserAccess", generator = "Immutables")
  public static class Builder {
    private static final long INIT_BIT_USER_ID = 0x1L;
    private static final long INIT_BIT_TENANT_ID = 0x2L;
    private static final long INIT_BIT_PERSON_ID = 0x4L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private static final long OPT_BIT_DEFAULT_INDICATOR = 0x4L;
    private long initBits = 0x7L;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long userAccessId;
    private long userId;
    private int tenantId;
    private long personId;
    private boolean defaultIndicator;
    private OffsetDateTime lastLoginDateTime;
    private String lastLoginIp;

    /**
     * Creates a builder for {@link UserAccess UserAccess} instances.
     * <pre>
     * new UserAccess.Builder()
     *    .createdId(Long | null) // nullable {@link UserAccess#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link UserAccess#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link UserAccess#active() active}
     *    .updatedId(Long | null) // nullable {@link UserAccess#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link UserAccess#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link UserAccess#updatedCount() updatedCount}
     *    .userAccessId(Long | null) // nullable {@link UserAccess#userAccessId() userAccessId}
     *    .userId(long) // required {@link UserAccess#userId() userId}
     *    .tenantId(int) // required {@link UserAccess#tenantId() tenantId}
     *    .personId(long) // required {@link UserAccess#personId() personId}
     *    .defaultIndicator(boolean) // optional {@link UserAccess#defaultIndicator() defaultIndicator}
     *    .lastLoginDateTime(java.time.OffsetDateTime | null) // nullable {@link UserAccess#lastLoginDateTime() lastLoginDateTime}
     *    .lastLoginIp(String | null) // nullable {@link UserAccess#lastLoginIp() lastLoginIp}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof UserAccess.Builder)) {
        throw new UnsupportedOperationException("Use: new UserAccess.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.user.access.UserAccess} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder from(UserAccess instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (UserAccess.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (UserAccess.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof UserAccess) {
        UserAccess instance = (UserAccess) object;
        Long userAccessIdValue = instance.userAccessId();
        if (userAccessIdValue != null) {
          userAccessId(userAccessIdValue);
        }
        OffsetDateTime lastLoginDateTimeValue = instance.lastLoginDateTime();
        if (lastLoginDateTimeValue != null) {
          lastLoginDateTime(lastLoginDateTimeValue);
        }
        tenantId(instance.tenantId());
        defaultIndicator(instance.defaultIndicator());
        personId(instance.personId());
        userId(instance.userId());
        String lastLoginIpValue = instance.lastLoginIp();
        if (lastLoginIpValue != null) {
          lastLoginIp(lastLoginIpValue);
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
        updatedDateTime(instance.updatedDateTime());
        updatedCount(instance.updatedCount());
      }
    }

    /**
     * Initializes the value for the {@link UserAccess#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link UserAccess#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link UserAccess#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link UserAccess#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link UserAccess#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#userAccessId() userAccessId} attribute.
     * @param userAccessId The value for userAccessId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder userAccessId(@Nullable Long userAccessId) {
      this.userAccessId = userAccessId;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#userId() userId} attribute.
     * @param userId The value for userId 
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder userId(long userId) {
      this.userId = userId;
      initBits &= ~INIT_BIT_USER_ID;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#tenantId() tenantId} attribute.
     * @param tenantId The value for tenantId 
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder tenantId(int tenantId) {
      this.tenantId = tenantId;
      initBits &= ~INIT_BIT_TENANT_ID;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#personId() personId} attribute.
     * @param personId The value for personId 
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder personId(long personId) {
      this.personId = personId;
      initBits &= ~INIT_BIT_PERSON_ID;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#defaultIndicator() defaultIndicator} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link UserAccess#defaultIndicator() defaultIndicator}.</em>
     * @param defaultIndicator The value for defaultIndicator 
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder defaultIndicator(boolean defaultIndicator) {
      this.defaultIndicator = defaultIndicator;
      optBits |= OPT_BIT_DEFAULT_INDICATOR;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#lastLoginDateTime() lastLoginDateTime} attribute.
     * @param lastLoginDateTime The value for lastLoginDateTime (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder lastLoginDateTime(@Nullable OffsetDateTime lastLoginDateTime) {
      this.lastLoginDateTime = lastLoginDateTime;
      return (UserAccess.Builder) this;
    }

    /**
     * Initializes the value for the {@link UserAccess#lastLoginIp() lastLoginIp} attribute.
     * @param lastLoginIp The value for lastLoginIp (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    public final UserAccess.Builder lastLoginIp(@Nullable String lastLoginIp) {
      this.lastLoginIp = lastLoginIp;
      return (UserAccess.Builder) this;
    }

    /**
     * Builds a new {@link UserAccess UserAccess}.
     * @return An immutable instance of UserAccess
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public UserAccess build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableUserAccess(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private boolean defaultIndicatorIsSet() {
      return (optBits & OPT_BIT_DEFAULT_INDICATOR) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_USER_ID) != 0) attributes.add("userId");
      if ((initBits & INIT_BIT_TENANT_ID) != 0) attributes.add("tenantId");
      if ((initBits & INIT_BIT_PERSON_ID) != 0) attributes.add("personId");
      return "Cannot build UserAccess, some of required attributes are not set " + attributes;
    }
  }
}
