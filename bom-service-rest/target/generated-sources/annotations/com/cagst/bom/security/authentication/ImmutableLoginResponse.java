package com.cagst.bom.security.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link LoginResponse}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new LoginResponse.Builder()}.
 */
@Generated(from = "LoginResponse", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableLoginResponse implements LoginResponse {
  private final LoginStatus loginStatus;
  private final @Nullable String accessToken;

  private ImmutableLoginResponse(ImmutableLoginResponse.Builder builder) {
    this.loginStatus = builder.loginStatus;
    this.accessToken = builder.accessToken;
  }

  /**
   * @return The value of the {@code loginStatus} attribute
   */
  @JsonProperty("status")
  @Override
  public LoginStatus loginStatus() {
    return loginStatus;
  }

  /**
   * @return The value of the {@code accessToken} attribute
   */
  @JsonProperty("access")
  @Override
  public @Nullable String accessToken() {
    return accessToken;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableLoginResponse} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableLoginResponse
        && equalTo((ImmutableLoginResponse) another);
  }

  private boolean equalTo(ImmutableLoginResponse another) {
    return loginStatus.equals(another.loginStatus)
        && Objects.equals(accessToken, another.accessToken);
  }

  /**
   * Computes a hash code from attributes: {@code loginStatus}, {@code accessToken}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + loginStatus.hashCode();
    h += (h << 5) + Objects.hashCode(accessToken);
    return h;
  }

  /**
   * Prints the immutable value {@code LoginResponse} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "LoginResponse{"
        + "loginStatus=" + loginStatus
        + ", accessToken=" + accessToken
        + "}";
  }

  /**
   * Builds instances of type {@link LoginResponse LoginResponse}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "LoginResponse", generator = "Immutables")
  @JsonPropertyOrder({"status", "access"})
  public static class Builder {
    private static final long INIT_BIT_LOGIN_STATUS = 0x1L;
    private long initBits = 0x1L;

    private LoginStatus loginStatus;
    private String accessToken;

    /**
     * Creates a builder for {@link LoginResponse LoginResponse} instances.
     * <pre>
     * new LoginResponse.Builder()
     *    .loginStatus(com.cagst.bom.security.authentication.LoginStatus) // required {@link LoginResponse#loginStatus() loginStatus}
     *    .accessToken(String | null) // nullable {@link LoginResponse#accessToken() accessToken}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof LoginResponse.Builder)) {
        throw new UnsupportedOperationException("Use: new LoginResponse.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code LoginResponse} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final LoginResponse.Builder from(LoginResponse instance) {
      Objects.requireNonNull(instance, "instance");
      loginStatus(instance.loginStatus());
      String accessTokenValue = instance.accessToken();
      if (accessTokenValue != null) {
        accessToken(accessTokenValue);
      }
      return (LoginResponse.Builder) this;
    }

    /**
     * Initializes the value for the {@link LoginResponse#loginStatus() loginStatus} attribute.
     * @param loginStatus The value for loginStatus 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("status")
    public final LoginResponse.Builder loginStatus(LoginStatus loginStatus) {
      this.loginStatus = Objects.requireNonNull(loginStatus, "loginStatus");
      initBits &= ~INIT_BIT_LOGIN_STATUS;
      return (LoginResponse.Builder) this;
    }

    /**
     * Initializes the value for the {@link LoginResponse#accessToken() accessToken} attribute.
     * @param accessToken The value for accessToken (can be {@code null})
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("access")
    public final LoginResponse.Builder accessToken(@Nullable String accessToken) {
      this.accessToken = accessToken;
      return (LoginResponse.Builder) this;
    }

    /**
     * Builds a new {@link LoginResponse LoginResponse}.
     * @return An immutable instance of LoginResponse
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public LoginResponse build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableLoginResponse(this);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_LOGIN_STATUS) != 0) attributes.add("loginStatus");
      return "Cannot build LoginResponse, some of required attributes are not set " + attributes;
    }
  }
}
