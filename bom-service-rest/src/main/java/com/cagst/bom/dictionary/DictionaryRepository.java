package com.cagst.bom.dictionary;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link Dictionary} records.
 *
 * @author Craig Gaskill
 */
public interface DictionaryRepository {
    /**
     * Finds a {@link Dictionary} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Dictionary} if found.
     */
    Mono<Dictionary> findById(@NonNull SecurityInfo securityInfo, long dictionaryId);

    /**
     * Finds all {@link Dictionary} for the given unique identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param ids
     *      A {@link Collection} of unique identifiers of Dictionaries to retrieve.
     *
     * @return A {@link Flux} that emits all the Dictionaries for the specified unique identifiers.
     */
    Flux<Dictionary> findByIds(@NonNull SecurityInfo securityInfo,@NonNull Collection<Long> ids);

    /**
     * Finds a {@link Dictionary} by its meaning.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param meaning
     *      A {@link String} the describes the purpose (meaning) for the dictionar.y
     *
     * @return A {@link Mono} that may emit a {@link Dictionary} if found.
     */
    Mono<Dictionary> findByMeaning(@NonNull SecurityInfo securityInfo, @NonNull String meaning);

    /**
     * Finds all {@link Dictionary} for the given meanings.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param meanings
     *      A {@link Collection} of meanings of Dictionaries to retrieve.
     *
     * @return A {@link Flux} that emits all the Dictionaries for the specified meanings.
     */
    Flux<Dictionary> findByMeanings(@NonNull SecurityInfo securityInfo, @NonNull Collection<String> meanings);

    /**
     * Finds all {@link Dictionary}s that match the specified criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Dictionary records on.
     *
     * @return A {@link Flux} that will emit all Dictionaries matching the specified search criteria.
     */
    Flux<Dictionary> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link Dictionary} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionary
     *      The {@link Dictionary} to insert.
     * @param featureMeaning
     *      The meaning of the Feature to associate this Dictionary with.
     *
     * @return A {@link Mono} that emits the Dictionary after it was inserted.
     */
    Mono<Dictionary> insert(@NonNull SecurityInfo securityInfo,
                            @NonNull Dictionary dictionary,
                            @NonNull String featureMeaning);

    /**
     * Updates an existing {@link Dictionary} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionary
     *      The {@link Dictionary} to update.
     *
     * @return A {@link Mono} that emits the Dictionary after it was updated.
     */
    Mono<Dictionary> update(@NonNull SecurityInfo securityInfo, @NonNull Dictionary dictionary);
}
