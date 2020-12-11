package com.cagst.bom.tenant;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import com.cagst.bom.BaseDTO;
import com.cagst.bom.subscription.SubscriptionType;
import com.cagst.bom.tenant.feature.TenantFeature;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = Tenant.Builder.class)
@JsonPropertyOrder({
    "tenantId",
    "mnemonic",
    "name",
    "locale",
    "subscriptionType",
    "subscriptionStartDate",
    "subscriptionEndDate",
    "features",
    "createdId",
    "createdDateTime",
    "active",
    "updatedId",
    "updatedDateTime",
    "updatedCount"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface Tenant extends BaseDTO {
    @Nullable
    @Value.Auxiliary
    Integer tenantId();

    String mnemonic();

    String name();

    @Value.Default
    default Locale locale() {
        return Locale.US;
    }

    @Value.Default
    default SubscriptionType subscriptionType() {
        return SubscriptionType.EarlyAdopter;
    }

    @Nullable
    LocalDate subscriptionStartDate();

    @Nullable
    LocalDate subscriptionEndDate();

    @Nullable
    List<TenantFeature> features();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableTenant.Builder implements BaseDTO.Builder {}
}
