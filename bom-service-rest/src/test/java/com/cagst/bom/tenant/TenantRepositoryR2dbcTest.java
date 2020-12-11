package com.cagst.bom.tenant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

/**
 * Test class for the {@link TenantRepositoryR2dbc} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("TenantRepositoryR2dbc")
@ExtendWith(SpringExtension.class)
@SpringBootTest
class TenantRepositoryR2dbcTest {
    private final SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    @Autowired
    private TenantRepositoryR2dbc repo;

    @DisplayName("when findById")
    @Nested
    class WhenFindById {
        @DisplayName("should return an non-emitting mono when not found")
        @Test
        void testNotFound() {
            StepVerifier.create(repo.findById(securityInfo, 999))
                .verifyComplete();
        }

        @DisplayName("should return an emitting mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findById(securityInfo, 1))
                .assertNext(tenant -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(tenant, "is valid"),
                    () -> assertEquals("Sandbox", tenant.name(), "has the correct name")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when findByIds")
    @Nested
    class WhenFindByIds {
        @DisplayName("should return a non-emitting flux when none are found")
        @Test
        void testNone() {
            StepVerifier.create(repo.findByIds(securityInfo, Lists.newArrayList(998, 999)))
                .verifyComplete();
        }

        @DisplayName("should return an emitting flux when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findByIds(securityInfo, Lists.newArrayList(1, 2)))
                .assertNext(result -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Demo", result.name(), "is the correct Tenant")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }
    }

    @DisplayName("when findByMnemonic")
    @Nested
    class WhenFindByMnemonic {
        @DisplayName("should return an empty mono when not found")
        @Test
        void testNotFound() {
            StepVerifier.create(repo.findByMnemonic(securityInfo, "NOT_FOUND"))
                .verifyComplete();
        }

        @DisplayName("should return a populated mono when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findByMnemonic(securityInfo, "CAGST"))
                .assertNext(tenant -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(tenant, "is valid"),
                    () -> assertEquals("CAGST", tenant.name(), "has the correct name")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when findByMnemonics")
    @Nested
    class WhenFindByMnemonics {
        @DisplayName("should return a non-emitting flux when none are found")
        @Test
        void testNone() {
            StepVerifier.create(repo.findByMnemonics(securityInfo, Lists.newArrayList("NOT_FOUND", "INVALID")))
                .verifyComplete();
        }

        @DisplayName("should return an emitting flux when found")
        @Test
        void testFound() {
            StepVerifier.create(repo.findByMnemonics(securityInfo, Lists.newArrayList("SANDBOX", "DEMO")))
                .assertNext(result -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(result, "is valid"),
                    () -> assertEquals("Demo", result.name(), "is the correct Tenant")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }
    }

    @DisplayName("when find")
    @Nested
    class WhenFind {
        @DisplayName("should return all Tenants")
        @Test
        void testFindAll() {
            StepVerifier.create(repo.findByCriteria(securityInfo, null))
                .assertNext(tenant -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(tenant, "is valid"),
                    () -> assertEquals("CAGST", tenant.name(), "is in the correct order")
                ))
                .expectNextCount(4L)
                .verifyComplete();
        }

        @DisplayName("should return a results set that starts with the 2nd possible value")
        @Test
        void testFindStart() {
            var searchCriteria = new SearchCriteria.Builder()
                .start(1)
                .build();

            StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                .assertNext(tenant -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(tenant, "is valid"),
                    () -> assertEquals("Demo", tenant.name(), "is in the correct order")
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
                .assertNext(tenant -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(tenant, "is valid"),
                    () -> assertEquals("CAGST", tenant.name(), "is in the correct order")
                ))
                .expectNextCount(1L)
                .verifyComplete();
        }

        @DisplayName("by searchText")
        @Nested
        class BySearchText {
            @DisplayName("should return the Tenant that equals the specified search text")
            @Test
            void textExact() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("CAGST")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(tenant -> assertAll("Ensure the Tenant",
                        () -> assertNotNull(tenant, "is valid"),
                        () -> assertEquals("CAGST", tenant.name(), "is the correct Tenant")
                    ))
                    .verifyComplete();
            }

            @DisplayName("should return the Tenants that start with the specified search text")
            @Test
            void testStartsWith() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("sa*")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(tenant -> assertAll("Ensure the Tenant",
                        () -> assertNotNull(tenant, "is valid"),
                        () -> assertEquals("Sandbox", tenant.name(), "is in the correct order")
                    ))
                    .expectNextCount(1L)
                    .verifyComplete();
            }

            @DisplayName("should return the Tenants that end with the specified search text")
            @Test
            void testEndsWith() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("*mo")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(tenant -> assertAll("Ensure the Tenant",
                        () -> assertNotNull(tenant, "is valid"),
                        () -> assertEquals("Demo", tenant.name(), "is in the correct order")
                    ))
                    .expectNextCount(1L)
                    .verifyComplete();
            }

            @DisplayName("should return the Tenants that contain the specified search text")
            @Test
            void testContains() {
                var searchCriteria = new SearchCriteria.Builder()
                    .searchText("*a*")
                    .build();

                StepVerifier.create(repo.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(tenant -> assertAll("Ensure the Tenant",
                        () -> assertNotNull(tenant, "is valid"),
                        () -> assertEquals("CAGST", tenant.name(), "is in the correct order")
                    ))
                    .expectNextCount(2L)
                    .verifyComplete();
            }
        }
    }

    @DisplayName("when insert")
    @Nested
    class WhenInsertTenant {
        @DisplayName("should return a newly inserted tenant")
        @Disabled("this can't be tested until migrating to version 1.0 of R2DBC so we can make the code generic")
        @Test
        void testInsert() {
            var newTenant = new Tenant.Builder()
                .name("Test")
                .mnemonic("TEST")
                .build();

            StepVerifier.create(repo.insertTenant(securityInfo, newTenant))
                .assertNext(tenant -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(tenant, "is valid"),
                    () -> assertNotNull(tenant.tenantId(), "has an ID"),
                    () -> assertEquals(newTenant, tenant, "is the same Tenant")
                ))
                .verifyComplete();
        }
    }

    @DisplayName("when update")
    @Nested
    class WhenUpdate {
        @DisplayName("should emit the updated Tenant when successful")
        @Disabled
        @Test
        void testUpdate() {
            var existing = repo.findByMnemonic(securityInfo, "SANDBOX")
                .block();

            assertAll("Ensure the Tenant",
                () -> assertNotNull(existing, "is valid"),
                () -> assertEquals("Sandbox", existing.name(), "is the correct Tenant")
            );

            var changed = new Tenant.Builder()
                .from(existing)
                .name(existing.name() + "- Changed")
                .build();

            StepVerifier.create(repo.updateTenant(securityInfo, changed))
                .assertNext(tenant -> assertAll("Ensure the Tenant",
                    () -> assertNotNull(tenant, "is valid"),
                    () -> assertEquals(changed, tenant, "is the same Tenant")
                ))
                .verifyComplete();

            var reload = repo.findByMnemonic(securityInfo, "SANDBOX")
                .block();

            assertAll("Ensure the Tenant",
                () -> assertNotNull(reload, "is valid"),
                () -> assertEquals("Sandbox - Changed", reload.name(), "is the correct Tenant")
            );
        }

        @DisplayName("should emit an error when not successful")
        void testUpdate_Failed() {
        }
    }
}
