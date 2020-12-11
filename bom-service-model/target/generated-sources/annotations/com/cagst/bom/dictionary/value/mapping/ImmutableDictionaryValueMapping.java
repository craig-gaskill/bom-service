package com.cagst.bom.dictionary.value.mapping;

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
 * Immutable implementation of {@link DictionaryValueMapping}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new DictionaryValueMapping.Builder()}.
 */
@Generated(from = "DictionaryValueMapping", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableDictionaryValueMapping
    implements DictionaryValueMapping {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long dictionaryValueMappingId;
  private final Long dictionaryValueId;
  private final String display;
  private final String meaning;
  private final String containingSystem;
  private final boolean viewable;
  private final boolean editable;
  private final boolean deletable;

  private ImmutableDictionaryValueMapping(ImmutableDictionaryValueMapping.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.dictionaryValueMappingId = builder.dictionaryValueMappingId;
    this.dictionaryValueId = builder.dictionaryValueId;
    this.display = builder.display;
    this.meaning = builder.meaning;
    this.containingSystem = builder.containingSystem;
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
    if (builder.viewableIsSet()) {
      initShim.viewable(builder.viewable);
    }
    if (builder.editableIsSet()) {
      initShim.editable(builder.editable);
    }
    if (builder.deletableIsSet()) {
      initShim.deletable(builder.deletable);
    }
    this.createdDateTime = initShim.createdDateTime();
    this.active = initShim.active();
    this.updatedDateTime = initShim.updatedDateTime();
    this.updatedCount = initShim.updatedCount();
    this.viewable = initShim.viewable();
    this.editable = initShim.editable();
    this.deletable = initShim.deletable();
    this.initShim = null;
  }

  private static final byte STAGE_INITIALIZING = -1;
  private static final byte STAGE_UNINITIALIZED = 0;
  private static final byte STAGE_INITIALIZED = 1;
  private transient volatile InitShim initShim = new InitShim();

  @Generated(from = "DictionaryValueMapping", generator = "Immutables")
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

    private byte viewableBuildStage = STAGE_UNINITIALIZED;
    private boolean viewable;

    boolean viewable() {
      if (viewableBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (viewableBuildStage == STAGE_UNINITIALIZED) {
        viewableBuildStage = STAGE_INITIALIZING;
        this.viewable = viewableInitialize();
        viewableBuildStage = STAGE_INITIALIZED;
      }
      return this.viewable;
    }

    void viewable(boolean viewable) {
      this.viewable = viewable;
      viewableBuildStage = STAGE_INITIALIZED;
    }

    private byte editableBuildStage = STAGE_UNINITIALIZED;
    private boolean editable;

    boolean editable() {
      if (editableBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (editableBuildStage == STAGE_UNINITIALIZED) {
        editableBuildStage = STAGE_INITIALIZING;
        this.editable = editableInitialize();
        editableBuildStage = STAGE_INITIALIZED;
      }
      return this.editable;
    }

    void editable(boolean editable) {
      this.editable = editable;
      editableBuildStage = STAGE_INITIALIZED;
    }

    private byte deletableBuildStage = STAGE_UNINITIALIZED;
    private boolean deletable;

    boolean deletable() {
      if (deletableBuildStage == STAGE_INITIALIZING) throw new IllegalStateException(formatInitCycleMessage());
      if (deletableBuildStage == STAGE_UNINITIALIZED) {
        deletableBuildStage = STAGE_INITIALIZING;
        this.deletable = deletableInitialize();
        deletableBuildStage = STAGE_INITIALIZED;
      }
      return this.deletable;
    }

    void deletable(boolean deletable) {
      this.deletable = deletable;
      deletableBuildStage = STAGE_INITIALIZED;
    }

    private String formatInitCycleMessage() {
      List<String> attributes = new ArrayList<>();
      if (createdDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("createdDateTime");
      if (activeBuildStage == STAGE_INITIALIZING) attributes.add("active");
      if (updatedDateTimeBuildStage == STAGE_INITIALIZING) attributes.add("updatedDateTime");
      if (updatedCountBuildStage == STAGE_INITIALIZING) attributes.add("updatedCount");
      if (viewableBuildStage == STAGE_INITIALIZING) attributes.add("viewable");
      if (editableBuildStage == STAGE_INITIALIZING) attributes.add("editable");
      if (deletableBuildStage == STAGE_INITIALIZING) attributes.add("deletable");
      return "Cannot build DictionaryValueMapping, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return DictionaryValueMapping.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return DictionaryValueMapping.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return DictionaryValueMapping.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return DictionaryValueMapping.super.updatedCount();
  }

  private boolean viewableInitialize() {
    return DictionaryValueMapping.super.viewable();
  }

  private boolean editableInitialize() {
    return DictionaryValueMapping.super.editable();
  }

  private boolean deletableInitialize() {
    return DictionaryValueMapping.super.deletable();
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
   * @return The value of the {@code dictionaryValueMappingId} attribute
   */
  @JsonProperty("dictionaryValueMappingId")
  @Override
  public @Nullable Long dictionaryValueMappingId() {
    return dictionaryValueMappingId;
  }

  /**
   * @return The value of the {@code dictionaryValueId} attribute
   */
  @JsonProperty("dictionaryValueId")
  @Override
  public Long dictionaryValueId() {
    return dictionaryValueId;
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
   * @return The value of the {@code meaning} attribute
   */
  @JsonProperty("meaning")
  @Override
  public String meaning() {
    return meaning;
  }

  /**
   * @return The value of the {@code containingSystem} attribute
   */
  @JsonProperty("containingSystem")
  @Override
  public String containingSystem() {
    return containingSystem;
  }

  /**
   * @return The value of the {@code viewable} attribute
   */
  @JsonProperty("viewable")
  @Override
  public boolean viewable() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.viewable()
        : this.viewable;
  }

  /**
   * @return The value of the {@code editable} attribute
   */
  @JsonProperty("editable")
  @Override
  public boolean editable() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.editable()
        : this.editable;
  }

  /**
   * @return The value of the {@code deletable} attribute
   */
  @JsonProperty("deletable")
  @Override
  public boolean deletable() {
    InitShim shim = this.initShim;
    return shim != null
        ? shim.deletable()
        : this.deletable;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableDictionaryValueMapping} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableDictionaryValueMapping
        && equalTo((ImmutableDictionaryValueMapping) another);
  }

  private boolean equalTo(ImmutableDictionaryValueMapping another) {
    return active == another.active
        && dictionaryValueId.equals(another.dictionaryValueId)
        && display.equals(another.display)
        && meaning.equals(another.meaning)
        && containingSystem.equals(another.containingSystem)
        && viewable == another.viewable
        && editable == another.editable
        && deletable == another.deletable;
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code dictionaryValueId}, {@code display}, {@code meaning}, {@code containingSystem}, {@code viewable}, {@code editable}, {@code deletable}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + dictionaryValueId.hashCode();
    h += (h << 5) + display.hashCode();
    h += (h << 5) + meaning.hashCode();
    h += (h << 5) + containingSystem.hashCode();
    h += (h << 5) + Boolean.hashCode(viewable);
    h += (h << 5) + Boolean.hashCode(editable);
    h += (h << 5) + Boolean.hashCode(deletable);
    return h;
  }

  /**
   * Prints the immutable value {@code DictionaryValueMapping} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "DictionaryValueMapping{"
        + "active=" + active
        + ", dictionaryValueId=" + dictionaryValueId
        + ", display=" + display
        + ", meaning=" + meaning
        + ", containingSystem=" + containingSystem
        + ", viewable=" + viewable
        + ", editable=" + editable
        + ", deletable=" + deletable
        + "}";
  }

  /**
   * Builds instances of type {@link DictionaryValueMapping DictionaryValueMapping}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "DictionaryValueMapping", generator = "Immutables")
  @JsonPropertyOrder({"dictionaryValueMapping", "dictionaryValueId", "display", "meaning", "containingSystem", "viewable", "editable", "deletable", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_DICTIONARY_VALUE_ID = 0x1L;
    private static final long INIT_BIT_DISPLAY = 0x2L;
    private static final long INIT_BIT_MEANING = 0x4L;
    private static final long INIT_BIT_CONTAINING_SYSTEM = 0x8L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private static final long OPT_BIT_VIEWABLE = 0x4L;
    private static final long OPT_BIT_EDITABLE = 0x8L;
    private static final long OPT_BIT_DELETABLE = 0x10L;
    private long initBits = 0xfL;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long dictionaryValueMappingId;
    private Long dictionaryValueId;
    private String display;
    private String meaning;
    private String containingSystem;
    private boolean viewable;
    private boolean editable;
    private boolean deletable;

    /**
     * Creates a builder for {@link DictionaryValueMapping DictionaryValueMapping} instances.
     * <pre>
     * new DictionaryValueMapping.Builder()
     *    .createdId(Long | null) // nullable {@link DictionaryValueMapping#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link DictionaryValueMapping#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link DictionaryValueMapping#active() active}
     *    .updatedId(Long | null) // nullable {@link DictionaryValueMapping#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link DictionaryValueMapping#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link DictionaryValueMapping#updatedCount() updatedCount}
     *    .dictionaryValueMappingId(Long | null) // nullable {@link DictionaryValueMapping#dictionaryValueMappingId() dictionaryValueMappingId}
     *    .dictionaryValueId(Long) // required {@link DictionaryValueMapping#dictionaryValueId() dictionaryValueId}
     *    .display(String) // required {@link DictionaryValueMapping#display() display}
     *    .meaning(String) // required {@link DictionaryValueMapping#meaning() meaning}
     *    .containingSystem(String) // required {@link DictionaryValueMapping#containingSystem() containingSystem}
     *    .viewable(boolean) // optional {@link DictionaryValueMapping#viewable() viewable}
     *    .editable(boolean) // optional {@link DictionaryValueMapping#editable() editable}
     *    .deletable(boolean) // optional {@link DictionaryValueMapping#deletable() deletable}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof DictionaryValueMapping.Builder)) {
        throw new UnsupportedOperationException("Use: new DictionaryValueMapping.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.dictionary.value.mapping.DictionaryValueMapping} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValueMapping.Builder from(DictionaryValueMapping instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValueMapping.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (DictionaryValueMapping.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof DictionaryValueMapping) {
        DictionaryValueMapping instance = (DictionaryValueMapping) object;
        viewable(instance.viewable());
        containingSystem(instance.containingSystem());
        dictionaryValueId(instance.dictionaryValueId());
        editable(instance.editable());
        meaning(instance.meaning());
        display(instance.display());
        deletable(instance.deletable());
        Long dictionaryValueMappingIdValue = instance.dictionaryValueMappingId();
        if (dictionaryValueMappingIdValue != null) {
          dictionaryValueMappingId(dictionaryValueMappingIdValue);
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
     * Initializes the value for the {@link DictionaryValueMapping#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final DictionaryValueMapping.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueMapping#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final DictionaryValueMapping.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueMapping#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final DictionaryValueMapping.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final DictionaryValueMapping.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueMapping#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final DictionaryValueMapping.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueMapping#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final DictionaryValueMapping.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#dictionaryValueMappingId() dictionaryValueMappingId} attribute.
     * @param dictionaryValueMappingId The value for dictionaryValueMappingId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("dictionaryValueMappingId")
    public final DictionaryValueMapping.Builder dictionaryValueMappingId(@Nullable Long dictionaryValueMappingId) {
      this.dictionaryValueMappingId = dictionaryValueMappingId;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#dictionaryValueId() dictionaryValueId} attribute.
     * @param dictionaryValueId The value for dictionaryValueId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("dictionaryValueId")
    public final DictionaryValueMapping.Builder dictionaryValueId(Long dictionaryValueId) {
      this.dictionaryValueId = Objects.requireNonNull(dictionaryValueId, "dictionaryValueId");
      initBits &= ~INIT_BIT_DICTIONARY_VALUE_ID;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#display() display} attribute.
     * @param display The value for display 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("display")
    public final DictionaryValueMapping.Builder display(String display) {
      this.display = Objects.requireNonNull(display, "display");
      initBits &= ~INIT_BIT_DISPLAY;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#meaning() meaning} attribute.
     * @param meaning The value for meaning 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("meaning")
    public final DictionaryValueMapping.Builder meaning(String meaning) {
      this.meaning = Objects.requireNonNull(meaning, "meaning");
      initBits &= ~INIT_BIT_MEANING;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#containingSystem() containingSystem} attribute.
     * @param containingSystem The value for containingSystem 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("containingSystem")
    public final DictionaryValueMapping.Builder containingSystem(String containingSystem) {
      this.containingSystem = Objects.requireNonNull(containingSystem, "containingSystem");
      initBits &= ~INIT_BIT_CONTAINING_SYSTEM;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#viewable() viewable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueMapping#viewable() viewable}.</em>
     * @param viewable The value for viewable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("viewable")
    public final DictionaryValueMapping.Builder viewable(boolean viewable) {
      this.viewable = viewable;
      optBits |= OPT_BIT_VIEWABLE;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#editable() editable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueMapping#editable() editable}.</em>
     * @param editable The value for editable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("editable")
    public final DictionaryValueMapping.Builder editable(boolean editable) {
      this.editable = editable;
      optBits |= OPT_BIT_EDITABLE;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueMapping#deletable() deletable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueMapping#deletable() deletable}.</em>
     * @param deletable The value for deletable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("deletable")
    public final DictionaryValueMapping.Builder deletable(boolean deletable) {
      this.deletable = deletable;
      optBits |= OPT_BIT_DELETABLE;
      return (DictionaryValueMapping.Builder) this;
    }

    /**
     * Builds a new {@link DictionaryValueMapping DictionaryValueMapping}.
     * @return An immutable instance of DictionaryValueMapping
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public DictionaryValueMapping build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableDictionaryValueMapping(this);
    }

    private boolean activeIsSet() {
      return (optBits & OPT_BIT_ACTIVE) != 0;
    }

    private boolean updatedCountIsSet() {
      return (optBits & OPT_BIT_UPDATED_COUNT) != 0;
    }

    private boolean viewableIsSet() {
      return (optBits & OPT_BIT_VIEWABLE) != 0;
    }

    private boolean editableIsSet() {
      return (optBits & OPT_BIT_EDITABLE) != 0;
    }

    private boolean deletableIsSet() {
      return (optBits & OPT_BIT_DELETABLE) != 0;
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_DICTIONARY_VALUE_ID) != 0) attributes.add("dictionaryValueId");
      if ((initBits & INIT_BIT_DISPLAY) != 0) attributes.add("display");
      if ((initBits & INIT_BIT_MEANING) != 0) attributes.add("meaning");
      if ((initBits & INIT_BIT_CONTAINING_SYSTEM) != 0) attributes.add("containingSystem");
      return "Cannot build DictionaryValueMapping, some of required attributes are not set " + attributes;
    }
  }
}
