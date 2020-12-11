package com.cagst.bom.role;

import java.util.Objects;
import java.util.Set;

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
 * Defines endpoints to retrieve / persist {@link Role} resources.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("roles")
public class RoleResource {
    private final RoleService roleService;

    @Autowired
    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Role> getRoles(
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
            .flatMapMany(securityInfo -> roleService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<Role> streamRoles(
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
            .flatMapMany(securityInfo -> roleService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @GetMapping(value = "{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Mono<Role> getRole(@PathVariable("roleId") long roleId,
                              @RequestParam(value = "with", required = false) Set<String> with
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> roleService.findById(securityInfo, roleId, with))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT))));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Mono<Role> createRole(@RequestBody Role role) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> roleService.insert(securityInfo, role));
    }

    @PutMapping(
        value = "{roleId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public Mono<Role> updateRole(@PathVariable("roleId") long roleId,
                                 @RequestBody Role role
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> {
                if (Objects.equals(roleId, role.roleId())) {
                    return roleService.update(securityInfo, role);
                } else {
                    return Mono.defer(() ->
                        Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "The [roleId] does not match the ID of the Role passed in"))
                    );
                }
            });
    }

    @DeleteMapping("{roleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public Mono<Void> deleteRole(@PathVariable("roleId") long roleId) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> roleService.deleteById(securityInfo, roleId));
    }
}
