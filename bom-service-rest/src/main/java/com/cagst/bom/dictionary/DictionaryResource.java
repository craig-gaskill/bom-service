package com.cagst.bom.dictionary;

import java.util.List;
import java.util.Objects;

import com.cagst.bom.imports.ImportType;
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
 * Defines endpoints to retrieve / persist {@link Dictionary} resources.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("dictionaries")
public class DictionaryResource {
    private final String FEATURE_CORE = "CORE";

    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryResource(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Dictionary> getDictionaries(
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "limit", defaultValue = "25") int limit,
        @RequestParam(value = "searchText", required = false) String searchText,
        @RequestParam(value = "includeInactive", defaultValue = "false") boolean includeInactive
    ) {
        var searchCriteria = new SearchCriteria.Builder()
            .start(start)
            .limit(limit > 0 ? limit : null)
            .searchText(searchText)
            .includeInactive(includeInactive)
            .build();

        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> dictionaryService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Dictionary> streamDictionaries(
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
            .flatMapMany(securityInfo -> dictionaryService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)));
    }

    @GetMapping(value = "{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Mono<Dictionary> getDictionary(@PathVariable("identifier") String identifier) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (StringUtils.isNumeric(identifier)) {
                    return dictionaryService.findById(securityInfo, Long.parseLong(identifier));
                } else {
                    return dictionaryService.findByMeaning(securityInfo, identifier);
                }
            })
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Mono<Dictionary> createDictionary(
        @RequestParam(value = "featureMeaning", defaultValue = FEATURE_CORE) String featureMeaning,
        @RequestBody Dictionary dictionary
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> dictionaryService.insert(securityInfo, dictionary, featureMeaning));
    }

    @PutMapping(
        value = "{identifier}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public Mono<Dictionary> updateDictionary(
        @PathVariable("identifier") long dictionaryId,
        @RequestBody Dictionary dictionary
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (Objects.equals(dictionaryId, dictionary.dictionaryId())) {
                    return dictionaryService.update(securityInfo, dictionary);
                } else {
                    return Mono.defer(() ->
                        Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The [identifier] does not match the ID of the Dictionary passed in"))
                    );
                }
            });
    }

    @DeleteMapping("{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public Mono<Void> deleteDictionary(@PathVariable("identifier") String identifier) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (StringUtils.isNumeric(identifier)) {
                    return dictionaryService.deleteById(securityInfo, Long.parseLong(identifier));
                } else {
                    return dictionaryService.deleteByMeaning(securityInfo, identifier);
                }
            });
    }

    @PostMapping(
        value = "list",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public Flux<Dictionary> listDictionaries(@RequestBody List<String> identifiers) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> dictionaryService.findByIdentifiers(securityInfo, identifiers));
    }

    @PostMapping(
        value = "save",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public Flux<Dictionary> saveDictionaries(
        @RequestParam(value = "featureMeaning", defaultValue = FEATURE_CORE) String featureMeaning,
        @RequestBody Flux<Dictionary> dictionaries
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> dictionaryService.save(securityInfo, dictionaries, featureMeaning));
    }

    @GetMapping(value = "export", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Dictionary> exportDictionaries() {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> dictionaryService.findByCriteria(securityInfo, null))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @PostMapping(
        value = "import",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public Mono<Void> importDictionaries(@RequestParam(value = "importType", defaultValue = "ADD_ONLY") ImportType importType,
                                         @RequestParam(value = "featureMeaning", defaultValue = "CORE") String featureMeaning,
                                         @RequestBody Flux<Dictionary> dictionaries
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> dictionaryService.importDictionaries(securityInfo, importType, featureMeaning, dictionaries))
            .then();
    }
}
