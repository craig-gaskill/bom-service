package com.cagst.bom.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

/**
 * Test class for the {@link UserRepositoryR2dbc} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("UserRepositoryR2dbc")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryR2dbcTest {
    private final SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    @Autowired
    private UserRepositoryR2dbc repo;

    @DisplayName("when findById")
    @Nested
    class WhenFindById {
        @DisplayName("should return an empty mono when not found")
        @Test
        void testNotFound() {
            StepVerifier.create(repo.findById(securityInfo, 999L))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findById(securityInfo, 1L))
                .assertNext(user -> assertAll("Ensure the User",
                    () -> assertNotNull(user, "is valid"),
                    () -> assertEquals("adamwest@test.com", user.username(), "is the correct User")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when findByIds")
    @Nested
    class WhenFindByIds {
        @DisplayName("should return a non-emitting flux when none are found")
        @Test
        void testNoneFound() {
            StepVerifier.create(repo.findByIds(securityInfo, Lists.newArrayList(998L, 999L)))
                .verifyComplete();
        }

        @DisplayName("should return an emitting flux when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findByIds(securityInfo, Lists.newArrayList(1L, 2L)))
                .assertNext(result -> assertAll("Ensure the User",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("adamwest@test.com", result.username(), "is the correct User")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }
    }

    @DisplayName("when findByUsername")
    @Nested
    class WhenFindByUsername {
        @DisplayName("should return an empty mono when not found")
        @Test
        void testNotFound() {
            StepVerifier.create(repo.findByUsername("invalid@test.com"))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findByUsername("adamwest@test.com"))
                .assertNext(user -> assertAll("Ensure the User",
                    () -> assertNotNull(user, "is valid"),
                    () -> assertEquals("adamwest@test.com", user.username(), "is the correct User")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when find")
    @Nested
    class WhenFind {
        @DisplayName("should return all Users for Tenant")
        @Test
        void testFind() {
            StepVerifier.create(repo.findByCriteria(securityInfo, null))
                .assertNext(person -> assertAll("Ensure the User",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals("anseladam@test.com", person.username(), "has the correct username")
                ))
                .expectNextCount(3L)
                .verifyComplete();
        }

        @DisplayName("should return a result set that starts with the 2nd possible value")
        @Test
        void testFindStart() {
            var searchCriteria = new SearchCriteria.Builder()
                .start(1)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                .assertNext(person -> assertAll("Ensure the User",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals("willienelson@test.com", person.username(), "has the correct username")
                ))
                .expectNextCount(2L)
                .verifyComplete();
        }

        @DisplayName("should return only the first 2 results")
        @Test
        void testFindLimit() {
            var searchCriteria = new SearchCriteria.Builder()
                .limit(2)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                .assertNext(person -> assertAll("Ensure the User",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals("anseladam@test.com", person.username(), "has the correct username")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }

        @DisplayName("by searchText")
        @Nested
        class BySearchText {
            @DisplayName("for exact match")
            @Nested
            class ForExact {
                @DisplayName("should return users who's given, family, or user names match exactly")
                @Test
                void testSingleWord() {
                    var searchCriteria1 = new SearchCriteria.Builder()
                        .searchText("adam")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria1))
                        .assertNext(result -> assertAll("Ensure the User",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("anseladam@test.com", result.username(), "has the correct username")
                        ))
                        .expectNextCount(1L)
                        .verifyComplete();

                    var searchCriteria2 = new SearchCriteria.Builder()
                        .searchText("west,")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria2))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("adamwest@test.com", result.username(), "has the correct username")
                        ))
                        .verifyComplete();
                }

//                @DisplayName("should return people who's given and family names match exactly")
//                @Test
//                void testFirstLast() {
//                    var searchCriteria = new PlatformSearchCriteria.Builder()
//                        .searchText("adam west")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
//
//                @DisplayName("should return people who's family and give names match exactly")
//                @Test
//                void testLastFirst() {
//                    var searchCriteria = new PlatformSearchCriteria.Builder()
//                        .searchText("west, adam")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
            }

            @DisplayName("for starts with match")
            @Nested
            class ForStartsWith {
//                @DisplayName("should return people who's given or family names starts with")
//                @Test
//                void testSingleWord() {
//                    var searchCriteria1 = new PlatformSearchCriteria.Builder()
//                        .searchText("wil*")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria1))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Willie", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("Nelson", result.familyName(), "has the correct family name")
//                        ))
//                        .expectNextCount(1L)
//                        .verifyComplete();
//
//                    var searchCriteria2 = new PlatformSearchCriteria.Builder()
//                        .searchText("we*,")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria2))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
//
//                @DisplayName("should return people who's given and family names starts with")
//                @Test
//                void testFirstLast() {
//                    var searchCriteria = new PlatformSearchCriteria.Builder()
//                        .searchText("ad* we*")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
//
//                @DisplayName("should return people who's family and give names start with")
//                @Test
//                void testLastFirst() {
//                    var searchCriteria = new PlatformSearchCriteria.Builder()
//                        .searchText("we*, ad*")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
            }

            @DisplayName("for ends with match")
            @Nested
            class ForEndsWith {
//                @DisplayName("should return people who's given or family names ends with")
//                @Test
//                void testSingleWord() {
//                    var searchCriteria1 = new PlatformSearchCriteria.Builder()
//                        .searchText("*am")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria1))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Ansel", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("Adam", result.familyName(), "has the correct family name")
//                        ))
//                        .expectNextCount(1L)
//                        .verifyComplete();
//
//                    var searchCriteria2 = new PlatformSearchCriteria.Builder()
//                        .searchText("*st,")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria2))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
//
//                @DisplayName("should return people who's given and family names ends with")
//                @Test
//                void testFirstLast() {
//                    var searchCriteria = new PlatformSearchCriteria.Builder()
//                        .searchText("*am *st")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
//
//                @DisplayName("should return people who's family and give names ends with")
//                @Test
//                void testLastFirst() {
//                    var searchCriteria = new PlatformSearchCriteria.Builder()
//                        .searchText("*st, *am")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
            }

            @DisplayName("for contains match")
            @Nested
            class ForContains {
//                @DisplayName("should return people who's given or family names contain")
//                @Test
//                void testSingleWord() {
//                    var searchCriteria1 = new PlatformSearchCriteria.Builder()
//                        .searchText("*da*")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria1))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Ansel", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("Adam", result.familyName(), "has the correct family name")
//                        ))
//                        .expectNextCount(1L)
//                        .verifyComplete();
//
//                    var searchCriteria2 = new PlatformSearchCriteria.Builder()
//                        .searchText("*es*,")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria2))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
//
//                @DisplayName("should return people who's given and family names contain")
//                @Test
//                void testFirstLast() {
//                    var searchCriteria = new PlatformSearchCriteria.Builder()
//                        .searchText("*da* *es*")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
//
//                @DisplayName("should return people who's family and give names contain")
//                @Test
//                void testLastFirst() {
//                    var searchCriteria = new PlatformSearchCriteria.Builder()
//                        .searchText("*es*, *da*")
//                        .build();
//
//                    StepVerifier.create(repo.find(securityInfo, searchCriteria))
//                        .assertNext(result -> assertAll("Ensure the Person",
//                            () -> assertNotNull(result, "is valid"),
//                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
//                            () -> assertEquals("West", result.familyName(), "has the correct family name")
//                        ))
//                        .verifyComplete();
//                }
            }
        }
    }
}
