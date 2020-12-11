package com.cagst.bom.dictionary;

import java.util.List;

import com.cagst.bom.imports.ImportType;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods to retrieve / persist {@link Dictionary} resources.
 *
 * @author Craig Gaskill
 */
public interface DictionaryService {
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
     * Finds a {@link Dictionary} by its meaning.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param meaning
     *      The meaning of the Dictionary to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Dictionary} if found.
     */
    Mono<Dictionary> findByMeaning(@NonNull SecurityInfo securityInfo, @NonNull String meaning);

    /**
     * Finds all the {@link Dictionary}s that match the specified criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Dictionary records on.
     *
     * @return A {@link Flux} that will emit all the Dictionaries that match the specified criteria.
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

    /**
     * Deletes an existing {@link Dictionary} (identified by its unique identifier) from persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaryId
     *      The unique identifier of the Dictionary to delete.
     *
     * @return A {@link Mono} that emits when complete.
     */
    Mono<Void> deleteById(@NonNull SecurityInfo securityInfo, long dictionaryId);

    /**
     * Deletes an existing {@link Dictionary} (identified by its meaning) from persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param meaning
     *      The meaning of the Dictionary to delete.
     *
     * @return A {@link Mono} that emits when complete.
     */
    Mono<Void> deleteByMeaning(@NonNull SecurityInfo securityInfo, @NonNull String meaning);

    /**
     * Finds all the {@link Dictionary Dictionaries} for the specified list of identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param identifiers
     *      A {@link List} of identifiers to retrieve Dictionaries for.
     *
     * @return A {@link Flux} that emits all the Dictionaries found for the given list of identifiers.
     */
    Flux<Dictionary> findByIdentifiers(@NonNull SecurityInfo securityInfo, @NonNull List<String> identifiers);

    /**
     * Saves the {@link Flux} of Dictionaries into persistent storage. Will insert or update as needed.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param dictionaries
     *      The {@link Flux} of Dictionaries to save.
     * @param featureMeaning
     *      The meaning of the Feature to associate the Dictionaries with.
     *
     * @return A {@link Flux} of Dictionaries after they have been persisted.
     */
    Flux<Dictionary> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<Dictionary> dictionaries, String featureMeaning);

    /**
     * Exports the dictionaries based upon the specified criteria.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     *
     * @return A {@link Flux} of Dictionaries to export based upon the specified criteria.
     */
    Flux<Dictionary> exportDictionaries(@NonNull SecurityInfo securityInfo);

    /**
     * Imports the dictionaries according to the {@link ImportType}.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param importType
     *      The {@link ImportType Type} of import to perform.
     * @param featureMeaning
     *      The meaning of the Feature to associate the Dictionaries with.
     * @param dictionaries
     *      A {@link Flux} that contains the {@link Dictionary Dictionaries} to import.
     *
     * @return Will emit if successful (or accepted for import) and an error if the import failed.
     */
    Mono<Void> importDictionaries(@NonNull SecurityInfo securityInfo,
                                  ImportType importType,
                                  String featureMeaning,
                                  Flux<Dictionary> dictionaries);
}
