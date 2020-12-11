package com.cagst.bom.security.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link ChangePasswordRequest}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new ChangePasswordRequest.Builder()}.
 */
@Generated(from = "ChangePasswordRequest", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableChangePasswordRequest
    implements ChangePasswordRequest {
  private final int tenantId;
  private final String newPassword;
  private final String confirmationPassword;

  private ImmutableChangePasswordRequest(ImmutableChangePasswordRequest.Builder builder) {
    this.tenantId = builder.tenantId;
    this.newPassword = builder.newPassword;
    this.confirmationPassword = builder.confirmationPassword;
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
   * @return The value of the {@code newPassword} attribute
   */
  @JsonProperty("newPassword")
  @Override
  public String newPassword() {
    return newPassword;
  }

  /**
   * @return The value of the {@code confirmationPassword} attribute
   */
  @JsonProperty("confirmationPassword")
  @Override
  public String confirmationPassword() {
    return confirmationPassword;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableChangePasswordRequest} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableChangePasswordRequest
        && equalTo((ImmutableChangePasswordRequest) another);
  }

  private boolean equalTo(ImmutableChangePasswordRequest another) {
    return tenantId == another.tenantId
        && newPassword.equals(another.newPassword)
        && confirmationPassword.equals(another.confirmationPassword);
  }

  /**
   * Computes a hash code from attributes: {@code tenantId}, {@code newPassword}, {@code confirmationPassword}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + tenantId;
    h += (h << 5) + newPassword.hashCode();
    h += (h << 5) + confirmationPassword.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code ChangePasswordRequest} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "ChangePasswordRequest{"
        + "tenantId=" + tenantId
        + ", newPassword=" + newPassword
        + ", confirmationPassword=" + confirmationPassword
        + "}";
  }

  /**
   * Builds instances of type {@link ChangePasswordRequest ChangePasswordRequest}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "ChangePasswordRequest", generator = "Immutables")
  @JsonPropertyOrder({"tenantId", "newPassword", "confirmationPassword"})
  public static class Builder {
    private static final long INIT_BIT_TENANT_ID = 0x1L;
    private static final long INIT_BIT_NEW_PASSWORD = 0x2L;
    private static final long INIT_BIT_CONFIRMATION_PASSWORD = 0x4L;
    private long initBits = 0x7L;

    private int tenantId;
    private String newPassword;
    private String confirmationPassword;

    /**
     * Creates a builder for {@link ChangePasswordRequest ChangePasswordRequest} instances.
     * <pre>
     * new ChangePasswordRequest.Builder()
     *    .tenantId(int) // required {@link ChangePasswordRequest#tenantId() tenantId}
     *    .newPassword(String) // required {@link ChangePasswordRequest#newPassword() newPassword}
     *    .confirmationPassword(String) // required {@link ChangePasswordRequest#confirmationPassword() confirmationPassword}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof ChangePasswordRequest.Builder)) {
        throw new UnsupportedOperationException("Use: new ChangePasswordRequest.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code ChangePasswordRequest} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final ChangePasswordRequest.Builder from(ChangePasswordRequest instance) {
      Objects.requireNonNull(instance, "instance");
      tenantId(instance.tenantId());
      newPassword(instance.newPassword());
      confirmationPassword(instance.confirmationPassword());
      return (ChangePasswordRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link ChangePasswordRequest#tenantId() tenantId} attribute.
     * @param tenantId The value for tenantId 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("tenantId")
    public final ChangePasswordRequest.Builder tenantId(int tenantId) {
      this.tenantId = tenantId;
      initBits &= ~INIT_BIT_TENANT_ID;
      return (ChangePasswordRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link ChangePasswordRequest#newPassword() newPassword} attribute.
     * @param newPassword The value for newPassword 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("newPassword")
    public final ChangePasswordRequest.Builder newPassword(String newPassword) {
      this.newPassword = Objects.requireNonNull(newPassword, "newPassword");
      initBits &= ~INIT_BIT_NEW_PASSWORD;
      return (ChangePasswordRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link ChangePasswordRequest#confirmationPassword() confirmationPassword} attribute.
     * @param confirmationPassword The value for confirmationPassword 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("confirmationPassword")
    public final ChangePasswordRequest.Builder confirmationPassword(String confirmationPassword) {
      this.confirmationPassword = Objects.requireNonNull(confirmationPassword, "confirmationPassword");
      initBits &= ~INIT_BIT_CONFIRMATION_PASSWORD;
      return (ChangePasswordRequest.Builder) this;
    }

    /**
     * Builds a new {@link ChangePasswordRequest ChangePasswordRequest}.
     * @return An immutable instance of ChangePasswordRequest
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public ChangePasswordRequest build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableChangePasswordRequest(this);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_TENANT_ID) != 0) attributes.add("tenantId");
      if ((initBits & INIT_BIT_NEW_PASSWORD) != 0) attributes.add("newPassword");
      if ((initBits & INIT_BIT_CONFIRMATION_PASSWORD) != 0) attributes.add("confirmationPassword");
      return "Cannot build ChangePasswordRequest, some of required attributes are not set " + attributes;
    }
  }
}
