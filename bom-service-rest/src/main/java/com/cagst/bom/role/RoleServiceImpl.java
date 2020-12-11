package com.cagst.bom.role;

import java.util.Collection;

import com.cagst.bom.role.permission.RolePermissionRepository;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An implementation of the {@link RoleService} that provides the business logic needed to properly
 * retrieve and persist {@link Role} resources.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository) {
        this.roleRepository = roleRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

    @NonNull
    @Override
    public Mono<Role> findById(@NonNull SecurityInfo securityInfo, long roleId, @Nullable Collection<String> with) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return roleRepository.findById(securityInfo, roleId)
            .flatMap(role -> findChildElements(securityInfo, role, with));
    }

    @NonNull
    @Override
    public Flux<Role> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Long> ids) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(ids, "Argument [ids] cannot be null");

        return roleRepository.findByIds(securityInfo, ids);
    }

    @NonNull
    @Override
    public Flux<Role> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return roleRepository.findByCriteria(securityInfo, searchCriteria);
    }

    @NonNull
    @Override
    public Mono<Role> insert(@NonNull SecurityInfo securityInfo, @NonNull Role role) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(role, "Argument [role] cannot be null");

        return isValid(role)
            .flatMap(__ -> checkForDuplicates(securityInfo, role))
            .flatMap(__ -> roleRepository.insert(securityInfo, role));
    }

    @NonNull
    @Override
    public Mono<Role> update(@NonNull SecurityInfo securityInfo, @NonNull Role role) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(role, "Argument [role] cannot be null");

        return isValid(role)
            .flatMap(__ -> checkForDuplicates(securityInfo, role))
            .flatMap(result -> {
                if (result == role) {
                    // if the 'checkForDuplicate' returned the same Role
                    // we sill want to ensure the Role exists
                    return checkExists(securityInfo, role.roleId());
                } else {
                    // if the 'checkForDuplicate' returned a different Role
                    // then the Role was found but won't cause a duplicate...so the Role does exist
                    return Mono.just(role);
                }
            })
            .flatMap(found -> {
                if (found.equals(role)) {
                    // if the found Role is the same as the specified Role
                    // there is nothing to do (no save is necessary)
                    return Mono.just(role);
                } else {
                    return roleRepository.update(securityInfo, role);
                }
            });
    }

    @NonNull
    @Override
    public Mono<Void> deleteById(@NonNull SecurityInfo securityInfo, long roleId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return checkExists(securityInfo, roleId)
            .flatMap(found -> {
                if (found.active()) {
                    return roleRepository.update(securityInfo, new Role.Builder().from(found).active(false).build());
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    /**
     * Will check tha the specified Role is valid.
     */
    private Mono<Role> isValid(Role role) {
        return Mono.just(role);
    }

    /**
     * Will check to ensure the specified Role won't cause a duplicate record.
     */
    private Mono<Role> checkForDuplicates(SecurityInfo securityInfo, Role role) {
        return Mono.just(role);
    }

    /**
     * Will check to see if the specified Role exists (based upon it's unique identifier).
     */
    private Mono<Role> checkExists(SecurityInfo securityInfo, long roleId) {
        return findById(securityInfo, roleId, null)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Role [" + roleId + "] was not found")));
    }

    private Mono<Role> findChildElements(SecurityInfo securityInfo, Role role, Collection<String> with) {
        if (CollectionUtils.isEmpty(with) || with.stream().noneMatch("permissions"::equalsIgnoreCase)) {
            return Mono.just(role);
        }

        return rolePermissionRepository.findForRoleId(securityInfo, role.roleId())
            .collectList()
            .map(results -> new Role.Builder()
                .from(role)
                .permissions(results)
                .build()
            );
    }

    private Mono<Role> saveChildElements(SecurityInfo securityInfo, Role role) {
        return Mono.just(role);
    }
}
