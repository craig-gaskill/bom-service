package com.cagst.bom.dictionary.value;

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
 * An implementation of the {@link DictionaryValueService} interface.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class DictionaryValueServiceImpl implements DictionaryValueService {
    private final DictionaryValueRepository dictionaryValueRepository;

    @Autowired
    public DictionaryValueServiceImpl(DictionaryValueRepository dictionaryValueRepository) {
        this.dictionaryValueRepository = dictionaryValueRepository;
    }

    @Override
    public Mono<DictionaryValue> findById(@NonNull SecurityInfo securityInfo,
                                          long dictionaryId,
                                          long dictionaryValueId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return dictionaryValueRepository.findById(securityInfo, dictionaryId, dictionaryValueId);
    }

    @Override
    public Mono<DictionaryValue> findById(@NonNull SecurityInfo securityInfo,
                                          @NonNull String dictionaryMeaning,
                                          long dictionaryValueId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        return dictionaryValueRepository.findById(securityInfo, dictionaryMeaning, dictionaryValueId);
    }

    @Override
    public Mono<DictionaryValue> findByMeaning(@NonNull SecurityInfo securityInfo,
                                               long dictionaryId,
                                               @NonNull String dictionaryValueMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryValueMeaning, "Argument [dictionaryValueMeaning] cannot be null or empty");

        return dictionaryValueRepository.findByMeaning(securityInfo, dictionaryId, dictionaryValueMeaning);
    }

    @Override
    public Mono<DictionaryValue> findByMeaning(@NonNull SecurityInfo securityInfo,
                                               @NonNull String dictionaryMeaning,
                                               @NonNull String dictionaryValueMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.hasText(dictionaryValueMeaning, "Argument [dictionaryValueMeaning] cannot be null or empty");

        return dictionaryValueRepository.findByMeaning(securityInfo, dictionaryMeaning, dictionaryValueMeaning);
    }

    @Override
    public Flux<DictionaryValue> findByCriteria(@NonNull SecurityInfo securityInfo,
                                                long dictionaryId,
                                                @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return dictionaryValueRepository.findByCriteria(securityInfo, dictionaryId, searchCriteria);
    }

    @Override
    public Flux<DictionaryValue> findByCriteria(@NonNull SecurityInfo securityInfo,
                                                @NonNull String dictionaryMeaning,
                                                @Nullable SearchCriteria searchCriteria
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        return dictionaryValueRepository.findByCriteria(securityInfo, dictionaryMeaning, searchCriteria);
    }

    @Override
    public Mono<DictionaryValue> insert(@NonNull SecurityInfo securityInfo,
                                        long dictionaryId,
                                        DictionaryValue dictionaryValue
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionaryValue, "Argument [dictionaryValue] cannot be null");

        return isValid(dictionaryValue)
            .flatMap(__ -> checkForDuplicate(securityInfo, dictionaryId, dictionaryValue))
            .flatMap(__ -> dictionaryValueRepository.insert(securityInfo, dictionaryValue, dictionaryId));
    }

    @Override
    public Mono<DictionaryValue> insert(@NonNull SecurityInfo securityInfo,
                                        @NonNull String dictionaryMeaning,
                                        DictionaryValue dictionaryValue
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.notNull(dictionaryValue, "Argument [dictionaryValue] cannot be null");

        return isValid(dictionaryValue)
            .flatMap(__ -> checkForDuplicate(securityInfo, dictionaryMeaning, dictionaryValue))
            .flatMap(__ -> dictionaryValueRepository.insert(securityInfo, dictionaryValue, dictionaryMeaning));
    }

    @Override
    public Mono<DictionaryValue> update(@NonNull SecurityInfo securityInfo,
                                        long dictionaryId,
                                        DictionaryValue dictionaryValue
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionaryValue, "Argument [dictionaryValue] cannot be null");

        return isValid(dictionaryValue)
            .flatMap(__ -> checkForDuplicate(securityInfo, dictionaryId, dictionaryValue))
            .flatMap(result -> {
                if (result == dictionaryValue) {
                    // if the 'checkForDuplicate' returned the same DictionaryValue
                    // we still want to ensure the DictionaryValue exists
                    return checkExists(securityInfo, dictionaryId, dictionaryValue.dictionaryValueId());
                } else {
                    // if the 'checkForDuplicate' returned a different DictionaryValue
                    // then the DictionaryValue was found but won't cause a duplicate.
                    return Mono.just(result);
                }
            })
            .flatMap(found -> {
                if (found.equals(dictionaryValue)) {
                    // if the found DictionaryValue is the same as the specified DictionaryValue
                    // there is nothing to do (no save is necessary)
                    return Mono.just(dictionaryValue);
                } else {
                    return dictionaryValueRepository.update(securityInfo, dictionaryValue);
                }
            });
    }

    @Override
    public Mono<DictionaryValue> update(@NonNull SecurityInfo securityInfo,
                                        @NonNull String dictionaryMeaning,
                                        DictionaryValue dictionaryValue
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");
        Assert.notNull(dictionaryValue, "Argument [dictionaryValue] cannot be null");

        return isValid(dictionaryValue)
            .flatMap(__ -> checkForDuplicate(securityInfo, dictionaryMeaning, dictionaryValue))
            .flatMap(result -> {
                if (result == dictionaryValue) {
                    // if the 'checkForDuplicate' returned the same DictionaryValue
                    // we still want to ensure the DictionaryValue exists
                    return checkExists(securityInfo, dictionaryMeaning, dictionaryValue.dictionaryValueId());
                } else {
                    // if the 'checkForDuplicate' returned a different DictionaryValue
                    // then the DictionaryValue was found but won't cause a duplicate.
                    return Mono.just(result);
                }
            })
            .flatMap(found -> {
                if (found.equals(dictionaryValue)) {
                    // if the found DictionaryValue is the same as the specified DictionaryValue
                    // there is nothing to do (no save is necessary)
                    return Mono.just(dictionaryValue);
                } else {
                    return dictionaryValueRepository.update(securityInfo, dictionaryValue);
                }
            });
    }

    @Override
    public Mono<Void> delete(@NonNull SecurityInfo securityInfo,
                             long dictionaryId,
                             long dictionaryValueId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return checkExists(securityInfo, dictionaryId, dictionaryValueId)
            .flatMap(found -> {
                if (found.active()) {
                    return dictionaryValueRepository.update(securityInfo, new DictionaryValue.Builder().from(found).active(false).build());
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    @Override
    public Mono<Void> delete(@NonNull SecurityInfo securityInfo,
                             @NonNull String dictionaryMeaning,
                             long dictionaryValueId
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(dictionaryMeaning, "Argument [dictionaryMeaning] cannot be null or empty");

        return checkExists(securityInfo, dictionaryMeaning, dictionaryValueId)
            .flatMap(found -> {
                if (found.active()) {
                    return dictionaryValueRepository.update(securityInfo, new DictionaryValue.Builder().from(found).active(false).build());
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    /**
     * Will check that the specified DictionaryValue is valid.
     * <ul>
     *     <li>The meaning cannot be numeric</li>
     * </ul>
     *
     * @return A {@link Mono} that either emits an error (if invalid) or emits the same DictionaryValue (if valid).
     */
    private Mono<DictionaryValue> isValid(DictionaryValue dictionaryValue) {
        if (StringUtils.isNumeric(dictionaryValue.meaning())) {
            return Mono.error(new BadRequestResourceException("The meaning cannot be numeric"));
        } else {
            return Mono.just(dictionaryValue);
        }
    }

    /**
     * Will check to ensure the specified DictionaryValue won't cause a duplicate record.
     *
     * @return A {@link Mono} that either emits an error (if it would cause a duplicate record),
     * emits the existing DictionaryValue (if found), or emits the specified DictionaryValue (if not found).
     */
    private Mono<DictionaryValue> checkForDuplicate(SecurityInfo securityInfo, long dictionaryId, DictionaryValue dictionaryValue) {
        return dictionaryValueRepository.findByMeaning(securityInfo, dictionaryId, dictionaryValue.meaning())
            .flatMap(found -> {
                if (dictionaryValue.dictionaryValueId() == null || !dictionaryValue.dictionaryValueId().equals(found.dictionaryValueId())) {
                    return Mono.error(new ConflictResourceException("A DictionaryValue already exists with the meaning of [" + dictionaryValue.meaning() + "]"));
                } else {
                    return Mono.just(found);
                }
            })
            .defaultIfEmpty(dictionaryValue);
    }

    /**
     * Will check to ensure the specified DictionaryValue won't cause a duplicate record.
     *
     * @return A {@link Mono} that either emits an error (if it would cause a duplicate record),
     * emits the existing DictionaryValue (if found), or emits the specified DictionaryValue (if not found).
     */
    private Mono<DictionaryValue> checkForDuplicate(SecurityInfo securityInfo, String dictionaryMeaning, DictionaryValue dictionaryValue) {
        return dictionaryValueRepository.findByMeaning(securityInfo, dictionaryMeaning, dictionaryValue.meaning())
            .flatMap(found -> {
                if (dictionaryValue.dictionaryValueId() == null || !dictionaryValue.dictionaryValueId().equals(found.dictionaryValueId())) {
                    return Mono.error(new ConflictResourceException("A DictionaryValue already exists with the meaning of [" + dictionaryValue.meaning() + "]"));
                } else {
                    return Mono.just(found);
                }
            })
            .defaultIfEmpty(dictionaryValue);
    }

    private Mono<DictionaryValue> checkExists(SecurityInfo securityInfo, long dictionaryId, long dictionaryValueId) {
        return findById(securityInfo, dictionaryId, dictionaryValueId)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("DictionaryValue [" + dictionaryValueId + "] was not found")));
    }

    private Mono<DictionaryValue> checkExists(SecurityInfo securityInfo, String dictionaryMeaning, long dictionaryValueId) {
        return findById(securityInfo, dictionaryMeaning, dictionaryValueId)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("DictionaryValue [" + dictionaryValueId + "] was not found")));
    }
}
