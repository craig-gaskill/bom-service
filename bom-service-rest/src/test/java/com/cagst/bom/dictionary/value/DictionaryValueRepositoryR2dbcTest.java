package com.cagst.bom.dictionary.value;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
 * Test class for the {@link DictionaryValueRepositoryR2dbc} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("DictionaryValueRepositoryR2dbc")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class DictionaryValueRepositoryR2dbcTest {
    private SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    @Autowired
    private DictionaryValueRepositoryR2dbc repo;

    @DisplayName("when findById")
    @Nested
    class WhenFindById {
        @DisplayName("should return a non-emitting mono when not found due to incorrect ID")
        @Test
        void testNotFound_CorrectDictionary() {
            StepVerifier.create(repo.findById(securityInfo, 1L, 999L))
                .verifyComplete();
        }

        @DisplayName("should return a non-emitting mono when not found due to an incorrect Dictionary")
        @Test
        void testNotFound_WrongDictionary() {
            StepVerifier.create(repo.findById(securityInfo, 10L, 1L))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findById(securityInfo, 1L, 1L))
                .assertNext(result -> assertAll("Ensure the DictionaryValue",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Female", result.display())
                ))
                .verifyComplete();

            StepVerifier.create(repo.findById(securityInfo, "GENDER", 1L))
                .assertNext(result -> assertAll("Ensure the DictionaryValue",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Female", result.display())
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
            StepVerifier.create(repo.findByMeaning(securityInfo, 1L, "INVALID"))
                .verifyComplete();
        }

        @DisplayName("should return a non-emitting mono when not found due to an incorrect Dictionary")
        @Test
        void testNotFound_WrongDictionary() {
            StepVerifier.create(repo.findByMeaning(securityInfo, "INVALID", "FEMALE"))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findByMeaning(securityInfo, 1L, "FEMALE"))
                .assertNext(result -> assertAll("Ensure the DictionaryValue",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Female", result.display())
                ))
                .verifyComplete();

            StepVerifier.create(repo.findByMeaning(securityInfo, "GENDER", "FEMALE"))
                .assertNext(result -> assertAll("Ensure the DictionaryValue",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Female", result.display())
                ))
                .verifyComplete();

            StepVerifier.create(repo.findByMeaning(securityInfo, "GENDER", "UNKNOWN"))
                .verifyComplete();

            securityInfo = new SecurityInfo.Builder()
                .from(securityInfo)
                .tenantId(2)
                .build();

            StepVerifier.create(repo.findByMeaning(securityInfo, "GENDER", "UNKNOWN"))
                .assertNext(result -> assertAll("Ensure the DictionaryValue",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Unknown", result.display())
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
            StepVerifier.create(repo.findByCriteria(securityInfo, 3L, null))
                .verifyComplete();
        }

        @DisplayName("should return all Dictionary Values for the given Dictionary and Search Criteria")
        @Test
        void testFindAll() {
            StepVerifier.create(repo.findByCriteria(securityInfo, 2L, null))
                .assertNext(result -> assertAll("Ensure the Dictionary Value",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Business/Work", result.display())
                ))
                .expectNextCount(2L)
                .verifyComplete();

            StepVerifier.create(repo.findByCriteria(securityInfo, "ADDRESS_TYPE", null))
                .assertNext(result -> assertAll("Ensure the Dictionary Value",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Business/Work", result.display())
                ))
                .expectNextCount(2L)
                .verifyComplete();
        }

        @DisplayName("should return a result set that starts with the 2nd possible value")
        @Test
        void testFindStart() {
            var searchCriteria = new SearchCriteria.Builder()
                .start(1)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, 2L, searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary Value",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Home/Residence", result.display())
                ))
                .expectNextCount(1L)
                .verifyComplete();

            StepVerifier.create(repo.findByCriteria(securityInfo, "ADDRESS_TYPE", searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary Value",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Home/Residence", result.display())
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }

        @DisplayName("should return only the first 2 results")
        @Test
        void testFindLimit() {
            var searchCriteria = new SearchCriteria.Builder()
                .limit(2)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, 2L, searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary Value",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Business/Work", result.display())
                ))
                .expectNextCount(1L)
                .verifyComplete();

            StepVerifier.create(repo.findByCriteria(securityInfo, "ADDRESS_TYPE", searchCriteria))
                .assertNext(result -> assertAll("Ensure the Dictionary Value",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Business/Work", result.display())
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }

        @DisplayName("should return all Dictionary Values for the given Dictionary and Search Criteria sorted by the Sort Order")
        @Test
        void testSortOrder() {
            securityInfo = new SecurityInfo.Builder()
                .from(securityInfo)
                .tenantId(2)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, "GENDER", null))
                .assertNext(result -> assertAll("Ensure the Dictionary Value",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Male", result.display())
                ))
                .expectNextCount(2L)
                .verifyComplete();
        }
    }
}
