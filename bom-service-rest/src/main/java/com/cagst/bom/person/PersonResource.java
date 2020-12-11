package com.cagst.bom.person;

import java.util.List;
import java.util.Objects;

import com.cagst.bom.imports.ImportType;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.spring.security.ReactiveSecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines endpoints to retrieve / persist {@link Person} resources.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("people")
public class PersonResource {
    private final PersonService personService;

    @Autowired
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Person> getPeople(
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "limit", defaultValue = "25") int limit,
        @RequestParam(value = "searchText", required = false) String searchText,
        @RequestParam(value = "includeInactive", defaultValue = "false") boolean includeInactive
    ) {
        SearchCriteria searchCriteria = new SearchCriteria.Builder()
            .start(start)
            .limit(limit > 0 ? limit : null)
            .searchText(searchText)
            .includeInactive(includeInactive)
            .build();

        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> personService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No People found"))));
    }

    @GetMapping(value = "{personId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Mono<Person> getPerson(@PathVariable("identifier") long personId) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> personService.findById(securityInfo, personId))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "Person was not found for [" + personId + "]"))));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional()
    public Mono<Person> createPerson(@RequestBody Person person) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> personService.insert(securityInfo, person));
    }

    @PutMapping(
        value = "{personId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional()
    public Mono<Person> updatePerson(@PathVariable("personId") long personId,
                                     @RequestBody Person person
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (Objects.equals(personId, person.personId())) {
                    return personService.update(securityInfo, person);
                } else {
                    return Mono.defer(() ->
                        Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The [personId] does not match the ID of the Person passed in"))
                    );
                }
            });
    }

    @DeleteMapping("{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional()
    public Mono<Void> deletePerson(@PathVariable("personId") long personId) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> personService.delete(securityInfo, personId));
    }

    @PostMapping(
        value = "list",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public Flux<Person> listPeople(@RequestBody List<Long> ids) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> personService.findByIds(securityInfo, ids));
    }

    @PostMapping(
        value = "save",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public Flux<Person> savePeople(@RequestBody Flux<Person> people) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> personService.save(securityInfo, people));
    }

    @GetMapping(value = "export", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<Person> exportPeople() {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> this.personService.findByCriteria(securityInfo, null))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No people found")));
    }

    @PostMapping(
        value = "import",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public Mono<Void> importPeople(@RequestParam ImportType importType,
                                   @RequestBody Flux<Person> people
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> personService.save(securityInfo, people))
            .then();
    }
}
