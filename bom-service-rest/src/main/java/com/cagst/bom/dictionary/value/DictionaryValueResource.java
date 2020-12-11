package com.cagst.bom.dictionary.value;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.spring.security.ReactiveSecurityContextHolder;
import org.apache.commons.lang3.StringUtils;
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
 * Handles endpoints for retrieving and persisting {@link DictionaryValue} records.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("dictionaries/{dictionaryIdentifier}/values")
public class DictionaryValueResource {
    private final DictionaryValueService dictionaryValueService;

    @Autowired
    public DictionaryValueResource(DictionaryValueService dictionaryValueService) {
        this.dictionaryValueService = dictionaryValueService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<DictionaryValue> getDictionaryValues(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
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
            .flatMapMany(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    return dictionaryValueService.findByCriteria(securityInfo, Long.parseLong(dictionaryIdentifier), searchCriteria);
                } else {
                    return dictionaryValueService.findByCriteria(securityInfo, dictionaryIdentifier, searchCriteria);
                }
            })
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<DictionaryValue> streamDictionaryValues(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
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
            .flatMapMany(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    return dictionaryValueService.findByCriteria(securityInfo, Long.parseLong(dictionaryIdentifier), searchCriteria);
                } else {
                    return dictionaryValueService.findByCriteria(securityInfo, dictionaryIdentifier, searchCriteria);
                }
            })
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @GetMapping(value = "{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Mono<DictionaryValue> getDictionary(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @PathVariable("identifier") String identifier
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    if (StringUtils.isNumeric(identifier)) {
                        return dictionaryValueService.findById(securityInfo, Long.parseLong(dictionaryIdentifier), Long.parseLong(identifier));
                    } else {
                        return dictionaryValueService.findByMeaning(securityInfo, Long.parseLong(dictionaryIdentifier), identifier);
                    }
                } else {
                    if (StringUtils.isNumeric(identifier)) {
                        return dictionaryValueService.findById(securityInfo, dictionaryIdentifier, Long.parseLong(identifier));
                    } else {
                        return dictionaryValueService.findByMeaning(securityInfo, dictionaryIdentifier, identifier);
                    }
                }
            })
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Mono<DictionaryValue> createDictionaryValue(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @RequestBody DictionaryValue dictionaryValue
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo ->
                StringUtils.isNumeric(dictionaryIdentifier) ?
                    dictionaryValueService.insert(securityInfo, Long.parseLong(dictionaryIdentifier), dictionaryValue) :
                    dictionaryValueService.insert(securityInfo, dictionaryIdentifier, dictionaryValue)
            );
    }

    @PutMapping(
        value = "{identifier}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Mono<DictionaryValue> updateDictionaryValue(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @PathVariable("identifier") String identifier,
        @RequestBody DictionaryValue dictionaryValue
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo ->
                StringUtils.isNumeric(dictionaryIdentifier) ?
                    dictionaryValueService.update(securityInfo, Long.parseLong(dictionaryIdentifier), dictionaryValue) :
                    dictionaryValueService.update(securityInfo, dictionaryIdentifier, dictionaryValue)
            );
    }

    @DeleteMapping("{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public Mono<Void> deleteDictionaryValue(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @PathVariable("identifier") long identifier
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo ->
                StringUtils.isNumeric(dictionaryIdentifier) ?
                    dictionaryValueService.delete(securityInfo, Long.parseLong(dictionaryIdentifier), identifier) :
                    dictionaryValueService.delete(securityInfo, dictionaryIdentifier, identifier)
            );
    }
}
