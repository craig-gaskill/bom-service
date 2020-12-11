package com.cagst.bom.dictionary.attribute;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.webflux.exception.BadRequestResourceException;
import com.cagst.bom.spring.webflux.exception.ConflictResourceException;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An implementation of the {@link DictionaryAttributeService} that provides the business logic needed to properly
 * retrieve and persist {@link DictionaryAttribute} resources.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class DictionaryAttributeServiceImpl implements DictionaryAttributeService {
    private final DictionaryAttributeRepository dictionaryAttributeRepository;

    @Autowired
    public DictionaryAttributeServiceImpl(DictionaryAttributeRepository dictionaryAttributeRepository) {
        this.dictionaryAttributeRepository = dictionaryAttributeRepository;
    }

    @Override
    public Mono<DictionaryAttribute> findById(@NonNull SecurityInfo securityInfo,
                                              long dictionaryId,
                                              long dictionaryAttributeId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return dictionaryAttributeRepository.findById(securityInfo, dictionaryId, dictionaryAttributeId);
    }

    @Override
    public Mono<DictionaryAttribute> findById(@NonNull SecurityInfo securityInfo,
                                              @NonNull String dictionaryMeaning,
                                              long dictionaryAttributeId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        return dictionaryAttributeRepository.findById(securityInfo, dictionaryMeaning, dictionaryAttributeId);
    }

    @Override
    public Mono<DictionaryAttribute> findByMeaning(@NonNull SecurityInfo securityInfo,
                                                   long dictionaryId,
                                                   @NonNull String attributeMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(attributeMeaning, "Argument [attributeMeaning] cannot be null or empty");

        return dictionaryAttributeRepository.findByMeaning(securityInfo, dictionaryId, attributeMeaning);
    }

    @Override
    public Mono<DictionaryAttribute> findByMeaning(@NonNull SecurityInfo securityInfo,
                                                   @NonNull String dictionaryMeaning,
                                                   @NonNull String attributeMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.hasText(attributeMeaning, "Argument [attributeMeaning] cannot be null or empty");

        return dictionaryAttributeRepository.findByMeaning(securityInfo, dictionaryMeaning, attributeMeaning);
    }

    @Override
    public Flux<DictionaryAttribute> findByCriteria(@NonNull SecurityInfo securityInfo,
                                                    long dictionaryId,
                                                    @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return dictionaryAttributeRepository.findByCriteria(securityInfo, dictionaryId, searchCriteria);
    }

    @Override
    public Flux<DictionaryAttribute> findByCriteria(@NonNull SecurityInfo securityInfo,
                                                    @NonNull String dictionaryMeaning,
                                                    @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        return dictionaryAttributeRepository.findByCriteria(securityInfo, dictionaryMeaning, searchCriteria);
    }

    @Override
    public Mono<DictionaryAttribute> insert(@NonNull SecurityInfo securityInfo,
                                            long dictionaryId,
                                            @NonNull DictionaryAttribute dictionaryAttribute
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionaryAttribute, "Argument [dictionaryAttribute] cannot be null");

        return isValid(dictionaryAttribute)
            .flatMap(__ -> checkForDuplicates(securityInfo, dictionaryId, dictionaryAttribute))
            .flatMap(__ -> dictionaryAttributeRepository.insert(securityInfo, dictionaryId, dictionaryAttribute));
    }

    @Override
    public Mono<DictionaryAttribute> insert(@NonNull SecurityInfo securityInfo,
                                            @NonNull String dictionaryMeaning,
                                            @NonNull DictionaryAttribute dictionaryAttribute
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.notNull(dictionaryAttribute, "Argument [dictionaryAttribute] cannot be null");

        return isValid(dictionaryAttribute)
            .flatMap(__ -> checkForDuplicates(securityInfo, dictionaryMeaning, dictionaryAttribute))
            .flatMap(__ -> dictionaryAttributeRepository.insert(securityInfo, dictionaryMeaning, dictionaryAttribute));
    }

    @Override
    public Mono<DictionaryAttribute> update(@NonNull SecurityInfo securityInfo,
                                            long dictionaryId,
                                            @NonNull DictionaryAttribute dictionaryAttribute
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionaryAttribute, "Argument [dictionaryAttribute] cannot be null");
        Assert.notNull(dictionaryAttribute.dictionaryAttributeId(), "Argument [dictionaryAttribute.dictionaryAttributeId] must be an existing Dictionary Attribute");

        return isValid(dictionaryAttribute)
            .flatMap(__ -> checkForDuplicates(securityInfo, dictionaryId, dictionaryAttribute))
            .flatMap(result -> {
                if (result == dictionaryAttribute) {
                    // if the 'checkForDuplicate' returned the same Dictionary Attribute
                    // we will want to ensure the Dictionary Attribute exists
                    return checkExists(securityInfo, dictionaryId, dictionaryAttribute.dictionaryAttributeId());
                } else {
                    // if the 'checkForDuplicate' returned a different Dictionary Attribute
                    // then the Dictionary Attribute was found but won't cause a duplicate...so the Dictionary Attribute does exist
                    return Mono.just(result);
                }
            })
            .flatMap(found -> {
                if (found.equals(dictionaryAttribute)) {
                    // if the found Dictionary Attribute is the same as the specified Dictionary Attribute
                    // there is nothing to do (no save is necessary)
                    return Mono.just(dictionaryAttribute);
                } else {
                    return dictionaryAttributeRepository.update(securityInfo, dictionaryAttribute);
                }
            });
    }

    @Override
    public Mono<DictionaryAttribute> update(@NonNull SecurityInfo securityInfo,
                                            @NonNull String dictionaryMeaning,
                                            @NonNull DictionaryAttribute dictionaryAttribute
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.notNull(dictionaryAttribute, "Argument [dictionaryAttribute] cannot be null");

        return isValid(dictionaryAttribute)
            .flatMap(__ -> checkForDuplicates(securityInfo, dictionaryMeaning, dictionaryAttribute))
            .flatMap(result -> {
                if (result == dictionaryAttribute) {
                    // if the 'checkForDuplicate' returned the same Dictionary Attribute
                    // we will want to ensure the Dictionary Attribute exists
                    return checkExists(securityInfo, dictionaryMeaning, dictionaryAttribute.dictionaryAttributeId());
                } else {
                    // if the 'checkForDuplicate' returned a different Dictionary Attribute
                    // then the Dictionary Attribute was found but won't cause a duplicate...so the Dictionary Attribute does exist
                    return Mono.just(result);
                }
            })
            .flatMap(found -> {
                if (found.equals(dictionaryAttribute)) {
                    // if the found Dictionary Attribute is the same as the specified Dictionary Attribute
                    // there is nothing to do (no save is necessary)
                    return Mono.just(dictionaryAttribute);
                } else {
                    return dictionaryAttributeRepository.update(securityInfo, dictionaryAttribute);
                }
            });
    }

    @Override
    public Mono<Void> deleteById(@NonNull SecurityInfo securityInfo,
                                 long dictionaryId,
                                 long attributeId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return checkExists(securityInfo, dictionaryId, attributeId)
            .flatMap(found -> {
                if (found.active()) {
                    return dictionaryAttributeRepository.update(securityInfo,
                        new DictionaryAttribute.Builder().from(found).active(false).build()
                    );
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    @Override
    public Mono<Void> deleteByMeaning(@NonNull SecurityInfo securityInfo,
                                      long dictionaryId,
                                      @NonNull String attributeMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(attributeMeaning, "Argument [attributeMeaning] cannot be null or empty");

        return checkExists(securityInfo, dictionaryId, attributeMeaning)
            .flatMap(found -> {
                if (found.active()) {
                    return dictionaryAttributeRepository.update(
                        securityInfo,
                        new DictionaryAttribute.Builder().from(found).active(false).build()
                    );
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    @Override
    public Mono<Void> deleteById(@NonNull SecurityInfo securityInfo,
                                 @NonNull String dictionaryMeaning,
                                 long attributeId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        return checkExists(securityInfo, dictionaryMeaning, attributeId)
            .flatMap(found -> {
                if (found.active()) {
                    return dictionaryAttributeRepository.update(
                        securityInfo,
                        new DictionaryAttribute.Builder().from(found).active(false).build()
                    );
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    @Override
    public Mono<Void> deleteByMeaning(@NonNull SecurityInfo securityInfo,
                                      @NonNull String dictionaryMeaning,
                                      @NonNull String attributeMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.hasText(attributeMeaning, "Argument [attributeMeaning] cannot be null or empty");

        return checkExists(securityInfo, dictionaryMeaning, attributeMeaning)
            .flatMap(found -> {
                if (found.active()) {
                    return dictionaryAttributeRepository.update(
                        securityInfo,
                        new DictionaryAttribute.Builder().from(found).active(false).build()
                    );
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    /**
     * Will check that the specified Dictionary Attribute is valid.
     * <ul>
     *     <li>The meaning cannot be numeric</li>
     * </ul>
     *
     * @return A {@link Mono} that either emits on error (if invalid) or emits the same Dictionary Attribute (if valid).
     */
    private Mono<DictionaryAttribute> isValid(DictionaryAttribute attribute) {
        if (StringUtils.isNumeric(attribute.meaning())) {
            return Mono.error(new BadRequestResourceException("The meaning cannot be numeric"));
        } else {
            return Mono.just(attribute);
        }
    }

    /**
     * Will check to ensure the specified Dictionary Attribute won't cause a duplicate record.
     *
     * @return A {@link Mono} that either emits an error (if it would cause a duplicate record),
     * emits the existing Dictionary Attribute (if found), or emits the specified Dictionary Attribute (if not found).
     */
    private Mono<DictionaryAttribute> checkForDuplicates(SecurityInfo securityInfo, long dictionaryId, DictionaryAttribute attribute) {
        return dictionaryAttributeRepository.findByMeaning(securityInfo, dictionaryId, attribute.meaning())
            .flatMap(found -> {
                if (attribute.dictionaryAttributeId() == null || !attribute.dictionaryAttributeId().equals(found.dictionaryAttributeId())) {
                    return Mono.error(new ConflictResourceException("A Dictionary Attribute already exists with the meaning of [" + attribute.meaning() + "]"));
                } else {
                    return Mono.just(found);
                }
            })
            .defaultIfEmpty(attribute);
    }

    /**
     * Will check to ensure the specified Dictionary Attribute won't cause a duplicate record.
     *
     * @return A {@link Mono} that either emits an error (if it would cause a duplicate record),
     * emits the existing Dictionary Attribute (if found), or emits the specified Dictionary Attribute (if not found).
     */
    private Mono<DictionaryAttribute> checkForDuplicates(SecurityInfo securityInfo, String dictionaryMeaning, DictionaryAttribute attribute) {
        return dictionaryAttributeRepository.findByMeaning(securityInfo, dictionaryMeaning, attribute.meaning())
            .flatMap(found -> {
                if (attribute.dictionaryAttributeId() == null || !attribute.dictionaryAttributeId().equals(found.dictionaryAttributeId())) {
                    return Mono.error(new ConflictResourceException("A Dictionary Attribute already exists with the meaning of [" + attribute.meaning() + "]"));
                } else {
                    return Mono.just(found);
                }
            })
            .defaultIfEmpty(attribute);
    }

    private Mono<DictionaryAttribute> checkExists(SecurityInfo securityInfo, long dictionaryId, long attributeId) {
        return dictionaryAttributeRepository.findById(securityInfo, dictionaryId, attributeId)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Dictionary Attribute [" + dictionaryId + ", " + attributeId + "] was not found")));
    }

    private Mono<DictionaryAttribute> checkExists(SecurityInfo securityInfo, long dictionaryId, String attributeMeaning) {
        return dictionaryAttributeRepository.findByMeaning(securityInfo, dictionaryId, attributeMeaning)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Dictionary Attribute [" + dictionaryId + ", " + attributeMeaning + "] was not found")));
    }

    private Mono<DictionaryAttribute> checkExists(SecurityInfo securityInfo, String dictionaryMeaning, long attributeId) {
        return dictionaryAttributeRepository.findById(securityInfo, dictionaryMeaning, attributeId)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Dictionary Attribute [" + dictionaryMeaning + ", " + attributeId + "] was not found")));
    }

    private Mono<DictionaryAttribute> checkExists(SecurityInfo securityInfo, String dictionaryMeaning, String attributeMeaning) {
        return dictionaryAttributeRepository.findByMeaning(securityInfo, dictionaryMeaning, attributeMeaning)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Dictionary Attribute [" + dictionaryMeaning + ", " + attributeMeaning + "] was not found")));
    }
}
