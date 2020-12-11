package com.cagst.bom.activity;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = ActivityLog.Builder.class)
@JsonPropertyOrder({
    "activityLogId",
    "activityDateTime",
    "activitySubCategory",
    "activityContext",
    "tenantId",
    "instigatingUserId"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface ActivityLog {
    @Nullable
    @Value.Auxiliary
    Long activityLogId();

    @Nullable
    OffsetDateTime activityDateTime();

    ActivityCategory activityCategory();

    @Nullable
    String activitySubCategory();

    @Nullable
    String activityContext();

    @Nullable
    Integer tenantId();

    @Nullable
    Long instigatingUserId();

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableActivityLog.Builder {}
}
