package com.cagst.bom.security.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.immutables.value.Generated;

/**
 * Immutable implementation of {@link LoginRequest}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new LoginRequest.Builder()}.
 */
@Generated(from = "LoginRequest", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableLoginRequest implements LoginRequest {
  private final String username;
  private final String password;

  private ImmutableLoginRequest(ImmutableLoginRequest.Builder builder) {
    this.username = builder.username;
    this.password = builder.password;
  }

  /**
   * @return The value of the {@code username} attribute
   */
  @JsonProperty("username")
  @Override
  public String username() {
    return username;
  }

  /**
   * @return The value of the {@code password} attribute
   */
  @JsonProperty("password")
  @Override
  public String password() {
    return password;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableLoginRequest} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableLoginRequest
        && equalTo((ImmutableLoginRequest) another);
  }

  private boolean equalTo(ImmutableLoginRequest another) {
    return username.equals(another.username)
        && password.equals(another.password);
  }

  /**
   * Computes a hash code from attributes: {@code username}, {@code password}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + username.hashCode();
    h += (h << 5) + password.hashCode();
    return h;
  }

  /**
   * Prints the immutable value {@code LoginRequest} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "LoginRequest{"
        + "username=" + username
        + ", password=" + password
        + "}";
  }

  /**
   * Builds instances of type {@link LoginRequest LoginRequest}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "LoginRequest", generator = "Immutables")
  @JsonPropertyOrder({"username", "password"})
  public static class Builder {
    private static final long INIT_BIT_USERNAME = 0x1L;
    private static final long INIT_BIT_PASSWORD = 0x2L;
    private long initBits = 0x3L;

    private String username;
    private String password;

    /**
     * Creates a builder for {@link LoginRequest LoginRequest} instances.
     * <pre>
     * new LoginRequest.Builder()
     *    .username(String) // required {@link LoginRequest#username() username}
     *    .password(String) // required {@link LoginRequest#password() password}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof LoginRequest.Builder)) {
        throw new UnsupportedOperationException("Use: new LoginRequest.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code LoginRequest} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final LoginRequest.Builder from(LoginRequest instance) {
      Objects.requireNonNull(instance, "instance");
      username(instance.username());
      password(instance.password());
      return (LoginRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link LoginRequest#username() username} attribute.
     * @param username The value for username 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("username")
    public final LoginRequest.Builder username(String username) {
      this.username = Objects.requireNonNull(username, "username");
      initBits &= ~INIT_BIT_USERNAME;
      return (LoginRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link LoginRequest#password() password} attribute.
     * @param password The value for password 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("password")
    public final LoginRequest.Builder password(String password) {
      this.password = Objects.requireNonNull(password, "password");
      initBits &= ~INIT_BIT_PASSWORD;
      return (LoginRequest.Builder) this;
    }

    /**
     * Builds a new {@link LoginRequest LoginRequest}.
     * @return An immutable instance of LoginRequest
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public LoginRequest build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableLoginRequest(this);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_USERNAME) != 0) attributes.add("username");
      if ((initBits & INIT_BIT_PASSWORD) != 0) attributes.add("password");
      return "Cannot build LoginRequest, some of required attributes are not set " + attributes;
    }
  }
}
