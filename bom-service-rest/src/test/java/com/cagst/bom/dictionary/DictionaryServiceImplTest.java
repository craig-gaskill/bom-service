package com.cagst.bom.dictionary;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.cagst.bom.dictionary.value.DictionaryValueRepository;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.webflux.exception.BadRequestResourceException;
import com.cagst.bom.spring.webflux.exception.ConflictResourceException;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Test class for the {@link DictionaryServiceImpl} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("DictionaryServiceImpl")
public class DictionaryServiceImplTest {
    private final DictionaryRepository dictionaryRepository = mock(DictionaryRepository.class);
    private final DictionaryValueRepository dictionaryValueRepository = mock(DictionaryValueRepository.class);

    private final DictionaryServiceImpl service = new DictionaryServiceImpl(dictionaryRepository, dictionaryValueRepository);

    private final SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    private final SearchCriteria searchCriteria = new SearchCriteria.Builder()
        .build();

    @DisplayName("when find Dictionary")
    @Nested
    class WhenFindDictionary {
        @DisplayName("by ID")
        @Nested
        class ById {
            @DisplayName("should return an empty mono when not found.")
            @Test
            void testNotFound() {
                when(dictionaryRepository.findById(any(SecurityInfo.class), anyLong())).thenReturn(Mono.empty());

                StepVerifier.create(service.findById(securityInfo, 999L))
                    .verifyComplete();

                verify(dictionaryRepository, times(1)).findById(any(SecurityInfo.class), anyLong());
                verify(dictionaryRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString());
                verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }

            @DisplayName("should return an emitting mono when found.")
            @Test
            void testFound() {
                var dictionary = new Dictionary.Builder()
                    .dictionaryId(1L)
                    .display("Gender")
                    .meaning("GENDER")
                    .build();

                when(dictionaryRepository.findById(any(SecurityInfo.class), anyLong()))
                    .thenReturn(Mono.just(dictionary));

                StepVerifier.create(service.findById(securityInfo, 1L))
                    .assertNext(result -> assertAll("Ensure the Dictionary",
                        () -> assertEquals(dictionary, result, "is the correct Dictionary"))
                    )
                    .verifyComplete();

                verify(dictionaryRepository, times(1)).findById(any(SecurityInfo.class), anyLong());
                verify(dictionaryRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString());
                verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }
        }

        @DisplayName("by meaning")
        @Nested
        class ByMeaning {
            @DisplayName("should return an empty mono when not found.")
            @Test
            void testNotFound() {
                when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString())).thenReturn(Mono.empty());

                StepVerifier.create(service.findByMeaning(securityInfo, "INVALID"))
                    .verifyComplete();

                verify(dictionaryRepository, times(0)).findById(any(SecurityInfo.class), anyLong());
                verify(dictionaryRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString());
                verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }

