package com.cagst.bom.dictionary.value;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.dictionary.value.attribute.DictionaryValueAttribute;
import com.cagst.bom.dictionary.value.mapping.DictionaryValueMapping;
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
 * Immutable implementation of {@link DictionaryValue}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new DictionaryValue.Builder()}.
 */
@Generated(from = "DictionaryValue", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableDictionaryValue implements DictionaryValue {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long dictionaryValueId;
  private final String display;
  private final String meaning;
  private final @Nullable String description;
  private final boolean viewable;
  private final boolean editable;
  private final boolean deletable;
  private final @Nullable Integer sortOrder;
  private final @Nullable List<DictionaryValueMapping> mappings;
  private final @Nullable List<DictionaryValueAttribute> attributes;

  private ImmutableDictionaryValue(ImmutableDictionaryValue.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.dictionaryValueId = builder.dictionaryValueId;
    this.display = builder.display;
    this.meaning = builder.meaning;
    this.description = builder.description;
    this.sortOrder = builder.sortOrder;
    this.mappings = builder.mappings == null ? null : createUnmodifiableList(true, builder.mappings);
    this.attributes = builder.attributes == null ? null : createUnmodifiableList(true, builder.attributes);
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

  @Generated(from = "DictionaryValue", generator = "Immutables")
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
      return "Cannot build DictionaryValue, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return DictionaryValue.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return DictionaryValue.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return DictionaryValue.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return DictionaryValue.super.updatedCount();
  }

  private boolean viewableInitialize() {
    return DictionaryValue.super.viewable();
  }

  private boolean editableInitialize() {
    return DictionaryValue.super.editable();
  }

  private boolean deletableInitialize() {
    return DictionaryValue.super.deletable();
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
   * @return The value of the {@code dictionaryValueId} attribute
   */
  @JsonProperty("dictionaryValueId")
  @Override
  public @Nullable Long dictionaryValueId() {
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
   * @return The value of the {@code sortOrder} attribute
   */
  @JsonProperty("sortOrder")
  @Override
  public @Nullable Integer sortOrder() {
    return sortOrder;
  }

  /**
   * @return The value of the {@code mappings} attribute
   */
  @JsonProperty("mappings")
  @Override
  public @Nullable List<DictionaryValueMapping> mappings() {
    return mappings;
  }

  /**
   * @return The value of the {@code attributes} attribute
   */
  @JsonProperty("attributes")
  @Override
  public @Nullable List<DictionaryValueAttribute> attributes() {
    return attributes;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableDictionaryValue} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableDictionaryValue
        && equalTo((ImmutableDictionaryValue) another);
  }

  private boolean equalTo(ImmutableDictionaryValue another) {
    return active == another.active
        && display.equals(another.display)
        && meaning.equals(another.meaning)
        && Objects.equals(description, another.description)
        && viewable == another.viewable
        && editable == another.editable
        && deletable == another.deletable
        && Objects.equals(sortOrder, another.sortOrder)
        && Objects.equals(mappings, another.mappings)
        && Objects.equals(attributes, another.attributes);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code display}, {@code meaning}, {@code description}, {@code viewable}, {@code editable}, {@code deletable}, {@code sortOrder}, {@code mappings}, {@code attributes}.
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
    h += (h << 5) + Objects.hashCode(sortOrder);
    h += (h << 5) + Objects.hashCode(mappings);
    h += (h << 5) + Objects.hashCode(attributes);
    return h;
  }

  /**
   * Prints the immutable value {@code DictionaryValue} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "DictionaryValue{"
        + "active=" + active
        + ", display=" + display
        + ", meaning=" + meaning
        + ", description=" + description
        + ", viewable=" + viewable
        + ", editable=" + editable
        + ", deletable=" + deletable
        + ", sortOrder=" + sortOrder
        + ", mappings=" + mappings
        + ", attributes=" + attributes
        + "}";
  }

  /**
   * Builds instances of type {@link DictionaryValue DictionaryValue}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "DictionaryValue", generator = "Immutables")
  @JsonPropertyOrder({"dictionaryValueId", "display", "meaning", "description", "viewable", "editable", "deletable", "sortOrder", "mappings", "attributes", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
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
    private Long dictionaryValueId;
    private String display;
    private String meaning;
    private String description;
    private boolean viewable;
    private boolean editable;
    private boolean deletable;
    private Integer sortOrder;
    private List<DictionaryValueMapping> mappings = null;
    private List<DictionaryValueAttribute> attributes = null;

    /**
     * Creates a builder for {@link DictionaryValue DictionaryValue} instances.
     * <pre>
     * new DictionaryValue.Builder()
     *    .createdId(Long | null) // nullable {@link DictionaryValue#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link DictionaryValue#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link DictionaryValue#active() active}
     *    .updatedId(Long | null) // nullable {@link DictionaryValue#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link DictionaryValue#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link DictionaryValue#updatedCount() updatedCount}
     *    .dictionaryValueId(Long | null) // nullable {@link DictionaryValue#dictionaryValueId() dictionaryValueId}
     *    .display(String) // required {@link DictionaryValue#display() display}
     *    .meaning(String) // required {@link DictionaryValue#meaning() meaning}
     *    .description(String | null) // nullable {@link DictionaryValue#description() description}
     *    .viewable(boolean) // optional {@link DictionaryValue#viewable() viewable}
     *    .editable(boolean) // optional {@link DictionaryValue#editable() editable}
     *    .deletable(boolean) // optional {@link DictionaryValue#deletable() deletable}
     *    .sortOrder(Integer | null) // nullable {@link DictionaryValue#sortOrder() sortOrder}
     *    .mappings(List&amp;lt;com.cagst.bom.dictionary.value.mapping.DictionaryValueMapping&amp;gt; | null) // nullable {@link DictionaryValue#mappings() mappings}
     *    .attributes(List&amp;lt;com.cagst.bom.dictionary.value.attribute.DictionaryValueAttribute&amp;gt; | null) // nullable {@link DictionaryValue#attributes() attributes}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof DictionaryValue.Builder)) {
        throw new UnsupportedOperationException("Use: new DictionaryValue.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.dictionary.value.DictionaryValue} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValue.Builder from(DictionaryValue instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (DictionaryValue.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValue.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (DictionaryValue.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof DictionaryValue) {
        DictionaryValue instance = (DictionaryValue) object;
        List<DictionaryValueMapping> mappingsValue = instance.mappings();
        if (mappingsValue != null) {
          addAllMappings(mappingsValue);
        }
        viewable(instance.viewable());
        Long dictionaryValueIdValue = instance.dictionaryValueId();
        if (dictionaryValueIdValue != null) {
          dictionaryValueId(dictionaryValueIdValue);
        }
        editable(instance.editable());
        meaning(instance.meaning());
        display(instance.display());
        Integer sortOrderValue = instance.sortOrder();
        if (sortOrderValue != null) {
          sortOrder(sortOrderValue);
        }
        deletable(instance.deletable());
        String descriptionValue = instance.description();
        if (descriptionValue != null) {
          description(descriptionValue);
        }
        List<DictionaryValueAttribute> attributesValue = instance.attributes();
        if (attributesValue != null) {
          addAllAttributes(attributesValue);
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
     * Initializes the value for the {@link DictionaryValue#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final DictionaryValue.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValue#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final DictionaryValue.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValue#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final DictionaryValue.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final DictionaryValue.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValue#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final DictionaryValue.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValue#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final DictionaryValue.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#dictionaryValueId() dictionaryValueId} attribute.
     * @param dictionaryValueId The value for dictionaryValueId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("dictionaryValueId")
    public final DictionaryValue.Builder dictionaryValueId(@Nullable Long dictionaryValueId) {
      this.dictionaryValueId = dictionaryValueId;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#display() display} attribute.
     * @param display The value for display 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("display")
    public final DictionaryValue.Builder display(String display) {
      this.display = Objects.requireNonNull(display, "display");
      initBits &= ~INIT_BIT_DISPLAY;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#meaning() meaning} attribute.
     * @param meaning The value for meaning 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("meaning")
    public final DictionaryValue.Builder meaning(String meaning) {
      this.meaning = Objects.requireNonNull(meaning, "meaning");
      initBits &= ~INIT_BIT_MEANING;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#description() description} attribute.
     * @param description The value for description (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("description")
    public final DictionaryValue.Builder description(@Nullable String description) {
      this.description = description;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#viewable() viewable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValue#viewable() viewable}.</em>
     * @param viewable The value for viewable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("viewable")
    public final DictionaryValue.Builder viewable(boolean viewable) {
      this.viewable = viewable;
      optBits |= OPT_BIT_VIEWABLE;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#editable() editable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValue#editable() editable}.</em>
     * @param editable The value for editable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("editable")
    public final DictionaryValue.Builder editable(boolean editable) {
      this.editable = editable;
      optBits |= OPT_BIT_EDITABLE;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#deletable() deletable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValue#deletable() deletable}.</em>
     * @param deletable The value for deletable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("deletable")
    public final DictionaryValue.Builder deletable(boolean deletable) {
      this.deletable = deletable;
      optBits |= OPT_BIT_DELETABLE;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValue#sortOrder() sortOrder} attribute.
     * @param sortOrder The value for sortOrder (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("sortOrder")
    public final DictionaryValue.Builder sortOrder(@Nullable Integer sortOrder) {
      this.sortOrder = sortOrder;
      return (DictionaryValue.Builder) this;
    }

    /**
     * Adds one element to {@link DictionaryValue#mappings() mappings} list.
     * @param element A mappings element
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValue.Builder addMappings(DictionaryValueMapping element) {
      if (this.mappings == null) {
        this.mappings = new ArrayList<DictionaryValueMapping>();
      }
      this.mappings.add(Objects.requireNonNull(element, "mappings element"));
      return (DictionaryValue.Builder) this;
    }

    /**
     * Adds elements to {@link DictionaryValue#mappings() mappings} list.
     * @param elements An array of mappings elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValue.Builder addMappings(DictionaryValueMapping... elements) {
      if (this.mappings == null) {
        this.mappings = new ArrayList<DictionaryValueMapping>();
      }
      for (DictionaryValueMapping element : elements) {
        this.mappings.add(Objects.requireNonNull(element, "mappings element"));
      }
      return (DictionaryValue.Builder) this;
    }


    /**
     * Sets or replaces all elements for {@link DictionaryValue#mappings() mappings} list.
     * @param elements An iterable of mappings elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("mappings")
    public final DictionaryValue.Builder mappings(@Nullable Iterable<? extends DictionaryValueMapping> elements) {
      if (elements == null) {
        this.mappings = null;
        return (DictionaryValue.Builder) this;
      }
      this.mappings = new ArrayList<DictionaryValueMapping>();
      return addAllMappings(elements);
    }

    /**
     * Adds elements to {@link DictionaryValue#mappings() mappings} list.
     * @param elements An iterable of mappings elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValue.Builder addAllMappings(Iterable<? extends DictionaryValueMapping> elements) {
      Objects.requireNonNull(elements, "mappings element");
      if (this.mappings == null) {
        this.mappings = new ArrayList<DictionaryValueMapping>();
      }
      for (DictionaryValueMapping element : elements) {
        this.mappings.add(Objects.requireNonNull(element, "mappings element"));
      }
      return (DictionaryValue.Builder) this;
    }

    /**
     * Adds one element to {@link DictionaryValue#attributes() attributes} list.
     * @param element A attributes element
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValue.Builder addAttributes(DictionaryValueAttribute element) {
      if (this.attributes == null) {
        this.attributes = new ArrayList<DictionaryValueAttribute>();
      }
      this.attributes.add(Objects.requireNonNull(element, "attributes element"));
      return (DictionaryValue.Builder) this;
    }

    /**
     * Adds elements to {@link DictionaryValue#attributes() attributes} list.
     * @param elements An array of attributes elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValue.Builder addAttributes(DictionaryValueAttribute... elements) {
      if (this.attributes == null) {
        this.attributes = new ArrayList<DictionaryValueAttribute>();
      }
      for (DictionaryValueAttribute element : elements) {
        this.attributes.add(Objects.requireNonNull(element, "attributes element"));
      }
      return (DictionaryValue.Builder) this;
    }


    /**
     * Sets or replaces all elements for {@link DictionaryValue#attributes() attributes} list.
     * @param elements An iterable of attributes elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("attributes")
    public final DictionaryValue.Builder attributes(@Nullable Iterable<? extends DictionaryValueAttribute> elements) {
      if (elements == null) {
        this.attributes = null;
        return (DictionaryValue.Builder) this;
      }
      this.attributes = new ArrayList<DictionaryValueAttribute>();
      return addAllAttributes(elements);
    }

    /**
     * Adds elements to {@link DictionaryValue#attributes() attributes} list.
     * @param elements An iterable of attributes elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValue.Builder addAllAttributes(Iterable<? extends DictionaryValueAttribute> elements) {
      Objects.requireNonNull(elements, "attributes element");
      if (this.attributes == null) {
        this.attributes = new ArrayList<DictionaryValueAttribute>();
      }
      for (DictionaryValueAttribute element : elements) {
        this.attributes.add(Objects.requireNonNull(element, "attributes element"));
      }
      return (DictionaryValue.Builder) this;
    }

    /**
     * Builds a new {@link DictionaryValue DictionaryValue}.
     * @return An immutable instance of DictionaryValue
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public DictionaryValue build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableDictionaryValue(this);
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
      return "Cannot build DictionaryValue, some of required attributes are not set " + attributes;
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
