package com.cagst.bom.tenant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.cagst.bom.feature.ValidFeatures;
import com.cagst.bom.role.Role;
import com.cagst.bom.role.RoleRepository;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.webflux.exception.BadRequestResourceException;
import com.cagst.bom.spring.webflux.exception.ConflictResourceException;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import com.cagst.bom.tenant.event.TenantEventPublisher;
import com.cagst.bom.tenant.feature.TenantFeature;
import com.cagst.bom.tenant.feature.TenantFeatureRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An implementation of the {@link TenantService} that provides the business logic needed to properly
 * retrieve and persist {@link Tenant} resources.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;
    private final TenantFeatureRepository tenantFeatureRepository;
    private final RoleRepository roleRepository;

    private final TenantEventPublisher tenantEventPublisher;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository,
                             TenantFeatureRepository tenantFeatureRepository,
                             RoleRepository roleRepository,
                             TenantEventPublisher tenantEventPublisher
    ) {
        this.tenantRepository = tenantRepository;
        this.tenantFeatureRepository = tenantFeatureRepository;
        this.roleRepository = roleRepository;
        this.tenantEventPublisher = tenantEventPublisher;
    }

    @Override
    public Mono<Tenant> findById(@NonNull SecurityInfo securityInfo, int tenantId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return tenantRepository.findById(securityInfo, tenantId);
    }

    @Override
    public Flux<Tenant> findByIds(@NonNull SecurityInfo securityInfo, @NonNull Collection<Integer> ids) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(ids, "Argument [ids] cannot be null or empty");

        return tenantRepository.findByIds(securityInfo, ids);
    }

    @Override
    public Mono<Tenant> findByMnemonic(@NonNull SecurityInfo securityInfo, @NonNull String mnemonic) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(mnemonic, "Argument [mnemonic] cannot be null or empty");

        return tenantRepository.findByMnemonic(securityInfo, mnemonic);
    }

    @Override
    public Flux<Tenant> findByMnemonics(@NonNull SecurityInfo securityInfo, @NonNull Collection<String> mnemonics) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(mnemonics, "Argument [mnemonics] cannot be null or empty");

        return tenantRepository.findByMnemonics(securityInfo, mnemonics);
    }

    @Override
    public Flux<Tenant> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(searchCriteria, "Argument [searchCriteria] cannot be null");

        return tenantRepository.findByCriteria(securityInfo, searchCriteria);
    }

    @Override
    public Flux<Tenant> findByIdentifiers(@NonNull SecurityInfo securityInfo, @NonNull Collection<String> identifiers) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(identifiers, "Argument [identifiers] cannot be null or empty");

        List<Integer> ids = new ArrayList<>();
        List<String> mnemonics = new ArrayList<>();

        identifiers.forEach(identifier -> {
            if (StringUtils.isNumeric(identifier)) {
                ids.add(Integer.valueOf(identifier));
            } else {
                mnemonics.add(identifier);
            }
        });

        return Flux.merge(
            tenantRepository.findByIds(securityInfo, ids),
            tenantRepository.findByMnemonics(securityInfo, mnemonics)
        ).distinct(Tenant::mnemonic);
    }

    @Override
    public Mono<Tenant> insert(@NonNull SecurityInfo securityInfo,
                               @NonNull Tenant tenant,
                               @Nullable Set<String> featureMeanings
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(tenant, "Argument [tenant] cannot be null");

        // TODO: Import the Dictionaries for the Core feature (and any feature they selected during
        //       the registration process).

        return isValid(tenant)
            .flatMap(__ -> checkForDuplicates(securityInfo, tenant))
            .flatMap(__ -> tenantRepository.insertTenant(securityInfo, tenant))
            .flatMap(insertedTenant -> associateFeatures(
                new SecurityInfo.Builder().from(securityInfo).tenantId(insertedTenant.tenantId()).build(),
                insertedTenant,
                CollectionUtils.isEmpty(featureMeanings) ? Collections.singleton(ValidFeatures.CORE.name()) : featureMeanings
            ))
            .flatMap(insertedTenant -> createAdminRole(
                new SecurityInfo.Builder().from(securityInfo).tenantId(insertedTenant.tenantId()).build(),
                insertedTenant
            ))
            .doOnSuccess(insertedTenant -> tenantEventPublisher.publishTenantCreatedEvent(
                new SecurityInfo.Builder().from(securityInfo).tenantId(insertedTenant.tenantId()).build(),
                insertedTenant)
            );
    }

    @Override
    public Mono<Tenant> update(@NonNull SecurityInfo securityInfo, @NonNull Tenant tenant) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(tenant, "Argument [tenant] cannot be null");
        Assert.notNull(tenant.tenantId(), "Argument [tenant] must be an existing Tenant");

        return isValid(tenant)
            .flatMap(__ -> checkForDuplicates(securityInfo, tenant))
            .flatMap(result -> {
                if (result == tenant) {
                    // if the 'checkForDuplicate' returned the same Tenant we are trying to update
                    // we still want to ensure the Tenant exists
                    return checkExists(securityInfo, tenant.tenantId());
                } else {
                    // if the 'checkForDuplicate' returned a different Tenant
                    // then the Tenant was found but won't cause a duplicate...so the Tenant does exist
                    return Mono.just(result);
                }
            })
            .flatMap(found -> {
                if (found.equals(tenant)) {
                    // if the found Tenant is the same as the specified Tenant
                    // there is nothing to do (no save is necessary)
                    return Mono.just(tenant);
                } else {
                    return tenantRepository.updateTenant(securityInfo, tenant);
                }
            });
    }

    @Override
    public Mono<Void> deleteById(@NonNull SecurityInfo securityInfo, int tenantId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return checkExists(securityInfo, tenantId)
            .flatMap(found -> {
                if (found.active()) {
                    return tenantRepository.updateTenant(
                        securityInfo,
                        new Tenant.Builder().from(found).active(false).build()
                    );
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    @Override
    public Mono<Void> deleteByMnemonic(@NonNull SecurityInfo securityInfo, @NonNull String mnemonic) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(mnemonic, "Argument [mnemonic] cannot be null or empty");

        return checkExists(securityInfo, mnemonic)
            .flatMap(found -> {
                if (found.active()) {
                    return tenantRepository.updateTenant(
                        securityInfo,
                        new Tenant.Builder().from(found).active(false).build()
                    );
                } else {
                    return Mono.just(found);
                }
            })
            .doOnSuccess(tent -> tenantEventPublisher.publishTenantDeletedEvent(securityInfo, tent))
            .then();
    }

    @Override
    public Flux<Tenant> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<Tenant> tenants) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return tenants.flatMap(tenant -> {
            if (tenant.tenantId() == null) {
                return insert(securityInfo, tenant, null);
            } else {
                return update(securityInfo, tenant);
            }
        });
    }

    /**
     * Will check that the specified Tenant is valid.
     * <ul>
     *     <li>The mnemonic cannot be numeric</li>
     * </ul>
     *
     * @return A {@link Mono} that either emits an error (if invalid) or emits the same Tenant (if valid).
     */
    private Mono<Tenant> isValid(Tenant tenant) {
        if (StringUtils.isNumeric(tenant.mnemonic())) {
            return Mono.error(new BadRequestResourceException("The mnemonic cannot be numeric"));
        } else {
            return Mono.just(tenant);
        }
    }

    /**
     * Will check to ensure the specified Tenant won't cause a duplicate record.
     * Duplicate is determined if more than 1 record would have the same 'mnemonic'.
     *
     * @return A {@link Mono} that either emits an error (if it would cause a duplicate record),
     * emits the existing Tenant (if found but no duplicate), or emits the specified Tenant (if not found and no duplicate).
     */
    private Mono<Tenant> checkForDuplicates(SecurityInfo securityInfo, Tenant check) {
        return tenantRepository.findByMnemonic(securityInfo, check.mnemonic())
            .flatMap(found -> {
                if (check.tenantId() == null || !Objects.equals(check.tenantId(), found.tenantId())) {
                    return Mono.error(new ConflictResourceException("A Tenant already exists with the mnemonic of [" + check.mnemonic() + "]"));
                } else {
                    return Mono.just(found);
                }
            })
            .defaultIfEmpty(check);
    }

    private Mono<Tenant> checkExists(SecurityInfo securityInfo, int tenantId) {
        return findById(securityInfo, tenantId)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Tenant [" + tenantId + "] was not found")));
    }

    private Mono<Tenant> checkExists(SecurityInfo securityInfo, String mnemonic) {
        return findByMnemonic(securityInfo, mnemonic)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Tenant [" + mnemonic + "] was not found")));
    }

    private Mono<Tenant> associateFeatures(SecurityInfo securityInfo, Tenant tenant, Set<String> featureMeanings) {
        return tenantFeatureRepository.save(
            securityInfo,
            tenantFeatureRepository.find(securityInfo)
                .map(feature -> featureMeanings.contains(feature.meaning()) ?
                    new TenantFeature.Builder().from(feature).active(true).build() : feature
                )
        ).collectList()
         .map(features -> new Tenant.Builder().from(tenant).features(features).build());
    }

    private Mono<Tenant> createAdminRole(SecurityInfo securityInfo, Tenant tenant) {
        return roleRepository.insert(securityInfo, new Role.Builder()
            .name("Administrator")
            .fullAccess(true)
            .build()
        ).map(__ -> tenant);
    }
}
