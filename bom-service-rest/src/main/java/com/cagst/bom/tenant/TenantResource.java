package com.cagst.bom.tenant;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines endpoints to retrieve / persist {@link Tenant} resources.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("tenants")
public class TenantResource {
    private final TenantService tenantService;

    @Autowired
    public TenantResource(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Tenant> getTenants(
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
            .flatMapMany(securityInfo -> tenantService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No Tenants found")));
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Tenant> streamTenants(
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
            .flatMapMany(securityInfo -> tenantService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No Tenants found")));
    }

    @GetMapping(value = "{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Mono<Tenant> getTenant(@PathVariable("identifier") String identifier) {
        if (StringUtils.isNumeric(identifier)) {
            return ReactiveSecurityContextHolder.getSecurityInfo()
                .flatMap(securityInfo -> tenantService.findById(securityInfo, Integer.parseInt(identifier)))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "Tenant was not found for ID [" + identifier + "]")));
        } else {
            return ReactiveSecurityContextHolder.getSecurityInfo()
                .flatMap(securityInfo -> tenantService.findByMnemonic(securityInfo, identifier))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "Tenant was not found for mnemonic [" + identifier + "]")));
        }
    }

    @RequestMapping(value = "{mnemonic}", method = RequestMethod.HEAD)
    public Mono<Void> checkTenantExists(@PathVariable("mnemonic") String mnemonic) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> tenantService.findByMnemonic(securityInfo, mnemonic).then())
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "Tenant was not found with mnemonic [" + mnemonic + "]"))));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Mono<Tenant> createTenant(@RequestBody Tenant tenant) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> tenantService.insert(securityInfo, tenant, null));
    }

    @PutMapping(
        value = "{tenantId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public Mono<Tenant> updateTenant(@PathVariable("tenantId") int tenantId, @RequestBody Tenant tenant) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> tenantService.update(securityInfo, tenant));
    }

    @DeleteMapping(value = "{identifier}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public Mono<Void> deleteTenant(@PathVariable("identifier") String identifier) {
        if (StringUtils.isNumeric(identifier)) {
            return ReactiveSecurityContextHolder.getSecurityInfo()
                .flatMap(securityInfo -> tenantService.deleteById(securityInfo, Integer.parseInt(identifier)));
        } else {
            return ReactiveSecurityContextHolder.getSecurityInfo()
                .flatMap(securityInfo -> tenantService.deleteByMnemonic(securityInfo, identifier));
        }
    }

    @PostMapping(
        value = "list",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public Flux<Tenant> listTenants(@RequestBody List<String> identifiers) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> tenantService.findByIdentifiers(securityInfo, identifiers));
    }

    @PostMapping(
        value = "save",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public Flux<Tenant> saveTenants(@RequestBody Flux<Tenant> tenants) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> tenantService.save(securityInfo, tenants));
    }

    @GetMapping(value = "export", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Tenant> exportTenants() {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> this.tenantService.findByCriteria(securityInfo, null))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No Tenants found"))));
    }

    @PostMapping(
        value = "import",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public Mono<Void> importTenants(@RequestParam ImportType importType,
                                    @RequestBody Flux<Tenant> tenants
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> tenantService.save(securityInfo, tenants))
            .then();
    }
}
