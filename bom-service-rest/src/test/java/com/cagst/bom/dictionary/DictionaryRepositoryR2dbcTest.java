package com.cagst.bom.dictionary;

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
 * Test class for the {@link DictionaryRepositoryR2dbc} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("DictionaryRepositoryR2dbc")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DictionaryRepositoryR2dbcTest {
    private SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    @Autowired
    private DictionaryRepositoryR2dbc repo;

    @DisplayName("when findById")
    @Nested
    class WhenFindById {
        @DisplayName("should return a non-emitting mono when not found due to incorrect ID")
        @Test
        void testNotFound_CorrectTenant() {
            StepVerifier.create(repo.findById(securityInfo, 999L))
                .verifyComplete();
        }

        @DisplayName("should return a non-emitting mono when not found due to an incorrect Tenant")
        @Test
        void testNotFound_WrongTenant() {
            securityInfo = new SecurityInfo.Builder()
                .from(securityInfo)
                .tenantId(2)
                .build();

            StepVerifier.create(repo.findById(securityInfo, 1L))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findById(securityInfo, 1L))
                .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                    () -> assertNotNull(dictionary, "is valid"),
                    () -> assertEquals("Gender", dictionary.display(), "has the correct display")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when findByMeaning")
    @Nested
    class WhenFindByMeaning {
        @DisplayName("should return a non-emitting mono when not found due to incorrect Meaning")
        @Test
        void testNotFound_CorrectTenant() {
            StepVerifier.create(repo.findByMeaning(securityInfo, "INVALID"))
                .verifyComplete();
        }

        @DisplayName("should return a non-emitting mono when not found due to incorrect Tenant")
        @Test
        void testNotFound_IncorrectTenant() {
            securityInfo = new SecurityInfo.Builder()
                .from(securityInfo)
                .tenantId(2)
                .build();

            StepVerifier.create(repo.findByMeaning(securityInfo, "EMAIL_TYPE"))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findByMeaning(securityInfo, "EMAIL_TYPE"))
                .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                    () -> assertNotNull(dictionary, "is valid"),
                    () -> assertEquals("Email Type", dictionary.display(), "has the correct display")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when find")
    @Nested
    class WhenFind {
        @DisplayName("should return all Dictionaries for Tenant")
        @Test
        void testFind() {
            StepVerifier.create(repo.findByCriteria(securityInfo, null))
                .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                    () -> assertNotNull(dictionary, "is valid"),
                    () -> assertEquals("Address Type", dictionary.display(), "is in the correct order")
                ))
                .expectNextCount(4L)
                .verifyComplete();

            securityInfo = new SecurityInfo.Builder()
                .from(securityInfo)
                .tenantId(2)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, null))
                .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                    () -> assertNotNull(dictionary, "is valid"),
                    () -> assertEquals("Address Type", dictionary.display(), "is in the correct order")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }

        @DisplayName("should return a result set that starts with the 2nd possible value")
        @Test
        void testFindStart() {
            var searchCriteria = new SearchCriteria.Builder()
                .start(1)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                    () -> assertNotNull(dictionary, "is valid"),
                    () -> assertEquals("Email Type", dictionary.display(), "is in the correct order")
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

            StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                    () -> assertNotNull(dictionary, "is valid"),
                    () -> assertEquals("Address Type", dictionary.display(), "is in the correct order")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }

        @DisplayName("by searchText")
        @Nested
        class BySearchText {
            @DisplayName("should return the Dictionary that equals the specified search text")
            @Test
            void testExact() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("gender")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                        () -> assertNotNull(dictionary, "is valid"),
                        () -> assertEquals("Gender", dictionary.display(), "is the correct Dictionary")
                    ))
                    .verifyComplete();
            }

            @DisplayName("should return the Dictionaries that start with the specified search text")
            @Test
            void testStartsWith() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("gen*")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                        () -> assertNotNull(dictionary, "is valid"),
                        () -> assertEquals("Gender", dictionary.display(), "is the correct Dictionary")
                    ))
                    .verifyComplete();
            }

            @DisplayName("should return the Dictionaries that end with the specified search text")
            @Test
            void testEndsWith() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("*type")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                        () -> assertNotNull(dictionary, "is valid"),
                        () -> assertEquals("Address Type", dictionary.display(), "is the correct Dictionary")
                    ))
                    .expectNextCount(2L)
                    .verifyComplete();
            }

            @DisplayName("should return the Dictionaries that contain the specified search text")
            @Test
            void textContains() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("*en*")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(dictionary -> assertAll("Ensure the Dictionary",
                        () -> assertNotNull(dictionary, "is valid"),
                        () -> assertEquals("Gender", dictionary.display(), "is the correct Dictionary")
                    ))
                    .expectNextCount(1L)
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
        @DisplayName("should emit the updated Dictionary when successful")
        @Test
        void testUpdate() {
            var existing = repo.findByMeaning(securityInfo, "GENDER")
                .block();

            assertAll("Ensure the Dictionary",
                () -> assertNotNull(existing, "is valid"),
                () -> assertEquals("Gender", existing.display(), "is the correct Dictionary"),
                () -> assertNull(existing.description(), "has no description")
            );

            var changed = new Dictionary.Builder()
                .from(existing)
                .description("Allows you to specify the Gender of the Individual")
                .build();

            StepVerifier.create(repo.update(securityInfo, changed))
                .assertNext(dictionary -> assertAll("Ensure the dictionary",
                    () -> assertNotNull(dictionary, "is valid"),
                    () -> assertEquals(changed, dictionary, "is the same dictionary"),
                    () -> assertNotNull(dictionary.description(), "has a description")
                ))
                .verifyComplete();

            var reload = repo.findByMeaning(securityInfo, "GENDER")
                .block();

            assertAll("Ensure the Dictionary",
                () -> assertNotNull(reload, "is valid"),
                () -> assertEquals("Gender", reload.display(), "is the correct Dictionary"),
                () -> assertNotNull(reload.description(), "has a description")
            );
        }

        @DisplayName("should emit an error when not successful")
        void testUpdate_Failed() {
        }
    }
}
