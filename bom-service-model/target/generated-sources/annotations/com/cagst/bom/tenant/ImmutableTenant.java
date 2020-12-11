package com.cagst.bom.tenant;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.subscription.SubscriptionType;
import com.cagst.bom.tenant.feature.TenantFeature;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link Tenant}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Tenant.Builder()}.
 */
@Generated(from = "Tenant", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableTenant implements Tenant {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Integer tenantId;
  private final String mnemonic;
  private final String name;
  private final Locale locale;
  private final SubscriptionType subscriptionType;
  private final @Nullable LocalDate subscriptionStartDate;
  private final @Nullable LocalDate subscriptionEndDate;
  private final @Nullable List<TenantFeature> features;

  private ImmutableTenant(ImmutableTenant.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.tenantId = builder.tenantId;
    this.mnemonic = builder.mnemonic;
    this.name = builder.name;
    this.subscriptionStartDate = builder.subscriptionStartDate;
    this.subscriptionEndDate = builder.subscriptionEndDate;
    this.features = builder.features == null ? null : createUnmodifiableList(true, builder.features);
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
    if (builder.locale != null) {
      initShim.locale(builder.locale);
    }
    if (builder.subscriptionType != null) {
      initShim.subscriptionType(builder.subscriptionType);
    }
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.locale = initShim.locale();
    this.subscriptionType = initShim.subscriptionType();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "Tenant", generator = "Immutables")
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

    private byte localeBuildStage = STAGE_UNINITIALIZED;
    private Locale locale;

    Locale locale() {
      if (localeBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (localeBuildStage == STAGE_UNINITIALIZED) {
        localeBuildStage = STAGE_INITIALIZING;
        this.locale = Objects.requireNonNull(localeInitialize(), "locale");
        localeBuildStage = STAGE_INITIALIZED;
      }
      return this.locale;
    }

    void locale(Locale locale) {
      this.locale = locale;
      localeBuildStage = STAGE_INITIALIZED;
    }

    private byte subscriptionTypeBuildStage = STAGE_UNINITIALIZED;
    private SubscriptionType subscriptionType;

    SubscriptionType subscriptionType() {
      if (subscriptionTypeBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (subscriptionTypeBuildStage == STAGE_UNINITIALIZED) {
        subscriptionTypeBuildStage = STAGE_INITIALIZING;
        this.subscriptionType = Objects.requireNonNull(subscriptionTypeInitialize(), "subscriptionType");
        subscriptionTypeBuildStage = STAGE_INITIALIZED;
      }
      return this.subscriptionType;
    }

    void subscriptionType(SubscriptionType subscriptionType) {
      this.subscriptionType = subscriptionType;
      subscriptionTypeBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      if (localeBuildStage == STAGE_INITIALIZING) attributes.add("locale");
      if (subscriptionTypeBuildStage == STAGE_INITIALIZING) attributes.add("subscriptionType");
      return "Cannot build Tenant, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return Tenant.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return Tenant.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return Tenant.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return Tenant.super.updatedCount();
  }

  private Locale localeInitialize() {
    return Tenant.super.locale();
  }

  private SubscriptionType subscriptionTypeInitialize() {
    return Tenant.super.subscriptionType();
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
   * @return The value of the {@code tenantId} attribute
   */
  @JsonProperty("tenantId")
  @Override
  public @Nullable Integer tenantId() {
    return tenantId;
  }

  /**
   * @return The value of the {@code mnemonic} attribute
   */
  @JsonProperty("mnemonic")
  @Override
  public String mnemonic() {
    return mnemonic;
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
   * @return The value of the {@code locale} attribute
   */
  @JsonProperty("locale")
  @Override
  public Locale locale() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.locale()
        : this.locale;
  }

  /**
   * @return The value of the {@code subscriptionType} attribute
   */
  @JsonProperty("subscriptionType")
  @Override
  public SubscriptionType subscriptionType() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.subscriptionType()
        : this.subscriptionType;
  }

  /**
   * @return The value of the {@code subscriptionStartDate} attribute
   */
  @JsonProperty("subscriptionStartDate")
  @Override
  public @Nullable LocalDate subscriptionStartDate() {
    return subscriptionStartDate;
  }

  /**
   * @return The value of the {@code subscriptionEndDate} attribute
   */
  @JsonProperty("subscriptionEndDate")
  @Override
  public @Nullable LocalDate subscriptionEndDate() {
    return subscriptionEndDate;
  }

  /**
   * @return The value of the {@code features} attribute
   */
  @JsonProperty("features")
  @Override
  public @Nullable List<TenantFeature> features() {
    return features;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableTenant} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableTenant
        && equalTo((ImmutableTenant) another);
  }

  private boolean equalTo(ImmutableTenant another) {
    return active == another.active
        && mnemonic.equals(another.mnemonic)
        && name.equals(another.name)
        && locale.equals(another.locale)
        && subscriptionType.equals(another.subscriptionType)
        && Objects.equals(subscriptionStartDate, another.subscriptionStartDate)
        && Objects.equals(subscriptionEndDate, another.subscriptionEndDate)
        && Objects.equals(features, another.features);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code mnemonic}, {@code name}, {@code locale}, {@code subscriptionType}, {@code subscriptionStartDate}, {@code subscriptionEndDate}, {@code features}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + mnemonic.hashCode();
    h += (h << 5) + name.hashCode();
    h += (h << 5) + locale.hashCode();
    h += (h << 5) + subscriptionType.hashCode();
    h += (h << 5) + Objects.hashCode(subscriptionStartDate);
    h += (h << 5) + Objects.hashCode(subscriptionEndDate);
    h += (h << 5) + Objects.hashCode(features);
    return h;
  }

  /**
   * Prints the immutable value {@code Tenant} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Tenant{"
        + "active=" + active
        + ", mnemonic=" + mnemonic
        + ", name=" + name
        + ", locale=" + locale
        + ", subscriptionType=" + subscriptionType
        + ", subscriptionStartDate=" + subscriptionStartDate
        + ", subscriptionEndDate=" + subscriptionEndDate
        + ", features=" + features
        + "}";
  }

  /**
   * Builds instances of type {@link Tenant Tenant}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Tenant", generator = "Immutables")
  @JsonPropertyOrder({"tenantId", "mnemonic", "name", "locale", "subscriptionType", "subscriptionStartDate", "subscriptionEndDate", "features", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_MNEMONIC = 0x1L;
    private static final long INIT_BIT_NAME = 0x2L;
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
    private Integer tenantId;
    private String mnemonic;
    private String name;
    private Locale locale;
    private SubscriptionType subscriptionType;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;
    private List<TenantFeature> features = null;

    /**
     * Creates a builder for {@link Tenant Tenant} instances.
     * <pre>
     * new Tenant.Builder()
     *    .createdId(Long | null) // nullable {@link Tenant#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link Tenant#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link Tenant#active() active}
     *    .updatedId(Long | null) // nullable {@link Tenant#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link Tenant#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link Tenant#updatedCount() updatedCount}
     *    .tenantId(Integer | null) // nullable {@link Tenant#tenantId() tenantId}
     *    .mnemonic(String) // required {@link Tenant#mnemonic() mnemonic}
     *    .name(String) // required {@link Tenant#name() name}
     *    .locale(Locale) // optional {@link Tenant#locale() locale}
     *    .subscriptionType(com.cagst.bom.subscription.SubscriptionType) // optional {@link Tenant#subscriptionType() subscriptionType}
     *    .subscriptionStartDate(java.time.LocalDate | null) // nullable {@link Tenant#subscriptionStartDate() subscriptionStartDate}
     *    .subscriptionEndDate(java.time.LocalDate | null) // nullable {@link Tenant#subscriptionEndDate() subscriptionEndDate}
     *    .features(List&amp;lt;com.cagst.bom.tenant.feature.TenantFeature&amp;gt; | null) // nullable {@link Tenant#features() features}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof Tenant.Builder)) {
        throw new UnsupportedOperationException("Use: new Tenant.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.tenant.Tenant} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Tenant.Builder from(Tenant instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Tenant.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Tenant.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Tenant.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof Tenant) {
        Tenant instance = (Tenant) object;
        List<TenantFeature> featuresValue = instance.features();
        if (featuresValue != null) {
          addAllFeatures(featuresValue);
        }
        subscriptionType(instance.subscriptionType());
        LocalDate subscriptionEndDateValue = instance.subscriptionEndDate();
        if (subscriptionEndDateValue != null) {
          subscriptionEndDate(subscriptionEndDateValue);
        }
        Integer tenantIdValue = instance.tenantId();
        if (tenantIdValue != null) {
          tenantId(tenantIdValue);
        }
        name(instance.name());
        mnemonic(instance.mnemonic());
        locale(instance.locale());
        LocalDate subscriptionStartDateValue = instance.subscriptionStartDate();
        if (subscriptionStartDateValue != null) {
          subscriptionStartDate(subscriptionStartDateValue);
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
     * Initializes the value for the {@link Tenant#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final Tenant.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Tenant#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final Tenant.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Tenant#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final Tenant.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final Tenant.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Tenant#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final Tenant.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Tenant#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final Tenant.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#tenantId() tenantId} attribute.
     * @param tenantId The value for tenantId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("tenantId")
    public final Tenant.Builder tenantId(@Nullable Integer tenantId) {
      this.tenantId = tenantId;
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#mnemonic() mnemonic} attribute.
     * @param mnemonic The value for mnemonic 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("mnemonic")
    public final Tenant.Builder mnemonic(String mnemonic) {
      this.mnemonic = Objects.requireNonNull(mnemonic, "mnemonic");
      initBits &= ~INIT_BIT_MNEMONIC;
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#name() name} attribute.
     * @param name The value for name 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("name")
    public final Tenant.Builder name(String name) {
      this.name = Objects.requireNonNull(name, "name");
      initBits &= ~INIT_BIT_NAME;
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#locale() locale} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Tenant#locale() locale}.</em>
     * @param locale The value for locale 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("locale")
    public final Tenant.Builder locale(Locale locale) {
      this.locale = Objects.requireNonNull(locale, "locale");
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#subscriptionType() subscriptionType} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Tenant#subscriptionType() subscriptionType}.</em>
     * @param subscriptionType The value for subscriptionType 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("subscriptionType")
    public final Tenant.Builder subscriptionType(SubscriptionType subscriptionType) {
      this.subscriptionType = Objects.requireNonNull(subscriptionType, "subscriptionType");
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#subscriptionStartDate() subscriptionStartDate} attribute.
     * @param subscriptionStartDate The value for subscriptionStartDate (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("subscriptionStartDate")
    public final Tenant.Builder subscriptionStartDate(@Nullable LocalDate subscriptionStartDate) {
      this.subscriptionStartDate = subscriptionStartDate;
      return (Tenant.Builder) this;
    }

    /**
     * Initializes the value for the {@link Tenant#subscriptionEndDate() subscriptionEndDate} attribute.
     * @param subscriptionEndDate The value for subscriptionEndDate (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("subscriptionEndDate")
    public final Tenant.Builder subscriptionEndDate(@Nullable LocalDate subscriptionEndDate) {
      this.subscriptionEndDate = subscriptionEndDate;
      return (Tenant.Builder) this;
    }

    /**
     * Adds one element to {@link Tenant#features() features} list.
     * @param element A features element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Tenant.Builder addFeatures(TenantFeature element) {
      if (this.features == null) {
        this.features = new ArrayList<TenantFeature>();
      }
      this.features.add(Objects.requireNonNull(element, "features element"));
      return (Tenant.Builder) this;
    }

    /**
     * Adds elements to {@link Tenant#features() features} list.
     * @param elements An array of features elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Tenant.Builder addFeatures(TenantFeature... elements) {
      if (this.features == null) {
        this.features = new ArrayList<TenantFeature>();
      }
      for (TenantFeature element : elements) {
        this.features.add(Objects.requireNonNull(element, "features element"));
      }
      return (Tenant.Builder) this;
    }


    /**
     * Sets or replaces all elements for {@link Tenant#features() features} list.
     * @param elements An iterable of features elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("features")
    public final Tenant.Builder features(@Nullable Iterable<? extends TenantFeature> elements) {
      if (elements == null) {
        this.features = null;
        return (Tenant.Builder) this;
      }
      this.features = new ArrayList<TenantFeature>();
      return addAllFeatures(elements);
    }

    /**
     * Adds elements to {@link Tenant#features() features} list.
     * @param elements An iterable of features elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Tenant.Builder addAllFeatures(Iterable<? extends TenantFeature> elements) {
      Objects.requireNonNull(elements, "features element");
      if (this.features == null) {
        this.features = new ArrayList<TenantFeature>();
      }
      for (TenantFeature element : elements) {
        this.features.add(Objects.requireNonNull(element, "features element"));
      }
      return (Tenant.Builder) this;
    }

    /**
     * Builds a new {@link Tenant Tenant}.
     * @return An immutable instance of Tenant
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public Tenant build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableTenant(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_MNEMONIC) != 0) attributes.add("mnemonic");
      if ((initBits & INIT_BIT_NAME) != 0) attributes.add("name");
      return "Cannot build Tenant, some of required attributes are not set " + attributes;
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
