package com.cagst.bom.dictionary;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.dictionary.value.DictionaryValue;
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
 * Immutable implementation of {@link Dictionary}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new Dictionary.Builder()}.
 */
@Generated(from = "Dictionary", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableDictionary implements Dictionary {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long dictionaryId;
  private final String display;
  private final String meaning;
  private final @Nullable String description;
  private final boolean viewable;
  private final boolean editable;
  private final boolean deletable;
  private final @Nullable List<DictionaryValue> values;

  private ImmutableDictionary(ImmutableDictionary.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.dictionaryId = builder.dictionaryId;
    this.display = builder.display;
    this.meaning = builder.meaning;
    this.description = builder.description;
    this.values = builder.values == null ? null : createUnmodifiableList(true, builder.values);
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

  @Generated(from = "Dictionary", generator = "Immutables")
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
      return "Cannot build Dictionary, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return Dictionary.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return Dictionary.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return Dictionary.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return Dictionary.super.updatedCount();
  }

  private boolean viewableInitialize() {
    return Dictionary.super.viewable();
  }

  private boolean editableInitialize() {
    return Dictionary.super.editable();
  }

  private boolean deletableInitialize() {
    return Dictionary.super.deletable();
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
   * @return The value of the {@code dictionaryId} attribute
   */
  @JsonProperty("dictionaryId")
  @Override
  public @Nullable Long dictionaryId() {
    return dictionaryId;
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
   * @return The value of the {@code description} attribute
   */
  @JsonProperty("description")
  @Override
  public @Nullable String description() {
    return description;
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
   * @return The value of the {@code values} attribute
   */
  @JsonProperty("values")
  @Override
  public @Nullable List<DictionaryValue> values() {
    return values;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableDictionary} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableDictionary
        && equalTo((ImmutableDictionary) another);
  }

  private boolean equalTo(ImmutableDictionary another) {
    return active == another.active
        && display.equals(another.display)
        && meaning.equals(another.meaning)
        && Objects.equals(description, another.description)
        && viewable == another.viewable
        && editable == another.editable
        && deletable == another.deletable
        && Objects.equals(values, another.values);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code display}, {@code meaning}, {@code description}, {@code viewable}, {@code editable}, {@code deletable}, {@code values}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + display.hashCode();
    h += (h << 5) + meaning.hashCode();
    h += (h << 5) + Objects.hashCode(description);
    h += (h << 5) + Boolean.hashCode(viewable);
    h += (h << 5) + Boolean.hashCode(editable);
    h += (h << 5) + Boolean.hashCode(deletable);
    h += (h << 5) + Objects.hashCode(values);
    return h;
  }

  /**
   * Prints the immutable value {@code Dictionary} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "Dictionary{"
        + "active=" + active
        + ", display=" + display
        + ", meaning=" + meaning
        + ", description=" + description
        + ", viewable=" + viewable
        + ", editable=" + editable
        + ", deletable=" + deletable
        + ", values=" + values
        + "}";
  }

  /**
   * Builds instances of type {@link Dictionary Dictionary}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "Dictionary", generator = "Immutables")
  @JsonPropertyOrder({"dictionaryId", "display", "meaning", "description", "viewable", "editable", "deletable", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_DISPLAY = 0x1L;
    private static final long INIT_BIT_MEANING = 0x2L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private static final long OPT_BIT_VIEWABLE = 0x4L;
    private static final long OPT_BIT_EDITABLE = 0x8L;
    private static final long OPT_BIT_DELETABLE = 0x10L;
    private long initBits = 0x3L;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long dictionaryId;
    private String display;
    private String meaning;
    private String description;
    private boolean viewable;
    private boolean editable;
    private boolean deletable;
    private List<DictionaryValue> values = null;

    /**
     * Creates a builder for {@link Dictionary Dictionary} instances.
     * <pre>
     * new Dictionary.Builder()
     *    .createdId(Long | null) // nullable {@link Dictionary#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link Dictionary#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link Dictionary#active() active}
     *    .updatedId(Long | null) // nullable {@link Dictionary#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link Dictionary#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link Dictionary#updatedCount() updatedCount}
     *    .dictionaryId(Long | null) // nullable {@link Dictionary#dictionaryId() dictionaryId}
     *    .display(String) // required {@link Dictionary#display() display}
     *    .meaning(String) // required {@link Dictionary#meaning() meaning}
     *    .description(String | null) // nullable {@link Dictionary#description() description}
     *    .viewable(boolean) // optional {@link Dictionary#viewable() viewable}
     *    .editable(boolean) // optional {@link Dictionary#editable() editable}
     *    .deletable(boolean) // optional {@link Dictionary#deletable() deletable}
     *    .values(List&amp;lt;com.cagst.bom.dictionary.value.DictionaryValue&amp;gt; | null) // nullable {@link Dictionary#values() values}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof Dictionary.Builder)) {
        throw new UnsupportedOperationException("Use: new Dictionary.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Dictionary.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Dictionary.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.dictionary.Dictionary} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final Dictionary.Builder from(Dictionary instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (Dictionary.Builder) this;
    }

    private void from(Object object) {
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
      if (object instanceof Dictionary) {
        Dictionary instance = (Dictionary) object;
        viewable(instance.viewable());
        editable(instance.editable());
        meaning(instance.meaning());
        display(instance.display());
        List<DictionaryValue> valuesValue = instance.values();
        if (valuesValue != null) {
          addAllValues(valuesValue);
        }
        deletable(instance.deletable());
        String descriptionValue = instance.description();
        if (descriptionValue != null) {
          description(descriptionValue);
        }
        Long dictionaryIdValue = instance.dictionaryId();
        if (dictionaryIdValue != null) {
          dictionaryId(dictionaryIdValue);
        }
      }
    }

    /**
     * Initializes the value for the {@link Dictionary#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final Dictionary.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Dictionary#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final Dictionary.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Dictionary#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final Dictionary.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final Dictionary.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Dictionary#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final Dictionary.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Dictionary#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final Dictionary.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#dictionaryId() dictionaryId} attribute.
     * @param dictionaryId The value for dictionaryId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("dictionaryId")
    public final Dictionary.Builder dictionaryId(@Nullable Long dictionaryId) {
      this.dictionaryId = dictionaryId;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#display() display} attribute.
     * @param display The value for display 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("display")
    public final Dictionary.Builder display(String display) {
      this.display = Objects.requireNonNull(display, "display");
      initBits &= ~INIT_BIT_DISPLAY;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#meaning() meaning} attribute.
     * @param meaning The value for meaning 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("meaning")
    public final Dictionary.Builder meaning(String meaning) {
      this.meaning = Objects.requireNonNull(meaning, "meaning");
      initBits &= ~INIT_BIT_MEANING;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#description() description} attribute.
     * @param description The value for description (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("description")
    public final Dictionary.Builder description(@Nullable String description) {
      this.description = description;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#viewable() viewable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Dictionary#viewable() viewable}.</em>
     * @param viewable The value for viewable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("viewable")
    public final Dictionary.Builder viewable(boolean viewable) {
      this.viewable = viewable;
      optBits |= OPT_BIT_VIEWABLE;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#editable() editable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Dictionary#editable() editable}.</em>
     * @param editable The value for editable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("editable")
    public final Dictionary.Builder editable(boolean editable) {
      this.editable = editable;
      optBits |= OPT_BIT_EDITABLE;
      return (Dictionary.Builder) this;
    }

    /**
     * Initializes the value for the {@link Dictionary#deletable() deletable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link Dictionary#deletable() deletable}.</em>
     * @param deletable The value for deletable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("deletable")
    public final Dictionary.Builder deletable(boolean deletable) {
      this.deletable = deletable;
      optBits |= OPT_BIT_DELETABLE;
      return (Dictionary.Builder) this;
    }

    /**
     * Adds one element to {@link Dictionary#values() values} list.
     * @param element A values element
     * @return {@code this} builder for use in a chained invocation
     */
    public final Dictionary.Builder addValues(DictionaryValue element) {
      if (this.values == null) {
        this.values = new ArrayList<DictionaryValue>();
      }
      this.values.add(Objects.requireNonNull(element, "values element"));
      return (Dictionary.Builder) this;
    }

    /**
     * Adds elements to {@link Dictionary#values() values} list.
     * @param elements An array of values elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Dictionary.Builder addValues(DictionaryValue... elements) {
      if (this.values == null) {
        this.values = new ArrayList<DictionaryValue>();
      }
      for (DictionaryValue element : elements) {
        this.values.add(Objects.requireNonNull(element, "values element"));
      }
      return (Dictionary.Builder) this;
    }


    /**
     * Sets or replaces all elements for {@link Dictionary#values() values} list.
     * @param elements An iterable of values elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("values")
    public final Dictionary.Builder values(@Nullable Iterable<? extends DictionaryValue> elements) {
      if (elements == null) {
        this.values = null;
        return (Dictionary.Builder) this;
      }
      this.values = new ArrayList<DictionaryValue>();
      return addAllValues(elements);
    }

    /**
     * Adds elements to {@link Dictionary#values() values} list.
     * @param elements An iterable of values elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final Dictionary.Builder addAllValues(Iterable<? extends DictionaryValue> elements) {
      Objects.requireNonNull(elements, "values element");
      if (this.values == null) {
        this.values = new ArrayList<DictionaryValue>();
      }
      for (DictionaryValue element : elements) {
        this.values.add(Objects.requireNonNull(element, "values element"));
      }
      return (Dictionary.Builder) this;
    }

    /**
     * Builds a new {@link Dictionary Dictionary}.
     * @return An immutable instance of Dictionary
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public Dictionary build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableDictionary(this);
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
      if ((initBits & INIT_BIT_DISPLAY) != 0) attributes.add("display");
      if ((initBits & INIT_BIT_MEANING) != 0) attributes.add("meaning");
      return "Cannot build Dictionary, some of required attributes are not set " + attributes;
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
