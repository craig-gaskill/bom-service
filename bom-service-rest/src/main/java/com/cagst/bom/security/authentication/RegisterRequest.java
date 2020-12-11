package com.cagst.bom.security.authentication;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = RegisterRequest.Builder.class)
@JsonPropertyOrder({
    "tenantName",
    "firstName",
    "lastName",
    "email",
    "password",
    "confirmationPassword",
    "features"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
/* package */ interface RegisterRequest {
    String tenantName();
    String firstName();
    String lastName();
    String email();

    @Value.Redacted
    String password();

    @Value.Redacted
    String confirmationPassword();

    /**
     * The meanings of the Feature to associate with the new Tenant.
     */
    @Nullable
    Set<String> features();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableRegisterRequest.Builder {}
}
