package com.cagst.bom.dictionary.value.attribute;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.dictionary.attribute.DictionaryAttributeType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link DictionaryValueAttribute}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new DictionaryValueAttribute.Builder()}.
 */
@Generated(from = "DictionaryValueAttribute", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableDictionaryValueAttribute
    implements DictionaryValueAttribute {
  private final @Nullable Long createdId;
  private final OffsetDateTime createdDateTime;
  private final boolean active;
  private final @Nullable Long updatedId;
  private final OffsetDateTime updatedDateTime;
  private final long updatedCount;
  private final @Nullable Long dictionaryValueAttributeId;
  private final Long dictionaryValueId;
  private final Long dictionaryAttributeId;
  private final String display;
  private final String meaning;
  private final @Nullable String description;
  private final DictionaryAttributeType attributeType;
  private final String attributeValue;
  private final boolean viewable;
  private final boolean editable;
  private final boolean deletable;
  private final @Nullable Integer sortOrder;

  private ImmutableDictionaryValueAttribute(ImmutableDictionaryValueAttribute.Builder builder) {
    this.createdId = builder.createdId;
    this.updatedId = builder.updatedId;
    this.dictionaryValueAttributeId = builder.dictionaryValueAttributeId;
    this.dictionaryValueId = builder.dictionaryValueId;
    this.dictionaryAttributeId = builder.dictionaryAttributeId;
    this.display = builder.display;
    this.meaning = builder.meaning;
    this.description = builder.description;
    this.attributeType = builder.attributeType;
    this.attributeValue = builder.attributeValue;
    this.sortOrder = builder.sortOrder;
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

  @Generated(from = "DictionaryValueAttribute", generator = "Immutables")
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
      return "Cannot build DictionaryValueAttribute, attribute initializers form cycle " + attributes;
    }
  }

  private OffsetDateTime createdDateTimeInitialize() {
    return DictionaryValueAttribute.super.createdDateTime();
  }

  private boolean activeInitialize() {
    return DictionaryValueAttribute.super.active();
  }

  private OffsetDateTime updatedDateTimeInitialize() {
    return DictionaryValueAttribute.super.updatedDateTime();
  }

  private long updatedCountInitialize() {
    return DictionaryValueAttribute.super.updatedCount();
  }

  private boolean viewableInitialize() {
    return DictionaryValueAttribute.super.viewable();
  }

  private boolean editableInitialize() {
    return DictionaryValueAttribute.super.editable();
  }

  private boolean deletableInitialize() {
    return DictionaryValueAttribute.super.deletable();
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
   * @return The value of the {@code dictionaryValueAttributeId} attribute
   */
  @JsonProperty("dictionaryValueAttributeId")
  @Override
  public @Nullable Long dictionaryValueAttributeId() {
    return dictionaryValueAttributeId;
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
   * @return The value of the {@code dictionaryAttributeId} attribute
   */
  @JsonProperty("dictionaryAttributeId")
  @Override
  public Long dictionaryAttributeId() {
    return dictionaryAttributeId;
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
   * @return The value of the {@code attributeType} attribute
   */
  @JsonProperty("attributeType")
  @Override
  public DictionaryAttributeType attributeType() {
    return attributeType;
  }

  /**
   * @return The value of the {@code attributeValue} attribute
   */
  @JsonProperty("attributeValue")
  @Override
  public String attributeValue() {
    return attributeValue;
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
   * This instance is equal to all instances of {@code ImmutableDictionaryValueAttribute} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableDictionaryValueAttribute
        && equalTo((ImmutableDictionaryValueAttribute) another);
  }

  private boolean equalTo(ImmutableDictionaryValueAttribute another) {
    return active == another.active
        && dictionaryValueId.equals(another.dictionaryValueId)
        && dictionaryAttributeId.equals(another.dictionaryAttributeId)
        && display.equals(another.display)
        && meaning.equals(another.meaning)
        && Objects.equals(description, another.description)
        && attributeType.equals(another.attributeType)
        && attributeValue.equals(another.attributeValue)
        && viewable == another.viewable
        && editable == another.editable
        && deletable == another.deletable
        && Objects.equals(sortOrder, another.sortOrder);
  }

  /**
   * Computes a hash code from attributes: {@code active}, {@code dictionaryValueId}, {@code dictionaryAttributeId}, {@code display}, {@code meaning}, {@code description}, {@code attributeType}, {@code attributeValue}, {@code viewable}, {@code editable}, {@code deletable}, {@code sortOrder}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + Boolean.hashCode(active);
    h += (h << 5) + dictionaryValueId.hashCode();
    h += (h << 5) + dictionaryAttributeId.hashCode();
    h += (h << 5) + display.hashCode();
    h += (h << 5) + meaning.hashCode();
    h += (h << 5) + Objects.hashCode(description);
    h += (h << 5) + attributeType.hashCode();
    h += (h << 5) + attributeValue.hashCode();
    h += (h << 5) + Boolean.hashCode(viewable);
    h += (h << 5) + Boolean.hashCode(editable);
    h += (h << 5) + Boolean.hashCode(deletable);
    h += (h << 5) + Objects.hashCode(sortOrder);
    return h;
  }

  /**
   * Prints the immutable value {@code DictionaryValueAttribute} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "DictionaryValueAttribute{"
        + "active=" + active
        + ", dictionaryValueId=" + dictionaryValueId
        + ", dictionaryAttributeId=" + dictionaryAttributeId
        + ", display=" + display
        + ", meaning=" + meaning
        + ", description=" + description
        + ", attributeType=" + attributeType
        + ", attributeValue=" + attributeValue
        + ", viewable=" + viewable
        + ", editable=" + editable
        + ", deletable=" + deletable
        + ", sortOrder=" + sortOrder
        + "}";
  }

  /**
   * Builds instances of type {@link DictionaryValueAttribute DictionaryValueAttribute}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "DictionaryValueAttribute", generator = "Immutables")
  @JsonPropertyOrder({"dictionaryValueAttributeId", "dictionaryValueId", "dictionaryAttributeId", "display", "meaning", "description", "attributeType", "attributeValue", "viewable", "editable", "deletable", "sortOrder", "createdId", "createdDateTime", "active", "updatedId", "updatedDateTime", "updatedCount"})
  public static class Builder {
    private static final long INIT_BIT_DICTIONARY_VALUE_ID = 0x1L;
    private static final long INIT_BIT_DICTIONARY_ATTRIBUTE_ID = 0x2L;
    private static final long INIT_BIT_DISPLAY = 0x4L;
    private static final long INIT_BIT_MEANING = 0x8L;
    private static final long INIT_BIT_ATTRIBUTE_TYPE = 0x10L;
    private static final long INIT_BIT_ATTRIBUTE_VALUE = 0x20L;
    private static final long OPT_BIT_ACTIVE = 0x1L;
    private static final long OPT_BIT_UPDATED_COUNT = 0x2L;
    private static final long OPT_BIT_VIEWABLE = 0x4L;
    private static final long OPT_BIT_EDITABLE = 0x8L;
    private static final long OPT_BIT_DELETABLE = 0x10L;
    private long initBits = 0x3fL;
    private long optBits;

    private Long createdId;
    private OffsetDateTime createdDateTime;
    private boolean active;
    private Long updatedId;
    private OffsetDateTime updatedDateTime;
    private long updatedCount;
    private Long dictionaryValueAttributeId;
    private Long dictionaryValueId;
    private Long dictionaryAttributeId;
    private String display;
    private String meaning;
    private String description;
    private DictionaryAttributeType attributeType;
    private String attributeValue;
    private boolean viewable;
    private boolean editable;
    private boolean deletable;
    private Integer sortOrder;

    /**
     * Creates a builder for {@link DictionaryValueAttribute DictionaryValueAttribute} instances.
     * <pre>
     * new DictionaryValueAttribute.Builder()
     *    .createdId(Long | null) // nullable {@link DictionaryValueAttribute#createdId() createdId}
     *    .createdDateTime(java.time.OffsetDateTime) // optional {@link DictionaryValueAttribute#createdDateTime() createdDateTime}
     *    .active(boolean) // optional {@link DictionaryValueAttribute#active() active}
     *    .updatedId(Long | null) // nullable {@link DictionaryValueAttribute#updatedId() updatedId}
     *    .updatedDateTime(java.time.OffsetDateTime) // optional {@link DictionaryValueAttribute#updatedDateTime() updatedDateTime}
     *    .updatedCount(long) // optional {@link DictionaryValueAttribute#updatedCount() updatedCount}
     *    .dictionaryValueAttributeId(Long | null) // nullable {@link DictionaryValueAttribute#dictionaryValueAttributeId() dictionaryValueAttributeId}
     *    .dictionaryValueId(Long) // required {@link DictionaryValueAttribute#dictionaryValueId() dictionaryValueId}
     *    .dictionaryAttributeId(Long) // required {@link DictionaryValueAttribute#dictionaryAttributeId() dictionaryAttributeId}
     *    .display(String) // required {@link DictionaryValueAttribute#display() display}
     *    .meaning(String) // required {@link DictionaryValueAttribute#meaning() meaning}
     *    .description(String | null) // nullable {@link DictionaryValueAttribute#description() description}
     *    .attributeType(com.cagst.bom.dictionary.attribute.DictionaryAttributeType) // required {@link DictionaryValueAttribute#attributeType() attributeType}
     *    .attributeValue(String) // required {@link DictionaryValueAttribute#attributeValue() attributeValue}
     *    .viewable(boolean) // optional {@link DictionaryValueAttribute#viewable() viewable}
     *    .editable(boolean) // optional {@link DictionaryValueAttribute#editable() editable}
     *    .deletable(boolean) // optional {@link DictionaryValueAttribute#deletable() deletable}
     *    .sortOrder(Integer | null) // nullable {@link DictionaryValueAttribute#sortOrder() sortOrder}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof DictionaryValueAttribute.Builder)) {
        throw new UnsupportedOperationException("Use: new DictionaryValueAttribute.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.dictionary.value.attribute.DictionaryValueAttribute} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValueAttribute.Builder from(DictionaryValueAttribute instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Fill a builder with attribute values from the provided {@code com.cagst.bom.BaseDTO} instance.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final DictionaryValueAttribute.Builder from(BaseDTO instance) {
      Objects.requireNonNull(instance, "instance");
      from((Object) instance);
      return (DictionaryValueAttribute.Builder) this;
    }

    private void from(Object object) {
      if (object instanceof DictionaryValueAttribute) {
        DictionaryValueAttribute instance = (DictionaryValueAttribute) object;
        viewable(instance.viewable());
        dictionaryValueId(instance.dictionaryValueId());
        attributeValue(instance.attributeValue());
        editable(instance.editable());
        meaning(instance.meaning());
        attributeType(instance.attributeType());
        display(instance.display());
        Long dictionaryValueAttributeIdValue = instance.dictionaryValueAttributeId();
        if (dictionaryValueAttributeIdValue != null) {
          dictionaryValueAttributeId(dictionaryValueAttributeIdValue);
        }
        Integer sortOrderValue = instance.sortOrder();
        if (sortOrderValue != null) {
          sortOrder(sortOrderValue);
        }
        deletable(instance.deletable());
        String descriptionValue = instance.description();
        if (descriptionValue != null) {
          description(descriptionValue);
        }
        dictionaryAttributeId(instance.dictionaryAttributeId());
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
     * Initializes the value for the {@link DictionaryValueAttribute#createdId() createdId} attribute.
     * @param createdId The value for createdId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdId")
    public final DictionaryValueAttribute.Builder createdId(@Nullable Long createdId) {
      this.createdId = createdId;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#createdDateTime() createdDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueAttribute#createdDateTime() createdDateTime}.</em>
     * @param createdDateTime The value for createdDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("createdDateTime")
    public final DictionaryValueAttribute.Builder createdDateTime(OffsetDateTime createdDateTime) {
      this.createdDateTime = Objects.requireNonNull(createdDateTime, "createdDateTime");
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#active() active} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueAttribute#active() active}.</em>
     * @param active The value for active 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("active")
    public final DictionaryValueAttribute.Builder active(boolean active) {
      this.active = active;
      optBits |= OPT_BIT_ACTIVE;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#updatedId() updatedId} attribute.
     * @param updatedId The value for updatedId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedId")
    public final DictionaryValueAttribute.Builder updatedId(@Nullable Long updatedId) {
      this.updatedId = updatedId;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#updatedDateTime() updatedDateTime} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueAttribute#updatedDateTime() updatedDateTime}.</em>
     * @param updatedDateTime The value for updatedDateTime 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedDateTime")
    public final DictionaryValueAttribute.Builder updatedDateTime(OffsetDateTime updatedDateTime) {
      this.updatedDateTime = Objects.requireNonNull(updatedDateTime, "updatedDateTime");
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#updatedCount() updatedCount} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueAttribute#updatedCount() updatedCount}.</em>
     * @param updatedCount The value for updatedCount 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("updatedCount")
    public final DictionaryValueAttribute.Builder updatedCount(long updatedCount) {
      this.updatedCount = updatedCount;
      optBits |= OPT_BIT_UPDATED_COUNT;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#dictionaryValueAttributeId() dictionaryValueAttributeId} attribute.
     * @param dictionaryValueAttributeId The value for dictionaryValueAttributeId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("dictionaryValueAttributeId")
    public final DictionaryValueAttribute.Builder dictionaryValueAttributeId(@Nullable Long dictionaryValueAttributeId) {
      this.dictionaryValueAttributeId = dictionaryValueAttributeId;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#dictionaryValueId() dictionaryValueId} attribute.
     * @param dictionaryValueId The value for dictionaryValueId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("dictionaryValueId")
    public final DictionaryValueAttribute.Builder dictionaryValueId(Long dictionaryValueId) {
      this.dictionaryValueId = Objects.requireNonNull(dictionaryValueId, "dictionaryValueId");
      initBits &= ~INIT_BIT_DICTIONARY_VALUE_ID;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#dictionaryAttributeId() dictionaryAttributeId} attribute.
     * @param dictionaryAttributeId The value for dictionaryAttributeId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("dictionaryAttributeId")
    public final DictionaryValueAttribute.Builder dictionaryAttributeId(Long dictionaryAttributeId) {
      this.dictionaryAttributeId = Objects.requireNonNull(dictionaryAttributeId, "dictionaryAttributeId");
      initBits &= ~INIT_BIT_DICTIONARY_ATTRIBUTE_ID;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#display() display} attribute.
     * @param display The value for display 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("display")
    public final DictionaryValueAttribute.Builder display(String display) {
      this.display = Objects.requireNonNull(display, "display");
      initBits &= ~INIT_BIT_DISPLAY;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#meaning() meaning} attribute.
     * @param meaning The value for meaning 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("meaning")
    public final DictionaryValueAttribute.Builder meaning(String meaning) {
      this.meaning = Objects.requireNonNull(meaning, "meaning");
      initBits &= ~INIT_BIT_MEANING;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#description() description} attribute.
     * @param description The value for description (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("description")
    public final DictionaryValueAttribute.Builder description(@Nullable String description) {
      this.description = description;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#attributeType() attributeType} attribute.
     * @param attributeType The value for attributeType 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("attributeType")
    public final DictionaryValueAttribute.Builder attributeType(DictionaryAttributeType attributeType) {
      this.attributeType = Objects.requireNonNull(attributeType, "attributeType");
      initBits &= ~INIT_BIT_ATTRIBUTE_TYPE;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#attributeValue() attributeValue} attribute.
     * @param attributeValue The value for attributeValue 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("attributeValue")
    public final DictionaryValueAttribute.Builder attributeValue(String attributeValue) {
      this.attributeValue = Objects.requireNonNull(attributeValue, "attributeValue");
      initBits &= ~INIT_BIT_ATTRIBUTE_VALUE;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#viewable() viewable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueAttribute#viewable() viewable}.</em>
     * @param viewable The value for viewable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("viewable")
    public final DictionaryValueAttribute.Builder viewable(boolean viewable) {
      this.viewable = viewable;
      optBits |= OPT_BIT_VIEWABLE;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#editable() editable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueAttribute#editable() editable}.</em>
     * @param editable The value for editable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("editable")
    public final DictionaryValueAttribute.Builder editable(boolean editable) {
      this.editable = editable;
      optBits |= OPT_BIT_EDITABLE;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#deletable() deletable} attribute.
     * <p><em>If not set, this attribute will have a default value as returned by the initializer of {@link DictionaryValueAttribute#deletable() deletable}.</em>
     * @param deletable The value for deletable 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("deletable")
    public final DictionaryValueAttribute.Builder deletable(boolean deletable) {
      this.deletable = deletable;
      optBits |= OPT_BIT_DELETABLE;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Initializes the value for the {@link DictionaryValueAttribute#sortOrder() sortOrder} attribute.
     * @param sortOrder The value for sortOrder (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("sortOrder")
    public final DictionaryValueAttribute.Builder sortOrder(@Nullable Integer sortOrder) {
      this.sortOrder = sortOrder;
      return (DictionaryValueAttribute.Builder) this;
    }

    /**
     * Builds a new {@link DictionaryValueAttribute DictionaryValueAttribute}.
     * @return An immutable instance of DictionaryValueAttribute
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public DictionaryValueAttribute build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableDictionaryValueAttribute(this);
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
      if ((initBits & INIT_BIT_DICTIONARY_ATTRIBUTE_ID) != 0) attributes.add("dictionaryAttributeId");
      if ((initBits & INIT_BIT_DISPLAY) != 0) attributes.add("display");
      if ((initBits & INIT_BIT_MEANING) != 0) attributes.add("meaning");
      if ((initBits & INIT_BIT_ATTRIBUTE_TYPE) != 0) attributes.add("attributeType");
      if ((initBits & INIT_BIT_ATTRIBUTE_VALUE) != 0) attributes.add("attributeValue");
      return "Cannot build DictionaryValueAttribute, some of required attributes are not set " + attributes;
    }
  }
}
