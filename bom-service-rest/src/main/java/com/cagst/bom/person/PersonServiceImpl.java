package com.cagst.bom.person;

import java.util.Collection;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An implementation of the {@link PersonService} that provides the business logic needed to properly
 * retrieve and persist {@link Person} resources.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Mono<Person> findById(@NonNull SecurityInfo securityInfo, long personId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return personRepository.findById(securityInfo, personId);
    }

    @Override
    public Flux<Person> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(ids, "Argument [ids] cannot be null or empty");

        return personRepository.findByIds(securityInfo, ids);
    }

    @Override
    public Flux<Person> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return personRepository.findByCriteria(securityInfo, searchCriteria);
    }

    @Override
    public Mono<Person> insert(@NonNull SecurityInfo securityInfo, @NonNull Person person) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(person, "Argument [person] cannot be null");
        Assert.isNull(person.personId(), "Argument [person.personId] must be null");

        return personRepository.insert(securityInfo, person);
    }

    @Override
    public Mono<Person> update(@NonNull SecurityInfo securityInfo, @NonNull Person person) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(person, "Argument [person] cannot be null");
        Assert.notNull(person.personId(), "Argument [person] must be an existing Person");

        return checkExists(securityInfo, person.personId())
            .flatMap(found -> {
                if (found.equals(person)) {
                    // if the found Person is the same as the specified Persion
                    // there is nothing to do (no save is necessary)
                    return Mono.just(person);
                } else {
                    return personRepository.update(securityInfo, person);
                }
            });
    }

    @Override
    public Mono<Void> delete(@NonNull SecurityInfo securityInfo, long personId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return checkExists(securityInfo, personId)
            .flatMap(found -> {
                if (found.active()) {
                    return personRepository.update(securityInfo, new Person.Builder().from(found).active(false).build());
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    @Override
    public Flux<Person> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<Person> people) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return people.flatMap(person -> {
            if (person.personId() == null) {
                return insert(securityInfo, person);
            } else {
                return update(securityInfo, person);
            }
        });
    }

    private Mono<Person> checkExists(SecurityInfo securityInfo, long personId) {
        return findById(securityInfo, personId)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Person [" + personId + "] was not found")));
    }
}
