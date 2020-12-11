package com.cagst.bom.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.cagst.bom.dictionary.value.DictionaryValueRepository;
import com.cagst.bom.imports.ImportType;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.webflux.exception.BadRequestResourceException;
import com.cagst.bom.spring.webflux.exception.ConflictResourceException;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An implementation of the {@link DictionaryService} that provides the business logic needed to properly
 * retrieve and persist {@link Dictionary} resources.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class DictionaryServiceImpl implements DictionaryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DictionaryServiceImpl.class);

    private final DictionaryRepository dictionaryRepository;
    private final DictionaryValueRepository dictionaryValueRepository;

    @Autowired
    public DictionaryServiceImpl(@NonNull DictionaryRepository dictionaryRepository,
                                 @NonNull DictionaryValueRepository dictionaryValueRepository
    ) {
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryValueRepository = dictionaryValueRepository;
    }

    @Override
    public Mono<Dictionary> findById(@NonNull SecurityInfo securityInfo, long dictionaryId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return dictionaryRepository.findById(securityInfo, dictionaryId);
    }

    @Cacheable(value = "cache-dictionaries", key = "#meaning")
    @Override
    public Mono<Dictionary> findByMeaning(@NonNull SecurityInfo securityInfo, @NonNull String meaning) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(meaning, "Argument [meaning] cannot be null or empty");

        LOGGER.info("Calling findByMeaning for [{}]", meaning);

        return dictionaryRepository.findByMeaning(securityInfo, meaning);
    }

    @Override
    public Flux<Dictionary> findByCriteria(@NonNull SecurityInfo securityInfo, @Nullable SearchCriteria searchCriteria) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return dictionaryRepository.findByCriteria(securityInfo, searchCriteria);
    }

    @Override
    public Mono<Dictionary> insert(@NonNull SecurityInfo securityInfo,
                                   @NonNull Dictionary dictionary,
                                   @NonNull String featureMeaning
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionary, "Argument [dictionary] cannot be null");
        Assert.isNull(dictionary.dictionaryId(), "Argument [dictionary.dictionaryId] must be null");

        return isValid(dictionary)
            .flatMap(__ -> checkForDuplicates(securityInfo, dictionary))
            .flatMap(__ -> dictionaryRepository.insert(securityInfo, dictionary, featureMeaning))
            .flatMap(inserted -> saveChildElements(securityInfo, inserted, true));
    }

    @Override
    public Mono<Dictionary> update(@NonNull SecurityInfo securityInfo, @NonNull Dictionary dictionary) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(dictionary, "Argument [dictionary] cannot be null");
        Assert.notNull(dictionary.dictionaryId(), "Argument [dictionary] must be an existing Dictionary");

        return isValid(dictionary)
            .flatMap(__ -> checkForDuplicates(securityInfo, dictionary))
            .flatMap(result -> {
                if (result == dictionary) {
                    // if the 'checkForDuplicate' returned the same Dictionary
                    // we sill want to ensure the Dictionary exists
                    return checkExists(securityInfo, dictionary.dictionaryId());
                } else {
                    // if the 'checkForDuplicate' returned a different Dictionary
                    // then the Dictionary was found but won't cause a duplicate...so the Dictionary does exist
                    return Mono.just(result);
                }
            })
            .flatMap(found -> {
                if (found.equals(dictionary)) {
                    // if the found Dictionary is the same as the specified Dictionary
                    // there is nothing to do (no save is necessary)
                    return Mono.just(dictionary);
                } else {
                    return dictionaryRepository.update(securityInfo, dictionary);
                }
            });
    }

    @Override
    public Mono<Void> deleteById(@NonNull SecurityInfo securityInfo, long dictionaryId) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return checkExists(securityInfo, dictionaryId)
            .flatMap(found -> {
                if (found.active()) {
                    return dictionaryRepository.update(securityInfo, new Dictionary.Builder().from(found).active(false).build());
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    @Override
    public Mono<Void> deleteByMeaning(@NonNull SecurityInfo securityInfo, @NonNull String meaning) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(meaning, "Argument [meaning] cannot be null or empty");

        return checkExists(securityInfo, meaning)
            .flatMap(found -> {
                if (found.active()) {
                    return dictionaryRepository.update(
                        securityInfo,
                        new Dictionary.Builder()
                            .from(found)
                            .active(false)
                            .build()
                    );
                } else {
                    return Mono.just(found);
                }
            })
            .then();
    }

    @Override
    public Flux<Dictionary> findByIdentifiers(@NonNull SecurityInfo securityInfo, @NonNull List<String> identifiers) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notEmpty(identifiers, "Argument [identifiers] cannot be null or empty");

        List<Long> ids = new ArrayList<>();
        List<String> meanings = new ArrayList<>();

        identifiers.forEach(identifier -> {
            if (StringUtils.isNumeric(identifier)) {
                ids.add(Long.valueOf(identifier));
            } else {
                meanings.add(identifier);
            }
        });

        return Flux.merge(
            dictionaryRepository.findByIds(securityInfo, ids),
            dictionaryRepository.findByMeanings(securityInfo, meanings)
        ).distinct(Dictionary::meaning);
    }

    @Override
    public Flux<Dictionary> save(@NonNull SecurityInfo securityInfo, @NonNull Flux<Dictionary> dictionaries, String featureMeaning) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return dictionaries.flatMap(dictionary -> {
            if (dictionary.dictionaryId() == null) {
                return insert(securityInfo, dictionary, featureMeaning)
                    .flatMap(result -> saveChildElements(securityInfo, result, false));
            } else {
                return update(securityInfo, dictionary)
                    .flatMap(result -> saveChildElements(securityInfo, result, false));
            }
        });
    }

    @Override
    public Flux<Dictionary> exportDictionaries(SecurityInfo securityInfo) {
        return null;
    }

    @Override
    public Mono<Void> importDictionaries(@NonNull SecurityInfo securityInfo,
                                         ImportType importType,
                                         String featureMeaning,
                                         Flux<Dictionary> dictionaries
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");

        return dictionaries.flatMap(dictionary -> {
            if (importType == ImportType.ADD_ONLY) {
                if (Objects.isNull(dictionary.dictionaryId())) {
                    return insert(securityInfo, dictionary, featureMeaning)
                        .map(inserted -> saveChildElements(securityInfo, inserted, true));
                } else {
                    return saveChildElements(securityInfo, dictionary, true);
                }
            } else {
                return Flux.error(new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "The importType of " + importType.name() + " is currently not supported")
                );
            }
        }).then();
    }

    /**
     * Will check that the specified Dictionary is valid.
     * <ul>
     *     <li>The meaning cannot be numeric</li>
     * </ul>
     *
     * @return A {@link Mono} that either emits an error (if invalid) or emits the same Dictionary (if valid).
     */
    private Mono<Dictionary> isValid(Dictionary dictionary) {
        if (StringUtils.isNumeric(dictionary.meaning())) {
            return Mono.error(new BadRequestResourceException("The meaning cannot be numeric"));
        } else {
            return Mono.just(dictionary);
        }
    }

    /**
     * Will check to ensure the specified Dictionary won't cause a duplicate record.
     *
     * @return A {@link Mono} that either emits an error (if it would cause a duplicate record),
     * emits the existing Dictionary (if found), or emits the specified Dictionary (if not found).
     */
    private Mono<Dictionary> checkForDuplicates(SecurityInfo securityInfo, Dictionary dictionary) {
        return dictionaryRepository.findByMeaning(securityInfo, dictionary.meaning())
            .flatMap(found -> {
                if (dictionary.dictionaryId() == null || !dictionary.dictionaryId().equals(found.dictionaryId())) {
                    return Mono.error(new ConflictResourceException("A Dictionary already exists with the meaning of [" + dictionary.meaning() + "]"));
                } else {
                    return Mono.just(found);
                }
            })
            .defaultIfEmpty(dictionary);
    }

    private Mono<Dictionary> checkExists(SecurityInfo securityInfo, long dictionaryId) {
        return findById(securityInfo, dictionaryId)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Dictionary [" + dictionaryId + "] was not found")));
    }

    private Mono<Dictionary> checkExists(SecurityInfo securityInfo, String meaning) {
        return findByMeaning(securityInfo, meaning)
            .switchIfEmpty(Mono.error(new NotFoundResourceException("Dictionary [" + meaning + "] was not found")));
    }

    private Mono<Dictionary> retrieveChildElements(@NonNull Dictionary dictionary, List<String> with) {
        return Mono.empty();
    }

    private Mono<Dictionary> saveChildElements(SecurityInfo securityInfo, Dictionary dictionary, boolean addOnly) {
        if (CollectionUtils.isEmpty(dictionary.values())) {
            return Mono.just(dictionary);
        }

        return Flux.fromIterable(dictionary.values())
            .flatMap(value -> {
                if (Objects.isNull(value.dictionaryValueId())) {
                    return dictionaryValueRepository.insert(securityInfo, value, dictionary.dictionaryId());
                } else {
                    if (addOnly) {
                        return Mono.just(value);
                    } else {
                        return dictionaryValueRepository.update(securityInfo, value);
                    }
                }
            })
            .collectList()
            .map(values -> new Dictionary.Builder()
                .from(dictionary)
                .values(values)
                .build()
            );
    }
}
