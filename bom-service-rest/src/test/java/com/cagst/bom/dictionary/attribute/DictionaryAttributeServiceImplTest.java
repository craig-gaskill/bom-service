package com.cagst.bom.dictionary.attribute;

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
 * Test class for the {@link DictionaryAttributeServiceImpl} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("DictionaryAttributeServiceImpl")
class DictionaryAttributeServiceImplTest {
    private final DictionaryAttributeRepository dictionaryAttributeRepository = mock(DictionaryAttributeRepository.class);
    private final DictionaryAttributeServiceImpl service = new DictionaryAttributeServiceImpl(dictionaryAttributeRepository);

    private final SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    private final SearchCriteria searchCriteria = new SearchCriteria.Builder()
        .build();

    @DisplayName("when find Dictionary Attribute")
    @Nested
    class WhenFindAttribute {
        @DisplayName("by ID")
        @Nested
        class ById {
            @DisplayName("should return an empty mono when not found.")
            @Test
            void testNotFound() {
                when(dictionaryAttributeRepository.findById(any(SecurityInfo.class), anyLong(), anyLong()))
                    .thenReturn(Mono.empty());

                StepVerifier.create(service.findById(securityInfo, 1L, 999L))
                    .verifyComplete();

                verify(dictionaryAttributeRepository, times(1)).findById(any(SecurityInfo.class), anyLong(), anyLong());
                verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyString(), anyLong());
                verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
                verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
                verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
                verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));
            }

            @DisplayName("should return an emitting mono when found.")
            @Test
            void testFound() {
                var attribute = new DictionaryAttribute.Builder()
                    .dictionaryAttributeId(1L)
                    .display("Test")
                    .meaning("TEST")
                    .attributeType(DictionaryAttributeType.BOOLEAN)
                    .build();

                when(dictionaryAttributeRepository.findById(any(SecurityInfo.class), anyLong(), anyLong()))
                    .thenReturn(Mono.just(attribute));
                when(dictionaryAttributeRepository.findById(any(SecurityInfo.class), anyString(), anyLong()))
                    .thenReturn(Mono.just(attribute));

                StepVerifier.create(service.findById(securityInfo, 1L, 999L))
                    .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                        () -> assertNotNull(result, "is valid"),
                        () -> assertEquals(attribute, result, "is the correct Dictionary Attribute")))
                    .verifyComplete();

                StepVerifier.create(service.findById(securityInfo, "DICTIONARY", 999L))
                    .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                        () -> assertNotNull(result, "is valid"),
                        () -> assertEquals(attribute, result, "is the correct Dictionary Attribute")))
                    .verifyComplete();

                verify(dictionaryAttributeRepository, times(1)).findById(any(SecurityInfo.class), anyLong(), anyLong());
                verify(dictionaryAttributeRepository, times(1)).findById(any(SecurityInfo.class), anyString(), anyLong());
                verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
                verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
                verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
                verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));
            }
        }

        @DisplayName("by Meaning")
        @Nested
        class ByMeaning {
            @DisplayName("should return an empty mono when not found.")
            @Test
            void testNotFound() {
                when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyLong(), anyString()))
                    .thenReturn(Mono.empty());

                StepVerifier.create(service.findByMeaning(securityInfo, 1L, "NOT_FOUND"))
                    .verifyComplete();

                verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyLong(), anyLong());
                verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyString(), anyLong());
                verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
                verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
                verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
                verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));
            }

            @DisplayName("should return an emitting mono when found.")
            @Test
            void testFound() {
                var attribute = new DictionaryAttribute.Builder()
                    .dictionaryAttributeId(1L)
                    .display("Test")
                    .meaning("TEST")
                    .attributeType(DictionaryAttributeType.BOOLEAN)
                    .build();

                when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyLong(), anyString()))
                    .thenReturn(Mono.just(attribute));
                when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyString(), anyString()))
                    .thenReturn(Mono.just(attribute));

                StepVerifier.create(service.findByMeaning(securityInfo, 1L, "TEST"))
                    .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                        () -> assertNotNull(result, "is valid"),
                        () -> assertEquals(attribute, result, "is the correct Dictionary Attribute")))
                    .verifyComplete();

                StepVerifier.create(service.findByMeaning(securityInfo, "DICTIONARY", "TEST"))
                    .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                        () -> assertNotNull(result, "is valid"),
                        () -> assertEquals(attribute, result, "is the correct Dictionary Attribute")))
                    .verifyComplete();

                verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyLong(), anyLong());
                verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyString(), anyLong());
                verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
                verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
                verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
                verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));
            }
        }
    }

    @DisplayName("when finding Dictionary Attributes")
    @Nested
    class WhenFindAttributes {
        @DisplayName("should return an empty flux when no Dictionary Attributes are found.")
        @Test
        void testNoneFound() {
            when(dictionaryAttributeRepository.findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class)))
                .thenReturn(Flux.empty());

            StepVerifier.create(service.findByCriteria(securityInfo, 1L, searchCriteria))
                .verifyComplete();
        }

        @DisplayName("should return an emitting flux of Dictionary Attributes when found.")
        @Test
        void testFound() {
            var level1 = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(1L)
                .display("Level 1")
                .meaning("LEVEL_1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            var level2 = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(2L)
                .display("Level 2")
                .meaning("LEVEL_2")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            var level3 = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(3L)
                .display("Level 3")
                .meaning("LEVEL_3")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            when(dictionaryAttributeRepository.findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class)))
                .thenReturn(Flux.fromIterable(List.of(level1, level2, level3)));
            when(dictionaryAttributeRepository.findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class)))
                .thenReturn(Flux.fromIterable(List.of(level1, level2, level3)));

            StepVerifier.create(service.findByCriteria(securityInfo, 1L, searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertEquals(level1, result, "is the correction Dictionary Attribute")
                ))
                .expectNextCount(2L)
                .verifyComplete();

            StepVerifier.create(service.findByCriteria(securityInfo, "DICTIONARY", searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertEquals(level1, result, "is the correction Dictionary Attribute")
                ))
                .expectNextCount(2L)
                .verifyComplete();

            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyLong(), anyLong());
            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyString(), anyLong());
            verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
            verify(dictionaryAttributeRepository, times(1)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
            verify(dictionaryAttributeRepository, times(1)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));
        }
    }

    @DisplayName("when creating a Dictionary Attribute")
    @Nested
    class WhenCreateAttribute {
        @DisplayName("should return a mono that emits an error when the new Dictionary Attribute would create a duplicate")
        @Test
        void testDuplicate() {
            var existing = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(1L)
                .display("Level 1")
                .meaning("LEVEL_1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            var newAttribute = new DictionaryAttribute.Builder()
                .display("Pain Level 1")
                .meaning("LEVEL_1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyLong(), anyString()))
                .thenReturn(Mono.just(existing));

            StepVerifier.create(service.insert(securityInfo, 1L, newAttribute))
                .expectError(ConflictResourceException.class)
                .verify();

            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyLong(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyString(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).update(any(SecurityInfo.class), any(DictionaryAttribute.class));
        }

        @DisplayName("should return a mono that emits an error when the Dictionary is invalid")
        @Test
        void testInvalid() {
            var newAttribute = new DictionaryAttribute.Builder()
                .display("Pain Level 1")
                .meaning("01")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            StepVerifier.create(service.insert(securityInfo, 1L, newAttribute))
                .expectError(BadRequestResourceException.class)
                .verify();

            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyLong(), anyLong());
            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyString(), anyLong());
            verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));

            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyLong(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyString(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).update(any(SecurityInfo.class), any(DictionaryAttribute.class));
        }

        @DisplayName("should return a mono that emits the Dictionary Attribute after it has been inserted")
        @Test
        void testCreate() {
            var newAttribute = new DictionaryAttribute.Builder()
                .display("Pain Level 1")
                .meaning("LEVEL1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            var inserted = new DictionaryAttribute.Builder()
                .from(newAttribute)
                .dictionaryAttributeId(101L)
                .build();

            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyLong(), anyString()))
                .thenReturn(Mono.empty());
            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyString(), anyString()))
                .thenReturn(Mono.empty());
            when(dictionaryAttributeRepository.insert(any(SecurityInfo.class), anyLong(), any(DictionaryAttribute.class)))
                .thenReturn(Mono.just(inserted));
            when(dictionaryAttributeRepository.insert(any(SecurityInfo.class), anyString(), any(DictionaryAttribute.class)))
                .thenReturn(Mono.just(inserted));

            StepVerifier.create(service.insert(securityInfo, 1L, newAttribute))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals(inserted, result, "is the correct Dictionary Attribute")
                ))
                .verifyComplete();

            StepVerifier.create(service.insert(securityInfo, "DICTIONARY", newAttribute))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals(inserted, result, "is the correct Dictionary Attribute")
                ))
                .verifyComplete();

            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyLong(), anyLong());
            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyString(), anyLong());
            verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
            verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));

            verify(dictionaryAttributeRepository, times(1)).insert(any(SecurityInfo.class), anyLong(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(1)).insert(any(SecurityInfo.class), anyString(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).update(any(SecurityInfo.class), any(DictionaryAttribute.class));
        }
    }

    @DisplayName("when updating a Dictionary Attribute")
    @Nested
    class WhenUpdateAttribute {
        @DisplayName("should return a mono that emits an error when the existing Dictionary Attribute could not be found")
        @Test
        void testNotFound() {
            var existing = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(1L)
                .display("Level 1")
                .meaning("LEVEL_1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyLong(), anyString())).thenReturn(Mono.empty());
            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyString(), anyString())).thenReturn(Mono.empty());
            when(dictionaryAttributeRepository.findById(any(SecurityInfo.class), anyLong(), anyLong())).thenReturn(Mono.empty());
            when(dictionaryAttributeRepository.findById(any(SecurityInfo.class), anyString(), anyLong())).thenReturn(Mono.empty());

            StepVerifier.create(service.update(securityInfo, 1L, existing))
                .expectError(NotFoundResourceException.class)
                .verify();

            StepVerifier.create(service.update(securityInfo, "DICTIONARY", existing))
                .expectError(NotFoundResourceException.class)
                .verify();

            verify(dictionaryAttributeRepository, times(1)).findById(any(SecurityInfo.class), anyLong(), anyLong());
            verify(dictionaryAttributeRepository, times(1)).findById(any(SecurityInfo.class), anyString(), anyLong());
            verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
            verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));

            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyLong(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyString(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).update(any(SecurityInfo.class), any(DictionaryAttribute.class));
        }

        @DisplayName("should return a mono that emits an error when the existing Dictionary Attribute would create a duplicate")
        @Test
        void testDuplicate() {
            var existing = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(1L)
                .display("Level 1")
                .meaning("LEVEL_1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            var duplicate = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(2L)
                .display("Level 01")
                .meaning("LEVEL_1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            var updated = new DictionaryAttribute.Builder()
                .from(existing)
                .description("The Level of the Value")
                .build();

            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyLong(), anyString()))
                .thenReturn(Mono.just(duplicate));
            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyString(), anyString()))
                .thenReturn(Mono.just(duplicate));

            StepVerifier.create(service.update(securityInfo, 1L, updated))
                .expectError(ConflictResourceException.class)
                .verify();

            StepVerifier.create(service.update(securityInfo, "DICTIONARY", updated))
                .expectError(ConflictResourceException.class)
                .verify();

            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyLong(), anyLong());
            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyString(), anyLong());
            verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
            verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));

            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyLong(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyString(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).update(any(SecurityInfo.class), any(DictionaryAttribute.class));
        }

        @DisplayName("should return a mono that emits an error when the Dictionary Attribute is invalid")
        @Test
        void testInvalid() {
            var existing = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(1L)
                .display("Level 1")
                .meaning("LEVEL_1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            var updated = new DictionaryAttribute.Builder()
                .from(existing)
                .description("The Level of the Value")
                .meaning("01")
                .build();

            StepVerifier.create(service.update(securityInfo, 1L, updated))
                .expectError(BadRequestResourceException.class)
                .verify();

            StepVerifier.create(service.update(securityInfo, "DICTIONARY", updated))
                .expectError(BadRequestResourceException.class)
                .verify();

            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyLong(), anyLong());
            verify(dictionaryAttributeRepository, times(0)).findById(any(SecurityInfo.class), anyString(), anyLong());
            verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));

            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyLong(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyString(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).update(any(SecurityInfo.class), any(DictionaryAttribute.class));
        }

        void testUpdate_DuplicateCheck_Same() {

        }

        void testUpdate_DuplicateCheck_Empty() {

        }

        @DisplayName("should return a mono that emits the Dictionary Attribute without updating it due to no change")
        @Test
        void testUpdate_NoChange() {
            var existing = new DictionaryAttribute.Builder()
                .dictionaryAttributeId(1L)
                .display("Level 1")
                .meaning("LEVEL_1")
                .attributeType(DictionaryAttributeType.INTEGER)
                .build();

            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyLong(), anyString()))
                .thenReturn(Mono.empty());
            when(dictionaryAttributeRepository.findByMeaning(any(SecurityInfo.class), anyString(), anyString()))
                .thenReturn(Mono.empty());

            when(dictionaryAttributeRepository.findById(any(SecurityInfo.class), anyLong(), anyLong()))
                .thenReturn(Mono.just(existing));
            when(dictionaryAttributeRepository.findById(any(SecurityInfo.class), anyString(), anyLong()))
                .thenReturn(Mono.just(existing));

            StepVerifier.create(service.update(securityInfo, 1L, existing))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertEquals(existing, result, "is the same Dictionary Attribute"),
                    () -> assertEquals(existing.updatedCount(), result.updatedCount(), "has the same update count (was't updated)")
                ))
                .verifyComplete();

            StepVerifier.create(service.update(securityInfo, "DICTIONARY", existing))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertEquals(existing, result, "is the same Dictionary Attribute"),
                    () -> assertEquals(existing.updatedCount(), result.updatedCount(), "has the same update count (was't updated)")
                ))
                .verifyComplete();

            verify(dictionaryAttributeRepository, times(1)).findById(any(SecurityInfo.class), anyLong(), anyLong());
            verify(dictionaryAttributeRepository, times(1)).findById(any(SecurityInfo.class), anyString(), anyLong());
            verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyLong(), anyString());
            verify(dictionaryAttributeRepository, times(1)).findByMeaning(any(SecurityInfo.class), anyString(), anyString());
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyLong(), any(SearchCriteria.class));
            verify(dictionaryAttributeRepository, times(0)).findByCriteria(any(SecurityInfo.class), anyString(), any(SearchCriteria.class));

            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyLong(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).insert(any(SecurityInfo.class), anyString(), any(DictionaryAttribute.class));
            verify(dictionaryAttributeRepository, times(0)).update(any(SecurityInfo.class), any(DictionaryAttribute.class));
        }
    }

    @DisplayName("when deleting a Dictionary Attribute")
    @Nested
    class WhenDeleteAttribute {

    }
}
