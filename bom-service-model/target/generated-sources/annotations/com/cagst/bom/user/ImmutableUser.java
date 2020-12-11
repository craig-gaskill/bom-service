package com.cagst.bom.user;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.user.access.UserAccess;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link User}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new User.Builder()}.
 */
@Generated(from = "User", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableUser implements User {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long userId;
  private final String username;
  private final boolean temporaryPassword;
  private final @Nullable OffsetDateTime accountLockedDateTime;
  private final @Nullable AccountLockedType accountLockedType;
  private final @Nullable OffsetDateTime accountExpiredDateTime;
  private final @Nullable OffsetDateTime passwordChangedDateTime;
  private final @Nullable OffsetDateTime passwordExpiredDateTime;
  private final boolean admin;
  private final @Nullable List<UserAccess> access;

  private ImmutableUser(ImmutableUser.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.userId = builder.userId;
    this.username = builder.username;
    this.accountLockedDateTime = builder.accountLockedDateTime;
    this.accountLockedType = builder.accountLockedType;
    this.accountExpiredDateTime = builder.accountExpiredDateTime;
    this.passwordChangedDateTime = builder.passwordChangedDateTime;
    this.passwordExpiredDateTime = builder.passwordExpiredDateTime;
    this.access = builder.access == null ? null : createUnmodifiableList(true, builder.access);
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
    if (builder.temporaryPasswordIsSet()) {
      initShim.temporaryPassword(builder.temporaryPassword);
    }
    if (builder.adminIsSet()) {
      initShim.admin(builder.admin);
    }
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.temporaryPassword = initShim.temporaryPassword();
    this.admin = initShim.admin();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "User", generator = "Immutables")
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

    private byte temporaryPasswordBuildStage = STAGE_UNINITIALIZED;
    private boolean temporaryPassword;

    boolean temporaryPassword() {
      if (temporaryPasswordBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (temporaryPasswordBuildStage == STAGE_UNINITIALIZED) {
        temporaryPasswordBuildStage = STAGE_INITIALIZING;
        this.temporaryPassword = temporaryPasswordInitialize();
        temporaryPasswordBuildStage = STAGE_INITIALIZED;
      }
      return this.temporaryPassword;
    }

    void temporaryPassword(boolean temporaryPassword) {
      this.temporaryPassword = temporaryPassword;
      temporaryPasswordBuildStage = STAGE_INITIALIZED;
    }

    private byte adminBuildStage = STAGE_UNINITIALIZED;
    private boolean admin;

    boolean admin() {
      if (adminBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (adminBuildStage == STAGE_UNINITIALIZED) {
        adminBuildStage = STAGE_INITIALIZING;
        this.admin = adminInitialize();
        adminBuildStage = STAGE_INITIALIZED;
      }
      return this.admin;
    }

    void admin(boolean admin) {
      this.admin = admin;
      adminBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      if (temporaryPasswordBuildStage == STAGE_INITIALIZING) attributes.add("temporaryPassword");
      if (adminBuildStage == STAGE_INITIALIZING) attributes.add("admin");
      return "Cannot build User, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return User.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return User.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return User.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return User.super.updatedCount();
  }

  private boolean temporaryPasswordInitialize() {
    return User.super.temporaryPassword();
  }

  private boolean adminInitialize() {
    return User.super.admin();
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
   * @return The value of the {@code userId} attribute
   */
  @JsonProperty("userId")
  @Override
  public @Nullable Long userId() {
    return userId;
  }

  /**
   * @return The value of the {@code username} attribute
   */
  @JsonProperty("username")
  @Override
  public String username() {
    return username;
  }

  /**
   * @return The value of the {@code temporaryPassword} attribute
   */
  @JsonProperty("temporaryPassword")
  @Override
  public boolean temporaryPassword() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.temporaryPassword()
        : this.temporaryPassword;
  }

  /**
   * @return The value of the {@code accountLockedDateTime} attribute
   */
  @JsonProperty("accountLockedDateTime")
  @Override
  public @Nullable OffsetDateTime accountLockedDateTime() {
    return accountLockedDateTime;
  }

  /**
   * @return The value of the {@code accountLockedType} attribute
   */
  @JsonProperty("accountLockedType")
  @Override
  public @Nullable AccountLockedType accountLockedType() {
    return accountLockedType;
  }

  /**
   * @return The value of the {@code accountExpiredDateTime} attribute
   */
  @JsonProperty("accountExpiredDateTime")
  @Override
  public @Nullable OffsetDateTime accountExpiredDateTime() {
    return accountExpiredDateTime;
  }

  /**
   * @return The value of the {@code passwordChangedDateTime} attribute
   */
  @JsonProperty("passwordChangedDateTime")
  @Override
  public @Nullable OffsetDateTime passwordChangedDateTime() {
    return passwordChangedDateTime;
  }

  /**
   * @return The value of the {@code passwordExpiredDateTime} attribute
   */
  @JsonProperty("passwordExpiredDateTime")
  @Override
  public @Nullable OffsetDateTime passwordExpiredDateTime() {
    return passwordExpiredDateTime;
  }

  /**
   * @return The value of the {@code admin} attribute
   */
  @JsonProperty("admin")
  @Override
  public boolean admin() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.admin()
        : this.admin;
  }

  /**
   * @return The value of the {@code access} attribute
   */
  @JsonProperty("access")
  @Override
  public @Nullable List<UserAccess> access() {
    return access;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableUser} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableUser
        && equalTo((ImmutableUser) another);
  }

  private boolean equalTo(ImmutableUser another) {
    return active == another.active
        && username.equals(another.username)
        && temporaryPassword == another.temporaryPassword
        && Objects.equals(accountLockedDateTime, another.accountLockedDateTime)
        && Objects.equals(accountLockedType, another.accountLockedType)
        && Objects.equals(accountExpiredDateTime, another.accountExpiredDateTime)
        && Objects.equals(passwordChangedDateTime, another.passwordChangedDateTime)
        && Objects.equals(passwordExpiredDateTime, another.passwordExpiredDateTime)
        && admin == another.admin
        && Objects.equals(access, another.access);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code username}, {@code temporaryPassword}, {@code accountLockedDateTime}, {@code accountLockedType}, {@code accountExpiredDateTime}, {@code passwordChangedDateTime}, {@code passwordExpiredDateTime}, {@code admin}, {@code access}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + username.hashCode();
    h += (h << 5) + Boolean.hashCode(temporaryPassword);
    h += (h << 5) + Objects.hashCode(accountLockedDateTime);
    h += (h << 5) + Objects.hashCode(accountLockedType);
    h += (h << 5) + Objects.hashCode(accountExpiredDateTime);
    h += (h << 5) + Objects.hashCode(passwordChangedDateTime);
    h += (h << 5) + Objects.hashCode(passwordExpiredDateTime);
    h += (h << 5) + Boolean.hashCode(admin);
    h += (h << 5) + Objects.hashCode(access);
    return h;
  }

  /**
   * Prints the immutable value {@code User} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "User{"
        + "active=" + active
        + ", username=" + username
        + ", temporaryPassword=" + temporaryPassword
        + ", accountLockedDateTime=" + accountLockedDateTime
        + ", accountLockedType=" + accountLockedType
        + ", accountExpiredDateTime=" + accountExpiredDateTime
        + ", passwordChangedDateTime=" + passwordChangedDateTime
        + ", passwordExpiredDateTime=" + passwordExpiredDateTime
        + ", admin=" + admin
        + ", access=" + access
        + "}";
  }

  /**
   * Builds instances of type {@link User User}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "User", generator = "Immutables")
  @JsonPropertyOrder({"userId", "username", "temporaryPassword", "accountLockedDateTime", "accountLockType", "accountExpiredDateTime", "passwordChangedDateTime", "passwordExpiredDateTime", "admin", "access", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_USERNAME = 0x1L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private static final long OPT_BIT_TEMPORARY_PASSWORD = 0x4L;
    private static final long OPT_BIT_ADMIN = 0x8L;
    private long initBits = 0x1L;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long userId;
    private String username;
    private boolean temporaryPassword;
    private OffsetDateTime accountLockedDateTime;
    private AccountLockedType accountLockedType;
    private OffsetDateTime accountExpiredDateTime;
    private OffsetDateTime passwordChangedDateTime;
    private OffsetDateTime passwordExpiredDateTime;
    private boolean admin;
    private List<UserAccess> access = null;

    /**
     * Creates a builder for {@link User User} instances.
     * <pre>
     * new User.Builder()
     *    .createdId(Long | null) // nullable {@link User#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link User#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link User#active() active}
     *    .updatedId(Long | null) // nullable {@link User#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link User#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link User#updatedCount() updatedCount}
     *    .userId(Long | null) // nullable {@link User#userId() userId}
     *    .username(String) // required {@link User#username() username}
     *    .temporaryPassword(boolean) // optional {@link User#temporaryPassword() temporaryPassword}
     *    .accountLockedDateTime(java.time.OffsetDateTime | null) // nullable {@link User#accountLockedDateTime() accountLockedDateTime}
     *    .accountLockedType(com.cagst.bom.user.AccountLockedType | null) // nullable {@link User#accountLockedType() accountLockedType}
     *    .accountExpiredDateTime(java.time.OffsetDateTime | null) // nullable {@link User#accountExpiredDateTime() accountExpiredDateTime}
     *    .passwordChangedDateTime(java.time.OffsetDateTime | null) // nullable {@link User#passwordChangedDateTime() passwordChangedDateTime}
     *    .passwordExpiredDateTime(java.time.OffsetDateTime | null) // nullable {@link User#passwordExpiredDateTime() passwordExpiredDateTime}
     *    .admin(boolean) // optional {@link User#admin() admin}
     *    .access(List&amp;lt;com.cagst.bom.user.access.UserAccess&amp;gt; | null) // nullable {@link User#access() access}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof User.Builder)) {
        throw new UnsupportedOperationException("Use: new User.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.user.User} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final User.Builder from(User instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (User.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final User.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (User.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof User) {
        User instance = (User) object;
        temporaryPassword(instance.temporaryPassword());
        List<UserAccess> accessValue = instance.access();
        if (accessValue != null) {
          addAllAccess(accessValue);
        }
        OffsetDateTime accountExpiredDateTimeValue = instance.accountExpiredDateTime();
        if (accountExpiredDateTimeValue != null) {
          accountExpiredDateTime(accountExpiredDateTimeValue);
        }
        OffsetDateTime accountLockedDateTimeValue = instance.accountLockedDateTime();
        if (accountLockedDateTimeValue != null) {
          accountLockedDateTime(accountLockedDateTimeValue);
        }
        OffsetDateTime passwordChangedDateTimeValue = instance.passwordChangedDateTime();
        if (passwordChangedDateTimeValue != null) {
          passwordChangedDateTime(passwordChangedDateTimeValue);
        }
        admin(instance.admin());
        AccountLockedType accountLockedTypeValue = instance.accountLockedType();
        if (accountLockedTypeValue != null) {
          accountLockedType(accountLockedTypeValue);
        }
        Long userIdValue = instance.userId();
        if (userIdValue != null) {
          userId(userIdValue);
        }
        OffsetDateTime passwordExpiredDateTimeValue = instance.passwordExpiredDateTime();
        if (passwordExpiredDateTimeValue != null) {
          passwordExpiredDateTime(passwordExpiredDateTimeValue);
        }
        username(instance.username());
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
     * Initializes the value for the {@link User#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final User.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link User#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final User.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link User#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final User.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final User.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link User#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final User.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link User#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final User.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#userId() userId} attribute.
     * @param userId The value for userId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("userId")
    public final User.Builder userId(@Nullable Long userId) {
      this.userId = userId;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#username() username} attribute.
     * @param username The value for username 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("username")
    public final User.Builder username(String username) {
      this.username = Objects.requireNonNull(username, "username");
      initBits &= ~INIT_BIT_USERNAME;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#temporaryPassword() temporaryPassword} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link User#temporaryPassword() temporaryPassword}.</em>
     * @param temporaryPassword The value for temporaryPassword 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("temporaryPassword")
    public final User.Builder temporaryPassword(boolean temporaryPassword) {
      this.temporaryPassword = temporaryPassword;
      optBits |= OPT_BIT_TEMPORARY_PASSWORD;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#accountLockedDateTime() accountLockedDateTime} attribute.
     * @param accountLockedDateTime The value for accountLockedDateTime (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("accountLockedDateTime")
    public final User.Builder accountLockedDateTime(@Nullable OffsetDateTime accountLockedDateTime) {
      this.accountLockedDateTime = accountLockedDateTime;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#accountLockedType() accountLockedType} attribute.
     * @param accountLockedType The value for accountLockedType (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("accountLockedType")
    public final User.Builder accountLockedType(@Nullable AccountLockedType accountLockedType) {
      this.accountLockedType = accountLockedType;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#accountExpiredDateTime() accountExpiredDateTime} attribute.
     * @param accountExpiredDateTime The value for accountExpiredDateTime (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("accountExpiredDateTime")
    public final User.Builder accountExpiredDateTime(@Nullable OffsetDateTime accountExpiredDateTime) {
      this.accountExpiredDateTime = accountExpiredDateTime;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#passwordChangedDateTime() passwordChangedDateTime} attribute.
     * @param passwordChangedDateTime The value for passwordChangedDateTime (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("passwordChangedDateTime")
    public final User.Builder passwordChangedDateTime(@Nullable OffsetDateTime passwordChangedDateTime) {
      this.passwordChangedDateTime = passwordChangedDateTime;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#passwordExpiredDateTime() passwordExpiredDateTime} attribute.
     * @param passwordExpiredDateTime The value for passwordExpiredDateTime (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("passwordExpiredDateTime")
    public final User.Builder passwordExpiredDateTime(@Nullable OffsetDateTime passwordExpiredDateTime) {
      this.passwordExpiredDateTime = passwordExpiredDateTime;
      return (User.Builder) this;
    }

    /**
     * Initializes the value for the {@link User#admin() admin} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link User#admin() admin}.</em>
     * @param admin The value for admin 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("admin")
    public final User.Builder admin(boolean admin) {
      this.admin = admin;
      optBits |= OPT_BIT_ADMIN;
      return (User.Builder) this;
    }

    /**
     * Adds one element to {@link User#access() access} list.
     * @param element A access element
     * @return {@code this} builder for use in a chained invocation
     */
    public final User.Builder addAccess(UserAccess element) {
      if (this.access == null) {
        this.access = new ArrayList<UserAccess>();
      }
      this.access.add(Objects.requireNonNull(element, "access element"));
      return (User.Builder) this;
    }

    /**
     * Adds elements to {@link User#access() access} list.
     * @param elements An array of access elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final User.Builder addAccess(UserAccess... elements) {
      if (this.access == null) {
        this.access = new ArrayList<UserAccess>();
      }
      for (UserAccess element : elements) {
        this.access.add(Objects.requireNonNull(element, "access element"));
      }
      return (User.Builder) this;
    }


    /**
     * Sets or replaces all elements for {@link User#access() access} list.
     * @param elements An iterable of access elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("access")
    public final User.Builder access(@Nullable Iterable<? extends UserAccess> elements) {
      if (elements == null) {
        this.access = null;
        return (User.Builder) this;
      }
      this.access = new ArrayList<UserAccess>();
      return addAllAccess(elements);
    }

    /**
     * Adds elements to {@link User#access() access} list.
     * @param elements An iterable of access elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final User.Builder addAllAccess(Iterable<? extends UserAccess> elements) {
      Objects.requireNonNull(elements, "access element");
      if (this.access == null) {
        this.access = new ArrayList<UserAccess>();
      }
      for (UserAccess element : elements) {
        this.access.add(Objects.requireNonNull(element, "access element"));
      }
      return (User.Builder) this;
    }

    /**
     * Builds a new {@link User User}.
     * @return An immutable instance of User
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public User build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableUser(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private boolean temporaryPasswordIsSet() {
      return (optBits & OPT_BIT_TEMPORARY_PASSWORD) != 0;
    }

    private boolean adminIsSet() {
      return (optBits & OPT_BIT_ADMIN) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_USERNAME) != 0) attributes.add("username");
      return "Cannot build User, some of required attributes are not set " + attributes;
    }
  }

  private static <T> List<T> createSafeList(Iterable<? extends T> iterable, boolean checkNulls, boolean skipNulls) {
    ArrayList<T> list;
    if (iterable instanceof Collection<?>) {
      int size = ((Collection<?>) iterable).size();
      if (size == 0) return Collections.emptyList();
      list = new ArrayList<>();
    } else {
      list = new ArrayList<>();
    }
    for (T element : iterable) {
      if (skipNulls && element == null) continue;
      if (checkNulls) Objects.requireNonNull(element, "element");
      list.add(element);
    }
    return list;
  }

  private static <T> List<T> createUnmodifiableList(boolean clone, List<T> list) {
    switch(list.size()) {
    case 0: return Collections.emptyList();
    case 1: return Collections.singletonList(list.get(0));
    default:
      if (clone) {
        return Collections.unmodifiableList(new ArrayList<>(list));
      } else {
        if (list instanceof ArrayList<?>) {
          ((ArrayList<?>) list).trimToSize();
        }
        return Collections.unmodifiableList(list);
      }
    }
  }
}
