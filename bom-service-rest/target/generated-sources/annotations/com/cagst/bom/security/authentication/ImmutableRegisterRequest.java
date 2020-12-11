package com.cagst.bom.security.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.immutables.value.Generated;
import org.springframework.lang.Nullable;

/**
 * Immutable implementation of {@link RegisterRequest}.
 * <p>
 * Use the builder to create immutable instances:
 * {@code new RegisterRequest.Builder()}.
 */
@Generated(from = "RegisterRequest", generator = "Immutables")
@SuppressWarnings({"all"})
@javax.annotation.processing.Generated("org.immutables.processor.ProxyProcessor")
final class ImmutableRegisterRequest implements RegisterRequest {
  private final String tenantName;
  private final String firstName;
  private final String lastName;
  private final String email;
  private final String password;
  private final String confirmationPassword;
  private final @Nullable Set<String> features;

  private ImmutableRegisterRequest(ImmutableRegisterRequest.Builder builder) {
    this.tenantName = builder.tenantName;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.email = builder.email;
    this.password = builder.password;
    this.confirmationPassword = builder.confirmationPassword;
    this.features = builder.features == null ? null : createUnmodifiableSet(builder.features);
  }

  /**
   * @return The value of the {@code tenantName} attribute
   */
  @JsonProperty("tenantName")
  @Override
  public String tenantName() {
    return tenantName;
  }

  /**
   * @return The value of the {@code firstName} attribute
   */
  @JsonProperty("firstName")
  @Override
  public String firstName() {
    return firstName;
  }

  /**
   * @return The value of the {@code lastName} attribute
   */
  @JsonProperty("lastName")
  @Override
  public String lastName() {
    return lastName;
  }

