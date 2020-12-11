package com.cagst.bom.person;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines methods to retrieve / persist {@link Person} resources.
 *
 * @author Craig Gaskill
 */
public interface PersonService {
    /**
     * Finds a {@link Person} by its unique identifier.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param personId
     *      The unique identifier of the Person to retrieve.
     *
     * @return A {@link Mono} that may emit a {@link Person} if found.
     */
    Mono<Person> findById(@NonNull SecurityInfo securityInfo, long personId);

    /**
     * Finds all {@link Person} for the given unique identifiers.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param ids
     *      A {@link Collection} of unique identifiers of People to retrieve.
     *
     * @return A {@link Flux} that emits all the People for the specified unique identifiers.
     */
    Flux<Person> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids);

    /**
     * Finds all {@link Person}s that match the specified {@link SearchCriteria} (if provided).
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param searchCriteria
     *      The {@link SearchCriteria} to use to qualify Person records on.
     *
     * @return A {@link Flux} that will emit all the People that match the specified search criteria (if provided).
     */
    Flux<Person> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria);

    /**
     * Inserts a new {@link Person} into persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param person
     *      The {@link Person} to insert.
     *
     * @return A {@link Mono} that emits the Person after it was inserted.
     */
    Mono<Person> insert(@NonNull SecurityInfo securityInfo, @NonNull Person person);

    /**
     * Updates an existing {@link Person} in persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param person
     *      The {@link Person} to update.
     *
     * @return A {@link Mono} that emits the Person after it was updated.
     */
    Mono<Person> update(@NonNull SecurityInfo securityInfo, @NonNull Person person);

    /**
     * Deletes an existing {@link Person}, identified by its unique identifier, from persistent storage.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param personId
     *      The unique identifier of the Person to delete.
     *
     * @return A {@link Mono} that emits when successfully deleted.
     */
    Mono<Void> delete(@NonNull SecurityInfo securityInfo, long personId);

    /**
     * Saves the {@link Flux} of People into persistent storage. Will insert or update as needed.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param people
     *      The {@link Flux} of People to save.
     *
     * @return The {@link Flux} of People after they have been persisted.
     */
    Flux<Person> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<Person> people);
}
