package com.cagst.bom.security.authentication;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@JsonDeserialize(builder = ChangePasswordRequest.Builder.class)
@JsonPropertyOrder({
    "tenantId",
    "newPassword",
    "confirmationPassword"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
/* package */ interface ChangePasswordRequest {
    int tenantId();
    String newPassword();
    String confirmationPassword();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableChangePasswordRequest.Builder {}
}
