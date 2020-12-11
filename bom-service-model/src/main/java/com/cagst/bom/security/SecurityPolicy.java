package com.cagst.bom.security;

import com.cagst.bom.BaseDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = SecurityPolicy.Builder.class)
@JsonPropertyOrder({
    "securityPolicyId",
    "policyName",
    "maxAttempts",
    "timeoutInMinutes",
    "expiryInDays",
    "lockedInMinutes",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface SecurityPolicy extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Long securityPolicyId();

    String policyName();

    /**
     * Defines the maximum number of attempts before the account is automatically locked.
     * If 0 (or less than zero), then the number of attempts are unlimited.
     */
    @Value.Default
    default int maxAttempts() {
        return 5;
    }

    /**
     * Defines the number of minutes before the user is timed out due to inactivity.
     * If 0 (or less than zero), then the user will not timeout due to inactivity.
     */
    @Value.Default
    default int timeoutInMinutes() {
        return 15;
    }

    /**
     * Defines the number of days before the password is expired and the user must change it.
     * If 0 (or less than zero), then the password will not expire.
     */
    @Value.Default
    default int expiryInDays() {
        return 90;
    }

    /**
     * Defines the number of minutes the user's account will remain locked (if lock automatically).
     * After this period has lapsed, then the account will be automatically unlocked.
     * If 0 (or less than zero), then the user's account will not be automatically be unlocked.
     */
    @Value.Default
    default int lockedInMinutes() {
        return 7 * 24 * 60;
    }

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableSecurityPolicy.Builder implements BaseDTO.Builder {}
}
