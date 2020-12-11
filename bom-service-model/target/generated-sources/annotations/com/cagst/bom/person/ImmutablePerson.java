package com.cagst.bom.person;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link Person}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Person.Builder()}.
 */
@Generated(from = "Person", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutablePerson implements Person {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long personId;
  private final String givenName;
  private final @Nullable String commonName;
  private final @Nullable String middleName;
  private final String familyName;
  private final @Nullable String prefix;
  private final @Nullable String suffix;
  private final @Nullable LocalDate dateOfBirth;
  private final @Nullable Long genderCd;
  private final @Nullable Long genderIdentityCd;
  private final @Nullable Long maritalStatusCd;
  private final @Nullable Long ethnicityCd;
  private final @Nullable Long raceCd;
  private final Locale locale;

  private ImmutablePerson(ImmutablePerson.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.personId = builder.personId;
    this.givenName = builder.givenName;
    this.commonName = builder.commonName;
    this.middleName = builder.middleName;
    this.familyName = builder.familyName;
    this.prefix = builder.prefix;
    this.suffix = builder.suffix;
    this.dateOfBirth = builder.dateOfBirth;
    this.genderCd = builder.genderCd;
    this.genderIdentityCd = builder.genderIdentityCd;
    this.maritalStatusCd = builder.maritalStatusCd;
    this.ethnicityCd = builder.ethnicityCd;
    this.raceCd = builder.raceCd;
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
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.locale = initShim.locale();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "Person", generator = "Immutables")
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

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      if (localeBuildStage == STAGE_INITIALIZING) attributes.add("locale");
      return "Cannot build Person, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return Person.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return Person.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return Person.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return Person.super.updatedCount();
  }

  private Locale localeInitialize() {
    return Person.super.locale();
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
   * @return The value of the {@code personId} attribute
   */
  @JsonProperty("personId")
  @Override
  public @Nullable Long personId() {
    return personId;
  }

  /**
   * @return The value of the {@code givenName} attribute
   */
  @JsonProperty("givenName")
  @Override
  public String givenName() {
    return givenName;
  }

  /**
   * @return The value of the {@code commonName} attribute
   */
  @JsonProperty("commonName")
  @Override
  public @Nullable String commonName() {
    return commonName;
  }

  /**
   * @return The value of the {@code middleName} attribute
   */
  @JsonProperty("middleName")
  @Override
  public @Nullable String middleName() {
    return middleName;
  }

  /**
   * @return The value of the {@code familyName} attribute
   */
  @JsonProperty("familyName")
  @Override
  public String familyName() {
    return familyName;
  }

  /**
   * @return The value of the {@code prefix} attribute
   */
  @JsonProperty("prefix")
  @Override
  public @Nullable String prefix() {
    return prefix;
  }

  /**
   * @return The value of the {@code suffix} attribute
   */
  @JsonProperty("suffix")
  @Override
  public @Nullable String suffix() {
    return suffix;
  }

  /**
   * @return The value of the {@code dateOfBirth} attribute
   */
  @JsonProperty("dateOfBirth")
  @Override
  public @Nullable LocalDate dateOfBirth() {
    return dateOfBirth;
  }

  /**
   * @return The value of the {@code genderCd} attribute
   */
  @JsonProperty("genderCd")
  @Override
  public @Nullable Long genderCd() {
    return genderCd;
  }

  /**
   * @return The value of the {@code genderIdentityCd} attribute
   */
  @JsonProperty("genderIdentityCd")
  @Override
  public @Nullable Long genderIdentityCd() {
    return genderIdentityCd;
  }

  /**
   * @return The value of the {@code maritalStatusCd} attribute
   */
  @JsonProperty("maritalStatusCd")
  @Override
  public @Nullable Long maritalStatusCd() {
    return maritalStatusCd;
  }

  /**
   * @return The value of the {@code ethnicityCd} attribute
   */
  @JsonProperty("ethnicityCd")
  @Override
  public @Nullable Long ethnicityCd() {
    return ethnicityCd;
  }

  /**
   * @return The value of the {@code raceCd} attribute
   */
  @JsonProperty("raceCd")
  @Override
  public @Nullable Long raceCd() {
    return raceCd;
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
   * This instance is equal to all instances of {@code ImmutablePerson} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutablePerson
        && equalTo((ImmutablePerson) another);
  }

  private boolean equalTo(ImmutablePerson another) {
    return active == another.active
        && givenName.equals(another.givenName)
        && Objects.equals(commonName, another.commonName)
        && Objects.equals(middleName, another.middleName)
        && familyName.equals(another.familyName)
        && Objects.equals(prefix, another.prefix)
        && Objects.equals(suffix, another.suffix)
        && Objects.equals(dateOfBirth, another.dateOfBirth)
        && Objects.equals(genderCd, another.genderCd)
        && Objects.equals(genderIdentityCd, another.genderIdentityCd)
        && Objects.equals(maritalStatusCd, another.maritalStatusCd)
        && Objects.equals(ethnicityCd, another.ethnicityCd)
        && Objects.equals(raceCd, another.raceCd)
        && locale.equals(another.locale);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code givenName}, {@code commonName}, {@code middleName}, {@code familyName}, {@code prefix}, {@code suffix}, {@code dateOfBirth}, {@code genderCd}, {@code genderIdentityCd}, {@code maritalStatusCd}, {@code ethnicityCd}, {@code raceCd}, {@code locale}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + givenName.hashCode();
    h += (h << 5) + Objects.hashCode(commonName);
    h += (h << 5) + Objects.hashCode(middleName);
    h += (h << 5) + familyName.hashCode();
    h += (h << 5) + Objects.hashCode(prefix);
    h += (h << 5) + Objects.hashCode(suffix);
    h += (h << 5) + Objects.hashCode(dateOfBirth);
    h += (h << 5) + Objects.hashCode(genderCd);
    h += (h << 5) + Objects.hashCode(genderIdentityCd);
    h += (h << 5) + Objects.hashCode(maritalStatusCd);
    h += (h << 5) + Objects.hashCode(ethnicityCd);
    h += (h << 5) + Objects.hashCode(raceCd);
    h += (h << 5) + locale.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code Person} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Person{"
        + "active=" + active
        + ", givenName=" + givenName
        + ", commonName=" + commonName
        + ", middleName=" + middleName
        + ", familyName=" + familyName
        + ", prefix=" + prefix
        + ", suffix=" + suffix
        + ", dateOfBirth=" + dateOfBirth
        + ", genderCd=" + genderCd
        + ", genderIdentityCd=" + genderIdentityCd
        + ", maritalStatusCd=" + maritalStatusCd
        + ", ethnicityCd=" + ethnicityCd
        + ", raceCd=" + raceCd
        + ", locale=" + locale
        + "}";
  }

  /**
   * Builds instances of type {@link Person Person}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Person", generator = "Immutables")
  @JsonPropertyOrder({"personId", "givenName", "commonName", "middleName", "familyName", "prefix", "suffix", "dateOfBirth", "genderCd", "genderIdentityCd", "maritalStatusCd", "ethnicityCd", "raceCd", "locale", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_GIVEN_NAME = 0x1L;
    private static final long INIT_BIT_FAMILY_NAME = 0x2L;
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
    private Long personId;
    private String givenName;
    private String commonName;
    private String middleName;
    private String familyName;
    private String prefix;
    private String suffix;
    private LocalDate dateOfBirth;
    private Long genderCd;
    private Long genderIdentityCd;
    private Long maritalStatusCd;
    private Long ethnicityCd;
    private Long raceCd;
    private Locale locale;

    /**
     * Creates a builder for {@link Person Person} instances.
     * <pre>
     * new Person.Builder()
     *    .createdId(Long | null) // nullable {@link Person#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link Person#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link Person#active() active}
     *    .updatedId(Long | null) // nullable {@link Person#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link Person#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link Person#updatedCount() updatedCount}
     *    .personId(Long | null) // nullable {@link Person#personId() personId}
     *    .givenName(String) // required {@link Person#givenName() givenName}
     *    .commonName(String | null) // nullable {@link Person#commonName() commonName}
     *    .middleName(String | null) // nullable {@link Person#middleName() middleName}
     *    .familyName(String) // required {@link Person#familyName() familyName}
     *    .prefix(String | null) // nullable {@link Person#prefix() prefix}
     *    .suffix(String | null) // nullable {@link Person#suffix() suffix}
     *    .dateOfBirth(java.time.LocalDate | null) // nullable {@link Person#dateOfBirth() dateOfBirth}
     *    .genderCd(Long | null) // nullable {@link Person#genderCd() genderCd}
     *    .genderIdentityCd(Long | null) // nullable {@link Person#genderIdentityCd() genderIdentityCd}
     *    .maritalStatusCd(Long | null) // nullable {@link Person#maritalStatusCd() maritalStatusCd}
     *    .ethnicityCd(Long | null) // nullable {@link Person#ethnicityCd() ethnicityCd}
     *    .raceCd(Long | null) // nullable {@link Person#raceCd() raceCd}
     *    .locale(Locale) // optional {@link Person#locale() locale}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof Person.Builder)) {
        throw new UnsupportedOperationException("Use: new Person.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.person.Person} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Person.Builder from(Person instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Person.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Person.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Person.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof Person) {
        Person instance = (Person) object;
        String commonNameValue = instance.commonName();
        if (commonNameValue != null) {
          commonName(commonNameValue);
        }
        String prefixValue = instance.prefix();
        if (prefixValue != null) {
          prefix(prefixValue);
        }
        givenName(instance.givenName());
        LocalDate dateOfBirthValue = instance.dateOfBirth();
        if (dateOfBirthValue != null) {
          dateOfBirth(dateOfBirthValue);
        }
        Long genderIdentityCdValue = instance.genderIdentityCd();
        if (genderIdentityCdValue != null) {
          genderIdentityCd(genderIdentityCdValue);
        }
        String suffixValue = instance.suffix();
        if (suffixValue != null) {
          suffix(suffixValue);
        }
        Long ethnicityCdValue = instance.ethnicityCd();
        if (ethnicityCdValue != null) {
          ethnicityCd(ethnicityCdValue);
        }
        locale(instance.locale());
        Long maritalStatusCdValue = instance.maritalStatusCd();
        if (maritalStatusCdValue != null) {
          maritalStatusCd(maritalStatusCdValue);
        }
        Long genderCdValue = instance.genderCd();
        if (genderCdValue != null) {
          genderCd(genderCdValue);
        }
        familyName(instance.familyName());
        Long personIdValue = instance.personId();
        if (personIdValue != null) {
          personId(personIdValue);
        }
        String middleNameValue = instance.middleName();
        if (middleNameValue != null) {
          middleName(middleNameValue);
        }
        Long raceCdValue = instance.raceCd();
        if (raceCdValue != null) {
          raceCd(raceCdValue);
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
     * Initializes the value for the {@link Person#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final Person.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Person#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final Person.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Person#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final Person.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final Person.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Person#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final Person.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Person#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final Person.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#personId() personId} attribute.
     * @param personId The value for personId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("personId")
    public final Person.Builder personId(@Nullable Long personId) {
      this.personId = personId;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#givenName() givenName} attribute.
     * @param givenName The value for givenName 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("givenName")
    public final Person.Builder givenName(String givenName) {
      this.givenName = Objects.requireNonNull(givenName, "givenName");
      initBits &= ~INIT_BIT_GIVEN_NAME;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#commonName() commonName} attribute.
     * @param commonName The value for commonName (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("commonName")
    public final Person.Builder commonName(@Nullable String commonName) {
      this.commonName = commonName;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#middleName() middleName} attribute.
     * @param middleName The value for middleName (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("middleName")
    public final Person.Builder middleName(@Nullable String middleName) {
      this.middleName = middleName;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#familyName() familyName} attribute.
     * @param familyName The value for familyName 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("familyName")
    public final Person.Builder familyName(String familyName) {
      this.familyName = Objects.requireNonNull(familyName, "familyName");
      initBits &= ~INIT_BIT_FAMILY_NAME;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#prefix() prefix} attribute.
     * @param prefix The value for prefix (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("prefix")
    public final Person.Builder prefix(@Nullable String prefix) {
      this.prefix = prefix;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#suffix() suffix} attribute.
     * @param suffix The value for suffix (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("suffix")
    public final Person.Builder suffix(@Nullable String suffix) {
      this.suffix = suffix;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#dateOfBirth() dateOfBirth} attribute.
     * @param dateOfBirth The value for dateOfBirth (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("dateOfBirth")
    public final Person.Builder dateOfBirth(@Nullable LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#genderCd() genderCd} attribute.
     * @param genderCd The value for genderCd (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("genderCd")
    public final Person.Builder genderCd(@Nullable Long genderCd) {
      this.genderCd = genderCd;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#genderIdentityCd() genderIdentityCd} attribute.
     * @param genderIdentityCd The value for genderIdentityCd (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("genderIdentityCd")
    public final Person.Builder genderIdentityCd(@Nullable Long genderIdentityCd) {
      this.genderIdentityCd = genderIdentityCd;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#maritalStatusCd() maritalStatusCd} attribute.
     * @param maritalStatusCd The value for maritalStatusCd (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("maritalStatusCd")
    public final Person.Builder maritalStatusCd(@Nullable Long maritalStatusCd) {
      this.maritalStatusCd = maritalStatusCd;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#ethnicityCd() ethnicityCd} attribute.
     * @param ethnicityCd The value for ethnicityCd (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("ethnicityCd")
    public final Person.Builder ethnicityCd(@Nullable Long ethnicityCd) {
      this.ethnicityCd = ethnicityCd;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#raceCd() raceCd} attribute.
     * @param raceCd The value for raceCd (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("raceCd")
    public final Person.Builder raceCd(@Nullable Long raceCd) {
      this.raceCd = raceCd;
      return (Person.Builder) this;
    }

    /**
     * Initializes the value for the {@link Person#locale() locale} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Person#locale() locale}.</em>
     * @param locale The value for locale 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("locale")
    public final Person.Builder locale(Locale locale) {
      this.locale = Objects.requireNonNull(locale, "locale");
      return (Person.Builder) this;
    }

    /**
     * Builds a new {@link Person Person}.
     * @return An immutable instance of Person
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public Person build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutablePerson(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_GIVEN_NAME) != 0) attributes.add("givenName");
      if ((initBits & INIT_BIT_FAMILY_NAME) != 0) attributes.add("familyName");
      return "Cannot build Person, some of required attributes are not set " + attributes;
    }
  }
}
