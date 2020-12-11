package com.cagst.bom.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link SecurityInfo}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new SecurityInfo.Builder()}.
 */
@Generated(from = "SecurityInfo", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableSecurityInfo implements SecurityInfo {
  private final int tenantId;
  private final long userId;
  private final @Nullable String source;
  private final @Nullable ZoneId zoneId;

  private ImmutableSecurityInfo(ImmutableSecurityInfo.Builder builder) {
    this.tenantId = builder.tenantId;
    this.userId = builder.userId;
    this.source = builder.source;
    this.zoneId = builder.zoneId;
  }

  /**
   * @return The value of the {@code tenantId} attribute
   */
  @JsonProperty("tenantId")
  @Override
  public int tenantId() {
    return tenantId;
  }

  /**
   * @return The value of the {@code userId} attribute
   */
  @JsonProperty("userId")
  @Override
  public long userId() {
    return userId;
  }

  /**
   * @return The value of the {@code source} attribute
   */
  @JsonProperty("source")
  @Override
  public @Nullable String source() {
    return source;
  }

  /**
   * @return The value of the {@code zoneId} attribute
   */
  @JsonProperty("zoneId")
  @Override
  public @Nullable ZoneId zoneId() {
    return zoneId;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableSecurityInfo} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableSecurityInfo
        && equalTo((ImmutableSecurityInfo) another);
  }

  private boolean equalTo(ImmutableSecurityInfo another) {
    return tenantId == another.tenantId
        && userId == another.userId
        && Objects.equals(source, another.source)
        && Objects.equals(zoneId, another.zoneId);
  }

  /**
   * Computes a hash code from attributes: {@code tenantId}, {@code userId}, {@code source}, {@code zoneId}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + tenantId;
    h += (h << 5) + Long.hashCode(userId);
    h += (h << 5) + Objects.hashCode(source);
    h += (h << 5) + Objects.hashCode(zoneId);
    return h;
  }

  /**
   * Prints the immutable value {@code SecurityInfo} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "SecurityInfo{"
        + "tenantId=" + tenantId
        + ", userId=" + userId
        + ", source=" + source
        + ", zoneId=" + zoneId
        + "}";
  }

  /**
   * Builds instances of type {@link SecurityInfo SecurityInfo}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "SecurityInfo", generator = "Immutables")
  @JsonPropertyOrder({"tenantId", "userId", "source", "zoneId"})
  public static class Builder {
    private static final long INIT_BIT_TENANT_ID = 0x1L;
    private static final long INIT_BIT_USER_ID = 0x2L;
    private long initBits = 0x3L;

    private int tenantId;
    private long userId;
    private String source;
    private ZoneId zoneId;

    /**
     * Creates a builder for {@link SecurityInfo SecurityInfo} instances.
     * <pre>
     * new SecurityInfo.Builder()
     *    .tenantId(int) // required {@link SecurityInfo#tenantId() tenantId}
     *    .userId(long) // required {@link SecurityInfo#userId() userId}
     *    .source(String | null) // nullable {@link SecurityInfo#source() source}
     *    .zoneId(java.time.ZoneId | null) // nullable {@link SecurityInfo#zoneId() zoneId}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof SecurityInfo.Builder)) {
        throw new UnsupportedOperationException("Use: new SecurityInfo.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code SecurityInfo} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final SecurityInfo.Builder from(SecurityInfo instance) {
      Objects.requireNonNull(instance, "instance");
      tenantId(instance.tenantId());
      userId(instance.userId());
      String sourceValue = instance.source();
      if (sourceValue != null) {
        source(sourceValue);
      }
      ZoneId zoneIdValue = instance.zoneId();
      if (zoneIdValue != null) {
        zoneId(zoneIdValue);
      }
      return (SecurityInfo.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityInfo#tenantId() tenantId} attribute.
     * @param tenantId The value for tenantId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("tenantId")
    public final SecurityInfo.Builder tenantId(int tenantId) {
      this.tenantId = tenantId;
      initBits &= ~INIT_BIT_TENANT_ID;
      return (SecurityInfo.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityInfo#userId() userId} attribute.
     * @param userId The value for userId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("userId")
    public final SecurityInfo.Builder userId(long userId) {
      this.userId = userId;
      initBits &= ~INIT_BIT_USER_ID;
      return (SecurityInfo.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityInfo#source() source} attribute.
     * @param source The value for source (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("source")
    public final SecurityInfo.Builder source(@Nullable String source) {
      this.source = source;
      return (SecurityInfo.Builder) this;
    }

    /**
     * Initializes the value for the {@link SecurityInfo#zoneId() zoneId} attribute.
     * @param zoneId The value for zoneId (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("zoneId")
    public final SecurityInfo.Builder zoneId(@Nullable ZoneId zoneId) {
      this.zoneId = zoneId;
      return (SecurityInfo.Builder) this;
    }

    /**
     * Builds a new {@link SecurityInfo SecurityInfo}.
     * @return An immutable instance of SecurityInfo
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public SecurityInfo build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableSecurityInfo(this);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_TENANT_ID) != 0) attributes.add("tenantId");
      if ((initBits & INIT_BIT_USER_ID) != 0) attributes.add("userId");
      return "Cannot build SecurityInfo, some of required attributes are not set " + attributes;
    }
  }
}
