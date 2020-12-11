package com.cagst.bom.user;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Defines endpoints to retrieve / persist {@link User} resources.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("users")
public class UserResource {
    private final UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<User> getUsers(
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
            .flatMapMany(securityInfo -> userService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No Users found"))));
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @Transactional(readOnly = true)
    public Flux<User> streamUsers(
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
            .flatMapMany(securityInfo -> userService.findByCriteria(securityInfo, searchCriteria))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "No Users found"))));
    }

    @GetMapping(value = "{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional(readOnly = true)
    public Mono<User> getUser(@PathVariable("userId") long userId) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> userService.findById(securityInfo, userId))
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "User was not found with ID [" + userId + "]"))));
    }

    @RequestMapping(value = "{username}", method = RequestMethod.HEAD)
    public Mono<Void> checkUserExists(@PathVariable("username") String username) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> userService.findByUsername(username).then())
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT, "User was not found with username [" + username + "]"))));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Mono<User> createUser(@RequestBody User user) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> userService.insert(securityInfo, user));
    }

    @PutMapping(
        value = "{userId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public Mono<User> updateUser(@PathVariable("userId") long userId, @RequestBody User user) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> userService.update(securityInfo, user));
    }

    @DeleteMapping(value = "{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public Mono<Void> deleteUser(@PathVariable("userId") long userId) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> userService.delete(securityInfo, userId));
    }

    @PostMapping(
        value = "list",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public Flux<User> listUsers(@RequestBody List<Long> ids) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> userService.findByIds(securityInfo, ids));
    }

    @PostMapping(
        value = "save",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public Flux<User> saveUsers(@RequestBody Flux<User> users) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMapMany(securityInfo -> userService.save(securityInfo, users));
    }

    @PutMapping(value = "{userId}/lock", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Mono<User> lockUser(@PathVariable("userId") long userId,
                               @RequestParam(value = "lockType", defaultValue = "Automatic") AccountLockedType lockedType
    ) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> userService.lockUser(securityInfo, userId, lockedType));
    }

    @PutMapping(value = "{userId}/unlock", produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public Mono<User> unlockUser(@PathVariable("userId") long userId) {
        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo -> userService.unlockUser(securityInfo, userId));
    }
}
