package com.cagst.bom.dictionary.attribute;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link DictionaryAttribute} records.
 *
 * @author Craig Gaskill
 */
public interface DictionaryAttributeRepository {
    /**
     * Finds a {@link DictionaryAttribute} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the attribute is associated with.
     * @param dictionaryAttributeId
     *      The unique identifier of the Dictionary Attribute to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryAttribute} if found.
     */
    Mono<DictionaryAttribute> findById(@NonNull SecurityInfo securityInfo,
                                       long dictionaryId,
                                       long dictionaryAttributeId);

    /**
     * Finds a {@link DictionaryAttribute} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the attribute is associated with.
     * @param dictionaryAttributeId
     *      The unique identifier of the Dictionary Attribute to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryAttribute} if found.
     */
    Mono<DictionaryAttribute> findById(@NonNull SecurityInfo securityInfo,
                                       @NonNull String dictionaryMeaning,
                                       long dictionaryAttributeId);

    /**
     * Finds a {@link DictionaryAttribute} by its meaning.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the attribute is associated with.
     * @param attributeMeaning
     *      The meaning of the Dictionary Attribute to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryAttribute} if found.
     */
    Mono<DictionaryAttribute> findByMeaning(@NonNull SecurityInfo securityInfo,
                                            long dictionaryId,
                                            @NonNull String attributeMeaning);

    /**
     * Finds a {@link DictionaryAttribute} by its meaning.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the attribute is associated with.
     * @param attributeMeaning
     *      The meaning of the Dictionary Attribute to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link DictionaryAttribute} if found.
     */
    Mono<DictionaryAttribute> findByMeaning(@NonNull SecurityInfo securityInfo,
                                            @NonNull String dictionaryMeaning,
                                            @NonNull String attributeMeaning);

    /**
     * Finds all {@link DictionaryAttribute}s that match the specified criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the attribute is associated with.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Dictionary Attribute records on.
     *
     * @return A {@link Flux} that will emit all Dictionary Attributes matching the specified search criteria.
     */
    Flux<DictionaryAttribute> findByCriteria(@NonNull SecurityInfo securityInfo,
                                             long dictionaryId,
                                             @Nullable SearchCriteria searchCriteria);

    /**
     * Finds all {@link DictionaryAttribute}s that match the specified criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the attribute is associated with.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Dictionary Attribute records on.
     *
     * @return A {@link Flux} that will emit all Dictionary Attributes matching the specified search criteria.
     */
    Flux<DictionaryAttribute> findByCriteria(@NonNull SecurityInfo securityInfo,
                                             @NonNull String dictionaryMeaning,
                                             @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link DictionaryAttribute} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary the attribute is associated with.
     * @param attribute
     *      The {@link DictionaryAttribute} to insert.
     *
     * @return A {@link Mono} that emits the Dictionary Attribute after it was inserted.
     */
    Mono<DictionaryAttribute> insert(@NonNull SecurityInfo securityInfo,
                                     long dictionaryId,
                                     @NonNull DictionaryAttribute attribute);
    /**
     * Inserts a new {@link DictionaryAttribute} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryMeaning
     *      The meaning of the Dictionary the attribute is associated with.
     * @param attribute
     *      The {@link DictionaryAttribute} to insert.
     *
     * @return A {@link Mono} that emits the Dictionary Attribute after it was inserted.
     */
    Mono<DictionaryAttribute> insert(@NonNull SecurityInfo securityInfo,
                                     @NonNull String dictionaryMeaning,
                                     @NonNull DictionaryAttribute attribute);

    /**
     * Updates an existing {@link DictionaryAttribute} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param attribute
     *      The {@link DictionaryAttribute} to update.
     *
     * @return A {@link Mono} that emits the Dictionary Attribute after it was updated.
     */
    Mono<DictionaryAttribute> update(@NonNull SecurityInfo securityInfo,
                                     @NonNull DictionaryAttribute attribute);
}
