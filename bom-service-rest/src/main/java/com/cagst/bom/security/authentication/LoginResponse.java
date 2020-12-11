package com.cagst.bom.security.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = LoginResponse.Builder.class)
@JsonPropertyOrder({
    "status",
    "access"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
/* package */ interface LoginResponse {
  @JsonProperty("status")
  LoginStatus loginStatus();

  @JsonProperty("access")
  @Nullable
  String accessToken();

  // static inner Builder class extends generated or yet-to-be generated Builder
  class Builder extends ImmutableLoginResponse.Builder {}
}