            @DisplayName("should return an emitting mono when found.")
            @Test
            void testFound() {
                var dictionary = new Dictionary.Builder()
                    .dictionaryId(1L)
                    .display("Gender")
                    .meaning("GENDER")
                    .build();

                when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString()))
                    .thenReturn(Mono.just(dictionary));

                StepVerifier.create(service.findByMeaning(securityInfo, "GENDER"))
                    .assertNext(result -> assertAll("Ensure the Dictionary",
                        () -> assertEquals(dictionary, result, "is the correct Dictionary"))
                    )
                    .verifyComplete();

                verify(dictionaryRepository, times(0)).findById(any(SecurityInfo.class), anyLong());
                verify(dictionaryRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString());
                verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }
        }
    }

    @DisplayName("when finding Dictionaries")
    @Nested
    class WhenFindDictionaries {
        @DisplayName("should return an empty flux when no Dictionaries are found.")
        @Test
        void testNoneFound() {
            when(dictionaryRepository.findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class)))
                .thenReturn(Flux.empty());

            StepVerifier.create(service.findByCriteria(securityInfo, searchCriteria))
                .verifyComplete();
        }

        @DisplayName("should return an emitting flux of Dictionaries when found.")
        @Test
        void testFound() {
            var addressType = new Dictionary.Builder()
                .dictionaryId(1L)
                .display("Address Type")
                .meaning("ADDRESS_TYPE")
                .build();

            var phoneType = new Dictionary.Builder()
                .dictionaryId(2L)
                .display("Phone Type")
                .meaning("PHONE_TYPE")
                .build();

            var emailType = new Dictionary.Builder()
                .dictionaryId(3L)
                .display("Email Type")
                .meaning("EMAIL_TYPE")
                .build();

            when(dictionaryRepository.findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class)))
                .thenReturn(Flux.fromIterable(List.of(addressType, phoneType, emailType)));

            StepVerifier.create(service.findByCriteria(securityInfo, searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary",
                    () -> assertEquals(addressType, result, "is the correct Dictionary")
                ))
                .expectNextCount(2L)
                .verifyComplete();

            verify(dictionaryRepository, times(0)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(1)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
        }
    }

    @DisplayName("when creating a Dictionary")
    @Nested
    class WhenCreateDictionary {
        @DisplayName("should return a mono that emits an error when the new Dictionary would create a duplicate")
        @Test
        void testDuplicate() {
            var existingDictionary = new Dictionary.Builder()
                .dictionaryId(1L)
                .display("Gender")
                .meaning("GENDER")
                .build();

            var newDictionary = new Dictionary.Builder()
                .display("Sex")
                .meaning("GENDER")
                .build();

            when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.just(existingDictionary));

            StepVerifier.create(service.insert(securityInfo, newDictionary, "CORE"))
                .expectError(ConflictResourceException.class)
                .verify();

            verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
        }

        @DisplayName("should return a mono that emits an error when the Dictionary is invalid")
        @Test
        void testInvalid() {
            var newDictionary = new Dictionary.Builder()
                .display("Level")
                .meaning("100")
                .build();

            StepVerifier.create(service.insert(securityInfo, newDictionary, "CORE"))
                .expectError(BadRequestResourceException.class)
                .verify();

            verify(dictionaryRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(0)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
        }

        @DisplayName("should return a mono that emits the Dictionary after it has been inserted")
        @Test
        void testCreate() {
            var newDictionary = new Dictionary.Builder()
                .display("Level")
                .meaning("LEVEL")
                .build();

            var inserted = new Dictionary.Builder()
                .from(newDictionary)
                .dictionaryId(101L)
                .build();

            when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.empty());
            when((dictionaryRepository.insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class))))
                .thenReturn(Mono.just(inserted));

            StepVerifier.create(service.insert(securityInfo, newDictionary, "CORE"))
                .assertNext(result -> assertAll("Ensure the Dictionary",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals(inserted, result, "is the correct Dictionary")
                ))
                .verifyComplete();

            verify(dictionaryRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(0)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(dictionaryRepository, times(1)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
        }
    }

    @DisplayName("when updating a Dictionary")
    @Nested
    class WhenUpdateDictionary {
        @DisplayName("should return a mono that emits an error when the existing Dictionary could not be found")
        @Test
        void testNotFound() {
            var existing = new Dictionary.Builder()
                .dictionaryId(1L)
                .display("Gender")
                .meaning("GENDER")
                .build();

            when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString())).thenReturn(Mono.empty());
            when(dictionaryRepository.findById(any(SecurityInfo.class), anyLong())).thenReturn(Mono.empty());

            StepVerifier.create(service.update(securityInfo, existing))
                .expectError(NotFoundResourceException.class)
                .verify();

            verify(dictionaryRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(1)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
        }

        @DisplayName("should return a mono that emits an error when the existing Dictionary would create a duplicate")
        @Test
        void testDuplicate() {
            var existing = new Dictionary.Builder()
                .dictionaryId(1L)
                .display("Gender")
                .meaning("GENDER")
                .build();

            var duplicate = new Dictionary.Builder()
                .dictionaryId(2L)
                .display("Sex")
                .meaning("GENDER")
                .build();

            var updated = new Dictionary.Builder()
                .from(existing)
                .description("The actual gender of the individual")
                .build();

            when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString())).thenReturn(Mono.just(duplicate));

            StepVerifier.create(service.update(securityInfo, updated))
                .expectError(ConflictResourceException.class)
                .verify();

            verify(dictionaryRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(0)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
        }

        @DisplayName("should return a mono that emits an error when the Dictionary is invalid")
        @Test
        void testInvalid() {
            var existing = new Dictionary.Builder()
                .dictionaryId(1L)
                .display("Gender")
                .meaning("GENDER")
                .build();

            var updated = new Dictionary.Builder()
                .from(existing)
                .meaning("12345")
                .description("The actual gender of the individual")
                .build();

            StepVerifier.create(service.update(securityInfo, updated))
                .expectError(BadRequestResourceException.class)
                .verify();

            verify(dictionaryRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(0)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
        }

        @DisplayName("should return a mono that emits the Dictionary after is has been updated")
        @Test
        void testUpdate_DuplicateCheck_Same() {
            var existing = new Dictionary.Builder()
                .dictionaryId(1L)
                .display("Gender")
                .meaning("GENDER")
                .build();

            var updated = new Dictionary.Builder()
                .from(existing)
                .description("The actual gender of the individual")
                .build();

            when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.just((existing)));
            when(dictionaryRepository.update(any(SecurityInfo.class), any(Dictionary.class)))
                .thenReturn(Mono.just(new Dictionary.Builder().from(updated).updatedCount(updated.updatedCount() + 1).build()));

            StepVerifier.create(service.update(securityInfo, updated))
                .assertNext(result -> assertAll("Ensure the Dictionary",
                    () -> assertEquals(updated, result, "is the same Dictionary"),
                    () -> assertEquals(updated.updatedCount() + 1, result.updatedCount(), "has been updated")
                ))
                .verifyComplete();

            verify(dictionaryRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(0)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(1)).update(any(SecurityInfo.class), any(Dictionary.class));
        }

        @DisplayName("should return a mono that emits the Dictionary after it has been updated")
        @Test
        void testUpdate_DuplicateCheck_Empty() {
            var existing = new Dictionary.Builder()
                .dictionaryId(1L)
                .display("Gender")
                .meaning("GENDER")
                .build();

            var updated = new Dictionary.Builder()
                .from(existing)
                .description("The actual gender of the individual")
                .build();

            when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.empty());
            when(dictionaryRepository.findById(any(SecurityInfo.class), anyLong()))
                .thenReturn(Mono.just(existing));
            when(dictionaryRepository.update(any(SecurityInfo.class), any(Dictionary.class)))
                .thenReturn(Mono.just(new Dictionary.Builder().from(updated).updatedCount(updated.updatedCount() + 1).build()));

            StepVerifier.create(service.update(securityInfo, updated))
                .assertNext(result -> assertAll("Ensure the Dictionary",
                    () -> assertEquals(updated, result, "is the same Dictionary"),
                    () -> assertEquals(updated.updatedCount() + 1, result.updatedCount(), "has been updated")
                ))
                .verifyComplete();

            verify(dictionaryRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(1)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(1)).update(any(SecurityInfo.class), any(Dictionary.class));
        }

        @DisplayName("should return a mono that emits the Dictionary without updating it due to no change")
        @Test
        void testUpdate_NoChange() {
            var existing = new Dictionary.Builder()
                .dictionaryId(1L)
                .display("Gender")
                .meaning("GENDER")
                .build();

            when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.empty());
            when(dictionaryRepository.findById(any(SecurityInfo.class), anyLong()))
                .thenReturn(Mono.just(existing));

            StepVerifier.create(service.update(securityInfo, existing))
                .assertNext(result -> assertAll("Ensure the Dictionary",
                    () -> assertEquals(existing, result, "is the same Dictionary"),
                    () -> assertEquals(existing.updatedCount(), result.updatedCount(), "has the same update count (wasn't updated)")
                ))
                .verifyComplete();

            verify(dictionaryRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString());
            verify(dictionaryRepository, times(1)).findById(any(SecurityInfo.class), anyLong());
            verify(dictionaryRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
            verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
        }
    }

    @DisplayName("when deleting a Dictionary")
    @Nested
    class WhenDeleteDictionary {
        @DisplayName("by ID")
        @Nested
        class ById {
            @DisplayName("should return a mono that emits an error when the existing Dictionary could not be found")
            @Test
            void testNotFound() {
                when(dictionaryRepository.findById(any(SecurityInfo.class), anyLong())).thenReturn(Mono.empty());

                StepVerifier.create(service.deleteById(securityInfo, 1))
                    .expectError(NotFoundResourceException.class)
                    .verify();

                verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
                verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
            }

            @DisplayName("should return a mono that emits when the Dictionary was deleted")
            @Test
            void testDelete() {
                var existing = new Dictionary.Builder()
                    .dictionaryId(1L)
                    .display("Gender")
                    .meaning("GENDER")
                    .build();

                var deleted = new Dictionary.Builder()
                    .from(existing)
                    .active(false)
                    .build();

                when(dictionaryRepository.findById(any(SecurityInfo.class), anyLong())).thenReturn(Mono.just(existing));
                when(dictionaryRepository.update(any(SecurityInfo.class), any(Dictionary.class))).thenReturn(Mono.just(deleted));

                StepVerifier.create(service.deleteById(securityInfo, 1))
                    .verifyComplete();

                verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
                verify(dictionaryRepository, times(1)).update(any(SecurityInfo.class), any(Dictionary.class));
            }
        }

        @DisplayName("by meaning")
        @Nested
        class ByMeaning {
            @DisplayName("should return a mono that emits an error when the existing Dictionary could not be found")
            @Test
            void testNotFound() {
                when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString())).thenReturn(Mono.empty());

                StepVerifier.create(service.deleteByMeaning(securityInfo, "UNKNOWN"))
                    .expectError(NotFoundResourceException.class)
                    .verify();

                verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
                verify(dictionaryRepository, times(0)).update(any(SecurityInfo.class), any(Dictionary.class));
            }

            @DisplayName("should return a mono that emits when the Dictionary was deleted")
            @Test
            void testDelete() {
                var existing = new Dictionary.Builder()
                    .dictionaryId(1L)
                    .display("Gender")
                    .meaning("GENDER")
                    .build();

                var deleted = new Dictionary.Builder()
                    .from(existing)
                    .active(false)
                    .build();

                when(dictionaryRepository.findByMeaning(any(SecurityInfo.class), anyString())).thenReturn(Mono.just(existing));
                when(dictionaryRepository.update(any(SecurityInfo.class), any(Dictionary.class))).thenReturn(Mono.just(deleted));

                StepVerifier.create(service.deleteByMeaning(securityInfo, "GENDER"))
                    .verifyComplete();

                verify(dictionaryRepository, times(0)).insert(any(SecurityInfo.class), any(Dictionary.class), any(String.class));
                verify(dictionaryRepository, times(1)).update(any(SecurityInfo.class), any(Dictionary.class));
            }
        }
    }
}
