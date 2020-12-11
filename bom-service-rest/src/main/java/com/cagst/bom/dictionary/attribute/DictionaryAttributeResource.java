package com.cagst.bom.dictionary.attribute;

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
 * Defines endpoints to retrieve / persist {@link DictionaryAttribute} resources.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("dictionaries/{dictionaryIdentifier}/attributes")
public class DictionaryAttributeResource {
    private final DictionaryAttributeService dictionaryAttributeService;

    @Autowired
    public DictionaryAttributeResource(DictionaryAttributeService dictionaryAttributeService) {
        this.dictionaryAttributeService = dictionaryAttributeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<DictionaryAttribute> getAttributes(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "limit", defaultValue = "25") int limit,
        @RequestParam(value = "includeInactive", defaultValue = "false") boolean includeInactive
    ) {
        SearchCriteria searchCriteria = new SearchCriteria.Builder()
            .start(start)
            .limit(limit > 0 ? limit : null)
            .includeInactive(includeInactive)
            .build();

        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    return dictionaryAttributeService.findByCriteria(securityInfo, Long.parseLong(dictionaryIdentifier), searchCriteria);
                } else {
                    return dictionaryAttributeService.findByCriteria(securityInfo, dictionaryIdentifier, searchCriteria);
                }
            })
            .switchIfEmpty(Mono.defer(() ->
                Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No Dictionary Attributes found for [" + dictionaryIdentifier + "]")))
            );
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<DictionaryAttribute> streamAttributes(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @RequestParam(value = "start", defaultValue = "0") int start,
        @RequestParam(value = "limit", defaultValue = "25") int limit,
        @RequestParam(value = "includeInactive", defaultValue = "false") boolean includeInactive
    ) {
        SearchCriteria searchCriteria = new SearchCriteria.Builder()
            .start(start)
            .limit(limit > 0 ? limit : null)
            .includeInactive(includeInactive)
            .build();

        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    return dictionaryAttributeService.findByCriteria(securityInfo, Long.parseLong(dictionaryIdentifier), searchCriteria);
                } else {
                    return dictionaryAttributeService.findByCriteria(securityInfo, dictionaryIdentifier, searchCriteria);
                }
            })
            .switchIfEmpty(Mono.defer(() ->
                Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No Dictionary Attributes found for [" + dictionaryIdentifier + "]")))
            );
    }

    @GetMapping(value = "attributeIdentifier", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Mono<DictionaryAttribute> findAttribute(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @PathVariable("attributeIdentifier") String attributeIdentifier
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    if (StringUtils.isNumeric(attributeIdentifier)) {
                        return dictionaryAttributeService.findById(securityInfo, Long.parseLong(dictionaryIdentifier), Long.parseLong(attributeIdentifier));
                    } else {
                        return dictionaryAttributeService.findByMeaning(securityInfo, Long.parseLong(dictionaryIdentifier), attributeIdentifier);
                    }
                } else {
                    if (StringUtils.isNumeric(attributeIdentifier)) {
                        return dictionaryAttributeService.findById(securityInfo, dictionaryIdentifier, Long.parseLong(attributeIdentifier));
                    } else {
                        return dictionaryAttributeService.findByMeaning(securityInfo, dictionaryIdentifier, attributeIdentifier);
                    }
                }
            })
            .switchIfEmpty(Mono.defer(() ->
                Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "Dictionary Attribute was not found for [" + dictionaryIdentifier + "," + attributeIdentifier + "]"))
            ));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional()
    public Mono<DictionaryAttribute> createAttribute(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @RequestBody DictionaryAttribute attribute
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    return dictionaryAttributeService.insert(securityInfo, Long.parseLong(dictionaryIdentifier), attribute);
                } else {
                    return dictionaryAttributeService.insert(securityInfo, dictionaryIdentifier, attribute);
                }
            });
    }

    @PutMapping(value = "attributeIdentifier", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional()
    public Mono<DictionaryAttribute> updateAttribute(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @RequestBody DictionaryAttribute attribute
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    return dictionaryAttributeService.update(securityInfo, Long.parseLong(dictionaryIdentifier), attribute);
                } else {
                    return dictionaryAttributeService.update(securityInfo, dictionaryIdentifier, attribute);
                }
            });
    }

    @DeleteMapping("attributeIdentifier")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional()
    public Mono<Void> deleteAttribute(
        @PathVariable("dictionaryIdentifier") String dictionaryIdentifier,
        @PathVariable("attributeIdentifier") String attributeIdentifier
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (StringUtils.isNumeric(dictionaryIdentifier)) {
                    if (StringUtils.isNumeric(attributeIdentifier)) {
                        return dictionaryAttributeService.deleteById(securityInfo, Long.parseLong(dictionaryIdentifier), Long.parseLong(attributeIdentifier));
                    } else {
                        return dictionaryAttributeService.deleteByMeaning(securityInfo, Long.parseLong(dictionaryIdentifier), attributeIdentifier);
                    }
                } else {
                    if (StringUtils.isNumeric(attributeIdentifier)) {
                        return dictionaryAttributeService.deleteById(securityInfo, dictionaryIdentifier, Long.parseLong(attributeIdentifier));
                    } else {
                        return dictionaryAttributeService.deleteByMeaning(securityInfo, dictionaryIdentifier, attributeIdentifier);
                    }
                }
            });
    }
}
