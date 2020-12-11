package com.cagst.bom.dictionary.attribute;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

/**
 * Test class for the {@link DictionaryAttributeRepositoryR2dbc} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("DictionaryAttributeRepositoryR2dbc")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DictionaryAttributeRepositoryR2dbcTest {
    private final SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    @Autowired
    private DictionaryAttributeRepositoryR2dbc repo;

    @DisplayName("when findById")
    @Nested
    class WhenFindById {
        @DisplayName("should return a non-emitting mono when not found due to an incorrect ID")
        @Test
        void testNotFound_CorrectDictionary() {
            StepVerifier.create(repo.findById(securityInfo, 3L, 999L))
                .verifyComplete();
        }

        @DisplayName("should return a non-emitting mono when not found due to an incorrect Dictionary")
        @Test
        void testNotFound_WrongDictionary() {
            StepVerifier.create(repo.findById(securityInfo, 5L, 1L))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findById(securityInfo, 3L, 1L))
                .assertNext(result -> assertAll("Ensure the DictionaryAttribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Mobile", result.display(), "has the correct display")
                ))
                .verifyComplete();

            StepVerifier.create(repo.findById(securityInfo, "PHONE_TYPE", 1L))
                .assertNext(result -> assertAll("Ensure the DictionaryAttribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Mobile", result.display(), "has the correct display")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when findByMeaning")
    @Nested
    class WhenFindByMeaning {
        @DisplayName("should return a non-emitting mono when not found due to incorrect Meaning")
        @Test
        void testNotFound_CorrectDictionary() {
            StepVerifier.create(repo.findByMeaning(securityInfo, 3L, "CELL"))
                .verifyComplete();
        }

        @DisplayName("should return a non-emitting mono when not found due to a incorrect Dictionary")
        @Test
        void testNotFound_WrongDictionary() {
            StepVerifier.create(repo.findByMeaning(securityInfo, 5L, "MOBILE"))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findByMeaning(securityInfo, 3L, "MOBILE"))
                .assertNext(result -> assertAll("Ensure the DictionaryAttribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Mobile", result.display(), "has the correct display")
                ))
                .verifyComplete();

            StepVerifier.create(repo.findByMeaning(securityInfo, "PHONE_TYPE", "MOBILE"))
                .assertNext(result -> assertAll("Ensure the DictionaryAttribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Mobile", result.display(), "has the correct display")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when find")
    @Nested
    class WhenFind {
        @DisplayName("should return a non-emitting flux when none are found")
        @Test
        void testNoneFound() {
            StepVerifier.create(repo.findByCriteria(securityInfo, 1L, null))
                .verifyComplete();
        }

        @DisplayName("should return all Dictionary Attributes for the given Dictionary and Search Criteria")
        @Test
        void testFindAll() {
            StepVerifier.create(repo.findByCriteria(securityInfo, 5L, null))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("High", result.display(), "is in the correct order")
                ))
                .expectNextCount(4L)
                .verifyComplete();

            StepVerifier.create(repo.findByCriteria(securityInfo, "SENSITIVITY_LEVEL", null))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("High", result.display(), "is in the correct order")
                ))
                .expectNextCount(4L)
                .verifyComplete();
        }

        @DisplayName("should return a result set that starts with the 2nd possible value")
        @Test
        void testFindStart() {
            var searchCriteria = new SearchCriteria.Builder()
                .start(1)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, 5L, searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Low", result.display(), "is in the correct order")
                ))
                .expectNextCount(3L)
                .verifyComplete();
        }

        @DisplayName("should return only the first 2 results")
        @Test
        void testFindLimit() {
            var searchCriteria = new SearchCriteria.Builder()
                .limit(2)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, 5L, searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("High", result.display(), "is in the correct order")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }

        @DisplayName("by searchText")
        @Nested
        class BySearchText {
            @DisplayName("should return the Dictionary Attribute that equals the specified search text")
            @Test
            void testExact() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("low")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, 5L, searchCriteria))
                    .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                        () -> assertNotNull(result, "is valid"),
                        () -> assertEquals("Low", result.display(), "is in the correct order")
                    ))
                    .verifyComplete();
            }

            @DisplayName("should return the Dictionary Attributes that start with the specified search text")
            @Test
            void testStartsWith() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("low*")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, 5L, searchCriteria))
                    .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                        () -> assertNotNull(result, "is valid"),
                        () -> assertEquals("Low", result.display(), "is in the correct order")
                    ))
                    .expectNextCount(1L)
                    .verifyComplete();
            }

            @DisplayName("should return the Dictionary Attributes that end with the specified search text")
            @Test
            void testEndsWith() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("*medium")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, 5L, searchCriteria))
                    .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                        () -> assertNotNull(result, "is valid"),
                        () -> assertEquals("Low/Medium", result.display(), "is in the correct order")
                    ))
                    .expectNextCount(1L)
                    .verifyComplete();
            }

            @DisplayName("should return the Dictionary Attributes that contain the specified search text")
            @Test
            void testContains() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("*med*")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, 5L, searchCriteria))
                    .assertNext(result -> assertAll("Ensure the Dictionary Attribute",
                        () -> assertNotNull(result, "is valid"),
                        () -> assertEquals("Low/Medium", result.display(), "is in the correct order")
                    ))
                    .expectNextCount(2L)
                    .verifyComplete();
            }
        }
    }

    @DisplayName("when insert")
    @Nested
    class WhenInsert {
    }

    @DisplayName("when update")
    @Nested
    class WhenUpdate {
        @DisplayName("should emit the updated Dictionary Attribute when successful")
        @Test
        void testUpdate() {
            var existing = repo.findByMeaning(securityInfo, "PHONE_TYPE", "MOBILE")
                .block();

            assertAll("Ensure the Dictionary Attribute",
                () -> assertNotNull(existing, "is valid"),
                () -> assertEquals("Mobile", existing.display(), "is the correct Dictionary Attribute"),
                () -> assertNull(existing.description(), "has no description")
            );

            var changed = new DictionaryAttribute.Builder()
                .from(existing)
                .description("Allows you to specify if this Phone is a mobile phone")
                .build();

            StepVerifier.create(repo.update(securityInfo, changed))
                .assertNext(result -> assertAll("Ensure the attribute",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals(changed, result, "is the same attribute"),
                    () -> assertNotNull(result.description(), "has a description")
                ))
                .verifyComplete();

            var reload = repo.findByMeaning(securityInfo, "PHONE_TYPE", "MOBILE")
                .block();

            assertAll("Ensure the Dictionary Attribute",
                () -> assertNotNull(reload, "is valid"),
                () -> assertEquals("Mobile", reload.display(), "is the correct Dictionary Attribute"),
                () -> assertNotNull(reload.description(), "has a description")
            );
        }
    }
}
