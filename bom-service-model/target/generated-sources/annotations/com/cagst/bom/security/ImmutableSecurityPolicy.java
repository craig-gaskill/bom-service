package com.cagst.bom.security;

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
 * Immutable implementation of {@link SecurityPolicy}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new SecurityPolicy.Builder()}.
 */
@Generated(from = "SecurityPolicy", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableSecurityPolicy implements SecurityPolicy {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long securityPolicyId;
  private final String policyName;
  private final int maxAttempts;
  private final int timeoutInMinutes;
  private final int expiryInDays;
  private final int lockedInMinutes;

  private ImmutableSecurityPolicy(ImmutableSecurityPolicy.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.securityPolicyId = builder.securityPolicyId;
    this.policyName = builder.policyName;
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
    if (builder.maxAttemptsIsSet()) {
      initShim.maxAttempts(builder.maxAttempts);
    }
    if (builder.timeoutInMinutesIsSet()) {
      initShim.timeoutInMinutes(builder.timeoutInMinutes);
    }
    if (builder.expiryInDaysIsSet()) {
      initShim.expiryInDays(builder.expiryInDays);
    }
    if (builder.lockedInMinutesIsSet()) {
      initShim.lockedInMinutes(builder.lockedInMinutes);
    }
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.maxAttempts = initShim.maxAttempts();
    this.timeoutInMinutes = initShim.timeoutInMinutes();
    this.expiryInDays = initShim.expiryInDays();
    this.lockedInMinutes = initShim.lockedInMinutes();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "SecurityPolicy", generator = "Immutables")
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

    private byte maxAttemptsBuildStage = STAGE_UNINITIALIZED;
    private int maxAttempts;

    int maxAttempts() {
      if (maxAttemptsBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (maxAttemptsBuildStage == STAGE_UNINITIALIZED) {
        maxAttemptsBuildStage = STAGE_INITIALIZING;
        this.maxAttempts = maxAttemptsInitialize();
        maxAttemptsBuildStage = STAGE_INITIALIZED;
      }
      return this.maxAttempts;
    }

    void maxAttempts(int maxAttempts) {
      this.maxAttempts = maxAttempts;
      maxAttemptsBuildStage = STAGE_INITIALIZED;
    }

    private byte timeoutInMinutesBuildStage = STAGE_UNINITIALIZED;
    private int timeoutInMinutes;

    int timeoutInMinutes() {
      if (timeoutInMinutesBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (timeoutInMinutesBuildStage == STAGE_UNINITIALIZED) {
        timeoutInMinutesBuildStage = STAGE_INITIALIZING;
        this.timeoutInMinutes = timeoutInMinutesInitialize();
        timeoutInMinutesBuildStage = STAGE_INITIALIZED;
      }
      return this.timeoutInMinutes;
    }

    void timeoutInMinutes(int timeoutInMinutes) {
      this.timeoutInMinutes = timeoutInMinutes;
      timeoutInMinutesBuildStage = STAGE_INITIALIZED;
    }

    private byte expiryInDaysBuildStage = STAGE_UNINITIALIZED;
    private int expiryInDays;

    int expiryInDays() {
      if (expiryInDaysBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (expiryInDaysBuildStage == STAGE_UNINITIALIZED) {
        expiryInDaysBuildStage = STAGE_INITIALIZING;
        this.expiryInDays = expiryInDaysInitialize();
        expiryInDaysBuildStage = STAGE_INITIALIZED;
      }
      return this.expiryInDays;
    }

    void expiryInDays(int expiryInDays) {
      this.expiryInDays = expiryInDays;
      expiryInDaysBuildStage = STAGE_INITIALIZED;
    }

    private byte lockedInMinutesBuildStage = STAGE_UNINITIALIZED;
    private int lockedInMinutes;

    int lockedInMinutes() {
      if (lockedInMinutesBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (lockedInMinutesBuildStage == STAGE_UNINITIALIZED) {
        lockedInMinutesBuildStage = STAGE_INITIALIZING;
        this.lockedInMinutes = lockedInMinutesInitialize();
        lockedInMinutesBuildStage = STAGE_INITIALIZED;
      }
      return this.lockedInMinutes;
    }

    void lockedInMinutes(int lockedInMinutes) {
      this.lockedInMinutes = lockedInMinutes;
      lockedInMinutesBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      if (maxAttemptsBuildStage == STAGE_INITIALIZING) attributes.add("maxAttempts");
      if (timeoutInMinutesBuildStage == STAGE_INITIALIZING) attributes.add("timeoutInMinutes");
      if (expiryInDaysBuildStage == STAGE_INITIALIZING) attributes.add("expiryInDays");
      if (lockedInMinutesBuildStage == STAGE_INITIALIZING) attributes.add("lockedInMinutes");
      return "Cannot build SecurityPolicy, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return SecurityPolicy.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return SecurityPolicy.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return SecurityPolicy.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return SecurityPolicy.super.updatedCount();
  }

  private int maxAttemptsInitialize() {
    return SecurityPolicy.super.maxAttempts();
  }

  private int timeoutInMinutesInitialize() {
    return SecurityPolicy.super.timeoutInMinutes();
  }

  private int expiryInDaysInitialize() {
    return SecurityPolicy.super.expiryInDays();
  }

  private int lockedInMinutesInitialize() {
    return SecurityPolicy.super.lockedInMinutes();
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
   * @return The value of the {@code securityPolicyId} attribute
   */
  @JsonProperty("securityPolicyId")
  @Override
  public @Nullable Long securityPolicyId() {
    return securityPolicyId;
  }

  /**
   * @return The value of the {@code policyName} attribute
   */
  @JsonProperty("policyName")
  @Override
  public String policyName() {
    return policyName;
  }

  /**
   * @return The value of the {@code maxAttempts} attribute
   */
  @JsonProperty("maxAttempts")
  @Override
  public int maxAttempts() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.maxAttempts()
        : this.maxAttempts;
  }

  /**
   * @return The value of the {@code timeoutInMinutes} attribute
   */
  @JsonProperty("timeoutInMinutes")
  @Override
  public int timeoutInMinutes() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.timeoutInMinutes()
        : this.timeoutInMinutes;
  }

  /**
   * @return The value of the {@code expiryInDays} attribute
   */
  @JsonProperty("expiryInDays")
  @Override
  public int expiryInDays() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.expiryInDays()
        : this.expiryInDays;
  }

  /**
   * @return The value of the {@code lockedInMinutes} attribute
   */
  @JsonProperty("lockedInMinutes")
  @Override
  public int lockedInMinutes() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.lockedInMinutes()
        : this.lockedInMinutes;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableSecurityPolicy} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableSecurityPolicy
        && equalTo((ImmutableSecurityPolicy) another);
  }

  private boolean equalTo(ImmutableSecurityPolicy another) {
    return active == another.active
        && policyName.equals(another.policyName)
        && maxAttempts == another.maxAttempts
        && timeoutInMinutes == another.timeoutInMinutes
        && expiryInDays == another.expiryInDays
        && lockedInMinutes == another.lockedInMinutes;
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code policyName}, {@code maxAttempts}, {@code timeoutInMinutes}, {@code expiryInDays}, {@code lockedInMinutes}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + policyName.hashCode();
    h += (h << 5) + maxAttempts;
    h += (h << 5) + timeoutInMinutes;
    h += (h << 5) + expiryInDays;
    h += (h << 5) + lockedInMinutes;
    return h;
  }

  /**
   * Prints the immutable value {@code SecurityPolicy} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "SecurityPolicy{"
        + "active=" + active
        + ", policyName=" + policyName
        + ", maxAttempts=" + maxAttempts
        + ", timeoutInMinutes=" + timeoutInMinutes
        + ", expiryInDays=" + expiryInDays
        + ", lockedInMinutes=" + lockedInMinutes
        + "}";
  }

  /**
   * Builds instances of type {@link SecurityPolicy SecurityPolicy}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "SecurityPolicy", generator = "Immutables")
  @JsonPropertyOrder({"securityPolicyId", "policyName", "maxAttempts", "timeoutInMinutes", "expiryInDays", "lockedInMinutes", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_POLICY_NAME = 0x1L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private static final long OPT_BIT_MAX_ATTEMPTS = 0x4L;
    private static final long OPT_BIT_TIMEOUT_IN_MINUTES = 0x8L;
    private static final long OPT_BIT_EXPIRY_IN_DAYS = 0x10L;
    private static final long OPT_BIT_LOCKED_IN_MINUTES = 0x20L;
    private long initBits = 0x1L;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long securityPolicyId;
    private String policyName;
    private int maxAttempts;
    private int timeoutInMinutes;
    private int expiryInDays;
    private int lockedInMinutes;

    /**
     * Creates a builder for {@link SecurityPolicy SecurityPolicy} instances.
     * <pre>
     * new SecurityPolicy.Builder()
     *    .createdId(Long | null) // nullable {@link SecurityPolicy#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link SecurityPolicy#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link SecurityPolicy#active() active}
     *    .updatedId(Long | null) // nullable {@link SecurityPolicy#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link SecurityPolicy#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link SecurityPolicy#updatedCount() updatedCount}
     *    .securityPolicyId(Long | null) // nullable {@link SecurityPolicy#securityPolicyId() securityPolicyId}
     *    .policyName(String) // required {@link SecurityPolicy#policyName() policyName}
     *    .maxAttempts(int) // optional {@link SecurityPolicy#maxAttempts() maxAttempts}
     *    .timeoutInMinutes(int) // optional {@link SecurityPolicy#timeoutInMinutes() timeoutInMinutes}
     *    .expiryInDays(int) // optional {@link SecurityPolicy#expiryInDays() expiryInDays}
     *    .lockedInMinutes(int) // optional {@link SecurityPolicy#lockedInMinutes() lockedInMinutes}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof SecurityPolicy.Builder)) {
        throw new UnsupportedOperationException("Use: new SecurityPolicy.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.security.SecurityPolicy} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final SecurityPolicy.Builder from(SecurityPolicy instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final SecurityPolicy.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (SecurityPolicy.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof SecurityPolicy) {
        SecurityPolicy instance = (SecurityPolicy) object;
        timeoutInMinutes(instance.timeoutInMinutes());
        lockedInMinutes(instance.lockedInMinutes());
        maxAttempts(instance.maxAttempts());
        expiryInDays(instance.expiryInDays());
        policyName(instance.policyName());
        Long securityPolicyIdValue = instance.securityPolicyId();
        if (securityPolicyIdValue != null) {
          securityPolicyId(securityPolicyIdValue);
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
     * Initializes the value for the {@link SecurityPolicy#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final SecurityPolicy.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SecurityPolicy#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final SecurityPolicy.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SecurityPolicy#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final SecurityPolicy.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final SecurityPolicy.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SecurityPolicy#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final SecurityPolicy.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SecurityPolicy#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final SecurityPolicy.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#securityPolicyId() securityPolicyId} attribute.
     * @param securityPolicyId The value for securityPolicyId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("securityPolicyId")
    public final SecurityPolicy.Builder securityPolicyId(@Nullable Long securityPolicyId) {
      this.securityPolicyId = securityPolicyId;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#policyName() policyName} attribute.
     * @param policyName The value for policyName 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("policyName")
    public final SecurityPolicy.Builder policyName(String policyName) {
      this.policyName = Objects.requireNonNull(policyName, "policyName");
      initBits &= ~INIT_BIT_POLICY_NAME;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#maxAttempts() maxAttempts} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SecurityPolicy#maxAttempts() maxAttempts}.</em>
     * @param maxAttempts The value for maxAttempts 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("maxAttempts")
    public final SecurityPolicy.Builder maxAttempts(int maxAttempts) {
      this.maxAttempts = maxAttempts;
      optBits |= OPT_BIT_MAX_ATTEMPTS;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#timeoutInMinutes() timeoutInMinutes} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SecurityPolicy#timeoutInMinutes() timeoutInMinutes}.</em>
     * @param timeoutInMinutes The value for timeoutInMinutes 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("timeoutInMinutes")
    public final SecurityPolicy.Builder timeoutInMinutes(int timeoutInMinutes) {
      this.timeoutInMinutes = timeoutInMinutes;
      optBits |= OPT_BIT_TIMEOUT_IN_MINUTES;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#expiryInDays() expiryInDays} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SecurityPolicy#expiryInDays() expiryInDays}.</em>
     * @param expiryInDays The value for expiryInDays 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("expiryInDays")
    public final SecurityPolicy.Builder expiryInDays(int expiryInDays) {
      this.expiryInDays = expiryInDays;
      optBits |= OPT_BIT_EXPIRY_IN_DAYS;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityPolicy#lockedInMinutes() lockedInMinutes} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link SecurityPolicy#lockedInMinutes() lockedInMinutes}.</em>
     * @param lockedInMinutes The value for lockedInMinutes 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("lockedInMinutes")
    public final SecurityPolicy.Builder lockedInMinutes(int lockedInMinutes) {
      this.lockedInMinutes = lockedInMinutes;
      optBits |= OPT_BIT_LOCKED_IN_MINUTES;
      return (SecurityPolicy.Builder) this;
    }

    /**
     * Builds a new {@link SecurityPolicy SecurityPolicy}.
     * @return An immutable instance of SecurityPolicy
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public SecurityPolicy build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableSecurityPolicy(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private boolean maxAttemptsIsSet() {
      return (optBits & OPT_BIT_MAX_ATTEMPTS) != 0;
    }

    private boolean timeoutInMinutesIsSet() {
      return (optBits & OPT_BIT_TIMEOUT_IN_MINUTES) != 0;
    }

    private boolean expiryInDaysIsSet() {
      return (optBits & OPT_BIT_EXPIRY_IN_DAYS) != 0;
    }

    private boolean lockedInMinutesIsSet() {
      return (optBits & OPT_BIT_LOCKED_IN_MINUTES) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_POLICY_NAME) != 0) attributes.add("policyName");
      return "Cannot build SecurityPolicy, some of required attributes are not set " + attributes;
    }
  }
}
