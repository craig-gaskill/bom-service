package com.cagst.bom.security;

import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonPropertyOrder({
    "tenantId",
    "userId",
    "source",
    "zoneId"
})
@JsonDeserialize(builder = SecurityInfo.Builder.class)
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface SecurityInfo {
    @JsonProperty("tenantId")
    int tenantId();

    @JsonProperty("userId")
    long userId();

    @JsonProperty("source")
    @Nullable
    String source();

    @JsonProperty("zoneId")
    @Nullable
    ZoneId zoneId();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableSecurityInfo.Builder {}
}
