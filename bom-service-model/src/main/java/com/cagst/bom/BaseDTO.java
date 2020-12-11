package com.cagst.bom;

import java.io.Serializable;
import java.time.OffsetDateTime;

import org.immutables.value.Value;
import org.springframework.lang.Nullable;

/**
 * Provides the base / common fields that should exist in all entities within the BOM family.
 *
 * @author Craig Gaskill
 */
public interface BaseDTO extends Serializable {
    @Nullable
    @Value.Auxiliary
    Long createdId();

    @Value.Auxiliary
    @Value.Default
    default OffsetDateTime createdDateTime() {
        return OffsetDateTime.now();
    }

    @Value.Default
    default boolean active() {
        return true;
    }

    @Nullable
    @Value.Auxiliary
    Long updatedId();

    @Value.Auxiliary
    @Value.Default
    default OffsetDateTime updatedDateTime() {
        return OffsetDateTime.now();
    }

    @Value.Auxiliary
    @Value.Default
    default long updatedCount() {
        return 0;
    }

    interface Builder {
        Builder createdId(Long createdId);
        Builder createdDateTime(OffsetDateTime createdDateTime);
        Builder active(boolean active);
        Builder updatedId(Long updatedId);
        Builder updatedDateTime(OffsetDateTime updatedDateTime);
        Builder updatedCount(long updatedCount);
    }
}
