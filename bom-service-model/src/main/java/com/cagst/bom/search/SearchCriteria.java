package com.cagst.bom.search;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;
import org.springframework.lang.Nullable;

@JsonDeserialize(builder = SearchCriteria.Builder.class)
@JsonPropertyOrder({
    "start",
    "limit",
    "searchText",
    "includeInactive"
})
@Value.Immutable(copy = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE, overshadowImplementation = true)
public interface SearchCriteria {
    /**
     * Allows the caller to specify the position in the collection to start returning results from.
     * If {@code null} then the results will start at the beginning of the result set.
     */
    @Nullable
    Integer start();

    /**
     * Allows the caller to specify the limit (maximum number of records) to return.
     * If (@code null} then all the qualifying results will be returned.
     */
    @Nullable
    Integer limit();

    /**
     * Allows the caller to specify some text to qualify records for.
     * <ul>
     *     <li>Search should be case-insensitive.</li>
     *     <li>Search should honor wildcard '*' as follows.</li>
     *     <ul>
     *         <li>If no wildcard, exact match should be performed.</li>
     *         <li>If prefixed with wildcard, an ends with search should be performed.</li>
     *         <li>If suffixed with wildcard, a starts with search should be performed.</li>
     *         <li>If prefixed and suffixed with wildcard, a contains search should be performed.</li>
     *     </ul>
     * </ul>
     */
    @Nullable
    String searchText();

    /**
     * Indicates if inactive (logically deleted records) should be returned.
     * Default: false
     */
    @Value.Default
    default boolean includeInactive() {
        return false;
    }

    // static inner Builder class extends generated or yet-to-be generated Builder
    class Builder extends ImmutableSearchCriteria.Builder {}
}
