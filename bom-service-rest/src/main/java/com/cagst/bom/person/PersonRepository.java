package com.cagst.bom.person;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods for retrieving / persisting {@link Person} records.
 *
 * @author Craig Gaskill
 */
public interface PersonRepository {
    /**
     * Finds a {@link Person} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param personId
     *      The unique identifier of the Person to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Person} if found.
     */
    @NonNull
    Mono<Person> findById(@NonNull SecurityInfo securityInfo, long personId);

    /**
     * Finds all {@link Person} for the given unique identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param ids
     *      A {@link Collection} of unique identifiers of People to retrieve.
     *
     * @return A {@link Flux} that emits all the People for the specified unique identifiers.
     */
    @NonNull
    Flux<Person> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids);

    /**
     * Finds all {@link Person People}.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param searchCriteria
     *      The {@link SecurityInfo} to use to qualify Person records on.
     *
     * @return A {@link Flux} that will emit all the People.
     */
    @NonNull
    Flux<Person> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link Person} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param person
     *      The {@link Person} to insert.
     *
     * @return A {@link Mono} that emits the Person after it was inserted.
     */
    @NonNull
    Mono<Person> insert(@NonNull SecurityInfo securityInfo, @NonNull Person person);

    /**
     * Updates an existing {@link Person} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param person
     *      The {@link Person} to update.
     *
     * @return A {@link Mono} that emits the Person after it was updated.
     */
    @NonNull
    Mono<Person> update(@NonNull SecurityInfo securityInfo, @NonNull Person person);
}
