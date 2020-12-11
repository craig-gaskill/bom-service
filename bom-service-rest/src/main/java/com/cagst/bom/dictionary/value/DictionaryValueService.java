package com.cagst.bom.dictionary.value;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods that enforce the business rules for retrieving and persisting {@link DictionaryValue} records.
 *
 * @author Craig Gaskill
 */
public interface DictionaryValueService {
    /**
     * Finds a {@link DictionaryValue} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the DictionaryValue is associated with.
     * @param dictionaryValueId
     *      The unique identifier of the DictionaryValue to retrieve.
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
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the DictionaryValue is associated with.
     * @param dictionaryValueId
     *      The unique identifier of the DictionaryValue to retrieve.
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
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the DictionaryValue is associated with.
     * @param dictionaryValueMeaning
     *      The meaning of the DictionaryValue to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryValue} if found.
     */
    Mono<DictionaryValue> findByMeaning(@NonNull SecurityInfo securityInfo,
                                        long dictionaryId,
                                        @NonNull String dictionaryValueMeaning);

    /**
     * Finds a {@link DictionaryValue} by its meaning.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the DictionaryValue is associated with.
     * @param dictionaryValueMeaning
     *      The meaning of the DictionaryValue to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryValue} if found.
     */
    Mono<DictionaryValue> findByMeaning(@NonNull SecurityInfo securityInfo,
                                        @NonNull String dictionaryMeaning,
                                        @NonNull String dictionaryValueMeaning);

    /**
     * Finds all the {@link DictionaryValue}s that are associated to the Dictionary that match the
     * specified criteria
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the DictionaryValue is associated with.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify DictionaryValue records on.
     *
     * @return A {@link Flux} that will emit all the DictionaryValues that match the specified criteria.
     */
    Flux<DictionaryValue> findByCriteria(@NonNull SecurityInfo securityInfo,
                                         long dictionaryId,
                                         @Nullable SearchCriteria searchCriteria);

    /**
     * Finds all the {@link DictionaryValue}s that are associated to the Dictionary that match the
     * specified criteria
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryMeaning
     *     The meaning of the Dictionary we want the dictionary values for.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify DictionaryValue records on.
     *
     * @return A {@link Flux} that will emit all the DictionaryValues that match the specified criteria.
     */
    Flux<DictionaryValue> findByCriteria(@NonNull SecurityInfo securityInfo,
                                         @NonNull String dictionaryMeaning,
                                         @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link DictionaryValue} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the DictionaryValue is associated with.
     * @param dictionaryValue
     *    The {@link DictionaryValue} to persist.
     *
     * @return The {@link DictionaryValue} after it has been persisted.
     */
    Mono<DictionaryValue> insert(@NonNull SecurityInfo securityInfo,
                                 long dictionaryId,
                                 DictionaryValue dictionaryValue);

    /**
     * Inserts a new {@link DictionaryValue} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryMeaning
     *     The meaning of the Dictionary we want the dictionary value associated with.
     * @param dictionaryValue
     *    The {@link DictionaryValue} to persist.
     *
     * @return The {@link DictionaryValue} after it has been persisted.
     */
    Mono<DictionaryValue> insert(@NonNull SecurityInfo securityInfo,
                                 @NonNull String dictionaryMeaning,
                                 DictionaryValue dictionaryValue);

    /**
     * Updates an existing {@link DictionaryValue} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the DictionaryValue is associated with.
     * @param dictionaryValue
     *    The {@link DictionaryValue} to persist.
     *
     * @return The {@link DictionaryValue} after it has been persisted.
     */
    Mono<DictionaryValue> update(@NonNull SecurityInfo securityInfo,
                                 long dictionaryId,
                                 DictionaryValue dictionaryValue);

    /**
     * Updates an existing {@link DictionaryValue} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryMeaning
     *     The meaning of the Dictionary we want the dictionary value associated with.
     * @param dictionaryValue
     *    The {@link DictionaryValue} to persist.
     *
     * @return The {@link DictionaryValue} after it has been persisted.
     */
    Mono<DictionaryValue> update(@NonNull SecurityInfo securityInfo,
                                 @NonNull String dictionaryMeaning,
                                 DictionaryValue dictionaryValue);

    /**
     * Deletes an existing {@link DictionaryValue} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the DictionaryValue is associated with.
     * @param dictionaryValueId
     *    The unique identifier of the DictionaryValue to update.
     */
    Mono<Void> delete(@NonNull SecurityInfo securityInfo,
                      long dictionaryId,
                      long dictionaryValueId);

    /**
     * Deletes an existing {@link DictionaryValue} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param dictionaryMeaning
     *     The meaning of the Dictionary we want the value associated with..
     * @param dictionaryValueId
     *    The unique identifier of the DictionaryValue to update.
     */
    Mono<Void> delete(@NonNull SecurityInfo securityInfo,
                      @NonNull String dictionaryMeaning,
                      long dictionaryValueId);
}
