package com.cagst.bom.person;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.security.jwt.JwtReactiveAuthenticationManager;
import com.cagst.bom.spring.security.jwt.JwtServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

/**
 * Test class for the {@link PersonRepositoryR2dbc} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("PersonRepositoryR2dbc")
@ExtendWith(SpringExtension.class)
@Import({JwtServiceImpl.class, JwtReactiveAuthenticationManager.class})
@SpringBootTest
class PersonRepositoryR2dbcTest {
    private SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    @Autowired
    private PersonRepositoryR2dbc repo;

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
                .assertNext(person -> assertAll("Ensure the Person",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals("Adam", person.givenName(), "has the correct first name"),
                    () -> assertEquals("West", person.familyName(), "has the correct last name")
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
                .assertNext(result -> assertAll("Ensure the Person",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Adam", result.givenName(), "is the correct Person")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }
    }

    @DisplayName("when findByCriteria")
    @Nested
    class WhenFindByCriteria {
        @DisplayName("should return all People for Tenant")
        @Test
        void testFind() {
            StepVerifier.create(repo.findByCriteria(securityInfo, null))
                .assertNext(person -> assertAll("Ensure the Person",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals("Ansel", person.givenName(), "has the correct first name"),
                    () -> assertEquals("Adam", person.familyName(), "has the correct last name")
                ))
                .expectNextCount(3L)
                .verifyComplete();

            securityInfo = new SecurityInfo.Builder()
                .from(securityInfo)
                .tenantId(2)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, null))
                .assertNext(person -> assertAll("Ensure the Person",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals("Fred", person.givenName(), "has the correct first name"),
                    () -> assertEquals("Flintstone", person.familyName(), "has the correct last name")
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

            StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                .assertNext(person -> assertAll("Ensure the Person",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals("Willie", person.givenName(), "has the correct given name"),
                    () -> assertEquals("Nelson", person.familyName(), "has the correct family name")
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
                .assertNext(person -> assertAll("Ensure the Person",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals("Ansel", person.givenName(), "has the correct given name"),
                    () -> assertEquals("Adam", person.familyName(), "has the correct family name")
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
                @DisplayName("should return people who's given or family names match exactly")
                @Test
                void testSingleWord() {
                    var searchCriteria1 = new SearchCriteria.Builder()
                        .searchText("adam")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria1))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Ansel", result.givenName(), "has the correct given name"),
                            () -> assertEquals("Adam", result.familyName(), "has the correct family name")
                        ))
                        .expectNextCount(1L)
                        .verifyComplete();

                    var searchCriteria2 = new SearchCriteria.Builder()
                        .searchText("west,")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria2))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }

                @DisplayName("should return people who's given and family names match exactly")
                @Test
                void testFirstLast() {
                    var searchCriteria = new SearchCriteria.Builder()
                        .searchText("adam west")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }

                @DisplayName("should return people who's family and give names match exactly")
                @Test
                void testLastFirst() {
                    var searchCriteria = new SearchCriteria.Builder()
                        .searchText("west, adam")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }
            }

            @DisplayName("for starts with match")
            @Nested
            class ForStartsWith {
                @DisplayName("should return people who's given or family names starts with")
                @Test
                void testSingleWord() {
                    var searchCriteria1 = new SearchCriteria.Builder()
                        .searchText("wil*")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria1))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Willie", result.givenName(), "has the correct given name"),
                            () -> assertEquals("Nelson", result.familyName(), "has the correct family name")
                        ))
                        .expectNextCount(1L)
                        .verifyComplete();

                    var searchCriteria2 = new SearchCriteria.Builder()
                        .searchText("we*,")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria2))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }

                @DisplayName("should return people who's given and family names starts with")
                @Test
                void testFirstLast() {
                    var searchCriteria = new SearchCriteria.Builder()
                        .searchText("ad* we*")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }

                @DisplayName("should return people who's family and give names start with")
                @Test
                void testLastFirst() {
                    var searchCriteria = new SearchCriteria.Builder()
                        .searchText("we*, ad*")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }
            }

            @DisplayName("for ends with match")
            @Nested
            class ForEndsWith {
                @DisplayName("should return people who's given or family names ends with")
                @Test
                void testSingleWord() {
                    var searchCriteria1 = new SearchCriteria.Builder()
                        .searchText("*am")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria1))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Ansel", result.givenName(), "has the correct given name"),
                            () -> assertEquals("Adam", result.familyName(), "has the correct family name")
                        ))
                        .expectNextCount(1L)
                        .verifyComplete();

                    var searchCriteria2 = new SearchCriteria.Builder()
                        .searchText("*st,")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria2))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }

                @DisplayName("should return people who's given and family names ends with")
                @Test
                void testFirstLast() {
                    var searchCriteria = new SearchCriteria.Builder()
                        .searchText("*am *st")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }

                @DisplayName("should return people who's family and give names ends with")
                @Test
                void testLastFirst() {
                    var searchCriteria = new SearchCriteria.Builder()
                        .searchText("*st, *am")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }
            }

            @DisplayName("for contains match")
            @Nested
            class ForContains {
                @DisplayName("should return people who's given or family names contain")
                @Test
                void testSingleWord() {
                    var searchCriteria1 = new SearchCriteria.Builder()
                        .searchText("*da*")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria1))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Ansel", result.givenName(), "has the correct given name"),
                            () -> assertEquals("Adam", result.familyName(), "has the correct family name")
                        ))
                        .expectNextCount(1L)
                        .verifyComplete();

                    var searchCriteria2 = new SearchCriteria.Builder()
                        .searchText("*es*,")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria2))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }

                @DisplayName("should return people who's given and family names contain")
                @Test
                void testFirstLast() {
                    var searchCriteria = new SearchCriteria.Builder()
                        .searchText("*da* *es*")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }

                @DisplayName("should return people who's family and give names contain")
                @Test
                void testLastFirst() {
                    var searchCriteria = new SearchCriteria.Builder()
                        .searchText("*es*, *da*")
                        .build();

                    StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                        .assertNext(result -> assertAll("Ensure the Person",
                            () -> assertNotNull(result, "is valid"),
                            () -> assertEquals("Adam", result.givenName(), "has the correct given name"),
                            () -> assertEquals("West", result.familyName(), "has the correct family name")
                        ))
                        .verifyComplete();
                }
            }
        }
    }

    @DisplayName("when insert")
    @Nested
    class WhenInsert {
    }

    @DisplayName("when update")
    @Disabled
    @Nested
    class WhenUpdate {
        @DisplayName("should emit the updated Person when successful")
        @Test
        void testUpdate() {
            var existing = repo.findById(securityInfo, 4L)
                .block();

            assertAll("Ensure the Person",
                () -> assertNotNull(existing, "is valid"),
                () -> assertEquals("Ansel", existing.givenName(), "has the correct first name"),
                () -> assertEquals("Adam", existing.familyName(), "has the correct last name"),
                () -> assertNull(existing.commonName(), "has no common name")
            );

            var changed = new Person.Builder()
                .from(existing)
                .familyName("Adams")
                .commonName("Andy")
                .build();

            StepVerifier.create(repo.update(securityInfo, changed))
                .assertNext(person -> assertAll("Ensure the person",
                    () -> assertNotNull(person, "is valid"),
                    () -> assertEquals(changed, person, "is the same person"),
                    () -> assertNotNull(person.commonName(), "has a common name")
                ))
                .verifyComplete();

            var reload = repo.findById(securityInfo, 4L)
                .block();

            assertAll("Ensure the Person",
                () -> assertNotNull(reload, "is valid"),
                () -> assertEquals("Ansel", reload.givenName(), "has the correct first name"),
                () -> assertEquals("Adams", reload.familyName(), "has the correct last name"),
                () -> assertNotNull(reload.commonName(), "has no common name")
            );
        }
    }
}
