package com.cagst.bom.dictionary.value;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

/**
 * Defines methods for retrieving / persisting {@link DictionaryValue} records.
 *
 * @author Craig Gaskill
 */
public interface DictionaryValueRepository {
    /**
     * Finds a {@link DictionaryValue} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the value is associated with.
     * @param dictionaryValueId
     *      The unique identifier of the Dictionary Value to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryValue} if found.
     */
    Mono<DictionaryValue> findById(@NonNull SecurityInfo securityInfo,
                                   long dictionaryId,
                                   long dictionaryValueId);

    /**
     * Finds a {@link DictionaryValue} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the value is associated with.
     * @param dictionaryValueId
     *      The unique identifier of the Dictionary Value to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryValue} if found.
     */
    Mono<DictionaryValue> findById(@NonNull SecurityInfo securityInfo,
                                   @NonNull String dictionaryMeaning,
                                   long dictionaryValueId);

    /**
     * Finds a {@link DictionaryValue} by its meaning.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the value is associated with.
     * @param valueMeaning
     *      The meaning of the Dictionary Value to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryValue} if found.
     */
    Mono<DictionaryValue> findByMeaning(@NonNull SecurityInfo securityInfo,
                                        long dictionaryId,
                                        @NonNull String valueMeaning);

    /**
     * Finds a {@link DictionaryValue} by its meaning.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the value is associated with.
     * @param valueMeaning
     *      The meaning of the Dictionary Value to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryValue} if found.
     */
    Mono<DictionaryValue> findByMeaning(@NonNull SecurityInfo securityInfo,
                                        @NonNull String dictionaryMeaning,
                                        @NonNull String valueMeaning);

    /**
     * Finds all {@link DictionaryValue}s that match the specified criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the value is associated with.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Dictionary Value records on.
     *
     * @return A {@link Flux} that will emit all Dictionary Values matching the specified search criteria.
     */
    Flux<DictionaryValue> findByCriteria(@NonNull SecurityInfo securityInfo,
                                         long dictionaryId,
                                         @Nullable SearchCriteria searchCriteria);

    /**
     * Finds all {@link DictionaryValue}s that match the specified criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the value is associated with.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Dictionary Value records on.
     *
     * @return A {@link Flux} that will emit all Dictionary Values matching the specified search criteria.
     */
    Flux<DictionaryValue> findByCriteria(@NonNull SecurityInfo securityInfo,
                                         @NonNull String dictionaryMeaning,
                                         @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link DictionaryValue} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryValue
     *      The {@link DictionaryValue} to insert.
     * @param dictionaryId
     *      The unique identifier of the Dictionary to associate this Dictionary Value to.
     *
     * @return A {@link Mono} that emits the Dictionary Value after it was inserted.
     */
    Mono<DictionaryValue> insert(@NonNull SecurityInfo securityInfo,
                                 @NonNull DictionaryValue dictionaryValue,
                                 long dictionaryId);

    /**
     * Inserts a new {@link DictionaryValue} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryValue
     *      The {@link DictionaryValue} to insert.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary to associate this Dictionary Value to.
     *
     * @return A {@link Mono} that emits the Dictionary Value after it was inserted.
     */
    Mono<DictionaryValue> insert(@NonNull SecurityInfo securityInfo,
                                 @NonNull DictionaryValue dictionaryValue,
                                 @NonNull String dictionaryMeaning);

    /**
     * Updates an existing {@link DictionaryValue} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryValue
     *      The {@link DictionaryValue} to update.
     *
     * @return A {@link Mono} that emits the Dictionary Value after it was updated.
     */
    Mono<DictionaryValue> update(@NonNull SecurityInfo securityInfo,
                                 @NonNull DictionaryValue dictionaryValue);
}