  /**
   * @return The value of the {@code email} attribute
   */
  @JsonProperty("email")
  @Override
  public String email() {
    return email;
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
   * @return The value of the {@code confirmationPassword} attribute
   */
  @JsonProperty("confirmationPassword")
  @Override
  public String confirmationPassword() {
    return confirmationPassword;
  }

  /**
   * @return The value of the {@code features} attribute
   */
  @JsonProperty("features")
  @Override
  public @Nullable Set<String> features() {
    return features;
  }

  /**
   * This instance is equal to all instances of {@code ImmutableRegisterRequest} that have equal attribute values.
   * @return {@code true} if {@code this} is equal to {@code another} instance
   */
  @Override
  public boolean equals(Object another) {
    if (this == another) return true;
    return another instanceof ImmutableRegisterRequest
        && equalTo((ImmutableRegisterRequest) another);
  }

  private boolean equalTo(ImmutableRegisterRequest another) {
    return tenantName.equals(another.tenantName)
        && firstName.equals(another.firstName)
        && lastName.equals(another.lastName)
        && email.equals(another.email)
        && password.equals(another.password)
        && confirmationPassword.equals(another.confirmationPassword)
        && Objects.equals(features, another.features);
  }

  /**
   * Computes a hash code from attributes: {@code tenantName}, {@code firstName}, {@code lastName}, {@code email}, {@code password}, {@code confirmationPassword}, {@code features}.
   * @return hashCode value
   */
  @Override
  public int hashCode() {
    int h = 5381;
    h += (h << 5) + tenantName.hashCode();
    h += (h << 5) + firstName.hashCode();
    h += (h << 5) + lastName.hashCode();
    h += (h << 5) + email.hashCode();
    h += (h << 5) + password.hashCode();
    h += (h << 5) + confirmationPassword.hashCode();
    h += (h << 5) + Objects.hashCode(features);
    return h;
  }

  /**
   * Prints the immutable value {@code RegisterRequest} with attribute values.
   * @return A string representation of the value
   */
  @Override
  public String toString() {
    return "RegisterRequest{"
        + "tenantName=" + tenantName
        + ", firstName=" + firstName
        + ", lastName=" + lastName
        + ", email=" + email
        + ", features=" + features
        + "}";
  }

  /**
   * Builds instances of type {@link RegisterRequest RegisterRequest}.
   * Initialize attributes and then invoke the {@link #build()} method to create an
   * immutable instance.
   * <p><em>{@code Builder} is not thread-safe and generally should not be stored in a field or collection,
   * but instead used immediately to create instances.</em>
   */
  @Generated(from = "RegisterRequest", generator = "Immutables")
  @JsonPropertyOrder({"tenantName", "firstName", "lastName", "email", "password", "confirmationPassword", "features"})
  public static class Builder {
    private static final long INIT_BIT_TENANT_NAME = 0x1L;
    private static final long INIT_BIT_FIRST_NAME = 0x2L;
    private static final long INIT_BIT_LAST_NAME = 0x4L;
    private static final long INIT_BIT_EMAIL = 0x8L;
    private static final long INIT_BIT_PASSWORD = 0x10L;
    private static final long INIT_BIT_CONFIRMATION_PASSWORD = 0x20L;
    private long initBits = 0x3fL;

    private String tenantName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmationPassword;
    private List<String> features = null;

    /**
     * Creates a builder for {@link RegisterRequest RegisterRequest} instances.
     * <pre>
     * new RegisterRequest.Builder()
     *    .tenantName(String) // required {@link RegisterRequest#tenantName() tenantName}
     *    .firstName(String) // required {@link RegisterRequest#firstName() firstName}
     *    .lastName(String) // required {@link RegisterRequest#lastName() lastName}
     *    .email(String) // required {@link RegisterRequest#email() email}
     *    .password(String) // required {@link RegisterRequest#password() password}
     *    .confirmationPassword(String) // required {@link RegisterRequest#confirmationPassword() confirmationPassword}
     *    .features(Set&amp;lt;String&amp;gt; | null) // nullable {@link RegisterRequest#features() features}
     *    .build();
     * </pre>
     */
    public Builder() {
      if (!(this instanceof RegisterRequest.Builder)) {
        throw new UnsupportedOperationException("Use: new RegisterRequest.Builder()");
      }
    }

    /**
     * Fill a builder with attribute values from the provided {@code RegisterRequest} instance.
     * Regular attribute values will be replaced with those from the given instance.
     * Absent optional values will not replace present values.
     * Collection elements and entries will be added, not replaced.
     * @param instance The instance from which to copy values
     * @return {@code this} builder for use in a chained invocation
     */
    public final RegisterRequest.Builder from(RegisterRequest instance) {
      Objects.requireNonNull(instance, "instance");
      tenantName(instance.tenantName());
      firstName(instance.firstName());
      lastName(instance.lastName());
      email(instance.email());
      password(instance.password());
      confirmationPassword(instance.confirmationPassword());
      Set<String> featuresValue = instance.features();
      if (featuresValue != null) {
        addAllFeatures(featuresValue);
      }
      return (RegisterRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link RegisterRequest#tenantName() tenantName} attribute.
     * @param tenantName The value for tenantName 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("tenantName")
    public final RegisterRequest.Builder tenantName(String tenantName) {
      this.tenantName = Objects.requireNonNull(tenantName, "tenantName");
      initBits &= ~INIT_BIT_TENANT_NAME;
      return (RegisterRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link RegisterRequest#firstName() firstName} attribute.
     * @param firstName The value for firstName 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("firstName")
    public final RegisterRequest.Builder firstName(String firstName) {
      this.firstName = Objects.requireNonNull(firstName, "firstName");
      initBits &= ~INIT_BIT_FIRST_NAME;
      return (RegisterRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link RegisterRequest#lastName() lastName} attribute.
     * @param lastName The value for lastName 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("lastName")
    public final RegisterRequest.Builder lastName(String lastName) {
      this.lastName = Objects.requireNonNull(lastName, "lastName");
      initBits &= ~INIT_BIT_LAST_NAME;
      return (RegisterRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link RegisterRequest#email() email} attribute.
     * @param email The value for email 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("email")
    public final RegisterRequest.Builder email(String email) {
      this.email = Objects.requireNonNull(email, "email");
      initBits &= ~INIT_BIT_EMAIL;
      return (RegisterRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link RegisterRequest#password() password} attribute.
     * @param password The value for password 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("password")
    public final RegisterRequest.Builder password(String password) {
      this.password = Objects.requireNonNull(password, "password");
      initBits &= ~INIT_BIT_PASSWORD;
      return (RegisterRequest.Builder) this;
    }

    /**
     * Initializes the value for the {@link RegisterRequest#confirmationPassword() confirmationPassword} attribute.
     * @param confirmationPassword The value for confirmationPassword 
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("confirmationPassword")
    public final RegisterRequest.Builder confirmationPassword(String confirmationPassword) {
      this.confirmationPassword = Objects.requireNonNull(confirmationPassword, "confirmationPassword");
      initBits &= ~INIT_BIT_CONFIRMATION_PASSWORD;
      return (RegisterRequest.Builder) this;
    }

    /**
     * Adds one element to {@link RegisterRequest#features() features} set.
     * @param element A features element
     * @return {@code this} builder for use in a chained invocation
     */
    public final RegisterRequest.Builder addFeatures(String element) {
      if (this.features == null) {
        this.features = new ArrayList<String>();
      }
      this.features.add(Objects.requireNonNull(element, "features element"));
      return (RegisterRequest.Builder) this;
    }

    /**
     * Adds elements to {@link RegisterRequest#features() features} set.
     * @param elements An array of features elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final RegisterRequest.Builder addFeatures(String... elements) {
      if (this.features == null) {
        this.features = new ArrayList<String>();
      }
      for (String element : elements) {
        this.features.add(Objects.requireNonNull(element, "features element"));
      }
      return (RegisterRequest.Builder) this;
    }


    /**
     * Sets or replaces all elements for {@link RegisterRequest#features() features} set.
     * @param elements An iterable of features elements
     * @return {@code this} builder for use in a chained invocation
     */
    @JsonProperty("features")
    public final RegisterRequest.Builder features(@Nullable Iterable<String> elements) {
      if (elements == null) {
        this.features = null;
        return (RegisterRequest.Builder) this;
      }
      this.features = new ArrayList<String>();
      return addAllFeatures(elements);
    }

    /**
     * Adds elements to {@link RegisterRequest#features() features} set.
     * @param elements An iterable of features elements
     * @return {@code this} builder for use in a chained invocation
     */
    public final RegisterRequest.Builder addAllFeatures(Iterable<String> elements) {
      Objects.requireNonNull(elements, "features element");
      if (this.features == null) {
        this.features = new ArrayList<String>();
      }
      for (String element : elements) {
        this.features.add(Objects.requireNonNull(element, "features element"));
      }
      return (RegisterRequest.Builder) this;
    }

    /**
     * Builds a new {@link RegisterRequest RegisterRequest}.
     * @return An immutable instance of RegisterRequest
     * @throws java.lang.IllegalStateException if any required attributes are missing
     */
    public RegisterRequest build() {
      if (initBits != 0) {
        throw new IllegalStateException(formatRequiredAttributesMessage());
      }
      return new ImmutableRegisterRequest(this);
    }

    private String formatRequiredAttributesMessage() {
      List<String> attributes = new ArrayList<>();
      if ((initBits & INIT_BIT_TENANT_NAME) != 0) attributes.add("tenantName");
      if ((initBits & INIT_BIT_FIRST_NAME) != 0) attributes.add("firstName");
      if ((initBits & INIT_BIT_LAST_NAME) != 0) attributes.add("lastName");
      if ((initBits & INIT_BIT_EMAIL) != 0) attributes.add("email");
      if ((initBits & INIT_BIT_PASSWORD) != 0) attributes.add("password");
      if ((initBits & INIT_BIT_CONFIRMATION_PASSWORD) != 0) attributes.add("confirmationPassword");
      return "Cannot build RegisterRequest, some of required attributes are not set " + attributes;
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

  /** Unmodifiable set constructed from list to avoid rehashing. */
  private static <T> Set<T> createUnmodifiableSet(List<T> list) {
    switch(list.size()) {
    case 0: return Collections.emptySet();
    case 1: return Collections.singleton(list.get(0));
    default:
      Set<T> set = new LinkedHashSet<>(list.size());
      set.addAll(list);
      return Collections.unmodifiableSet(set);
    }
  }
}
