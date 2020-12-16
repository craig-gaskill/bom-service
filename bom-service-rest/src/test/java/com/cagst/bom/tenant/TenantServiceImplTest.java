package com.cagst.bom.tenant;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import com.cagst.bom.dictionary.DictionaryService;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.webflux.exception.BadRequestResourceException;
import com.cagst.bom.spring.webflux.exception.ConflictResourceException;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import com.cagst.bom.tenant.event.TenantEventPublisher;
import com.cagst.bom.tenant.feature.TenantFeatureRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Test class for the {@link TenantServiceImpl} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("TenantServiceImpl")
class TenantServiceImplTest {
    private final TenantRepository tenantRepository = mock(TenantRepository.class);
    private final TenantFeatureRepository tenantFeatureRepository = mock(TenantFeatureRepository.class);
    private final DictionaryService dictionaryService = mock(DictionaryService.class);
    private final TenantEventPublisher tenantEventPublisher = mock(TenantEventPublisher.class);

    private final TenantServiceImpl service = new TenantServiceImpl(
        tenantRepository,
        tenantFeatureRepository,
        dictionaryService,
        tenantEventPublisher
    );

    private final SecurityInfo securityInfo = new SecurityInfo.Builder()
        .tenantId(1)
        .userId(1L)
        .build();

    private final SearchCriteria searchCriteria = new SearchCriteria.Builder()
        .build();

    @BeforeEach
    public void setUp() {
        when(tenantFeatureRepository.save(any(SecurityInfo.class), any(Flux.class)))
            .thenReturn(Flux.empty());
        when(tenantFeatureRepository.find(any(SecurityInfo.class)))
            .thenReturn(Flux.empty());
    }

    @DisplayName("when find Tenant")
    @Nested
    class WhenFindTenant {
        @DisplayName("by ID")
        @Nested
        class ById {
            @DisplayName("should return an empty mono when not found.")
            @Test
            void testNotFound() {
                when(tenantRepository.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.empty());

                StepVerifier.create(service.findById(securityInfo, 999))
                    .verifyComplete();

                verify(tenantRepository, times(1)).findById(any(SecurityInfo.class), anyInt());
                verify(tenantRepository, times(0)).findByMnemonic(any(SecurityInfo.class), anyString());
                verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }

            @DisplayName("should return an emitting mono when found.")
            @Test
            void testFound() {
                var tenant = new Tenant.Builder()
                    .tenantId(1)
                    .name("Sandbox")
                    .mnemonic("SANDBOX")
                    .build();

                when(tenantRepository.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.just(tenant));

                StepVerifier.create(service.findById(securityInfo, 1))
                    .assertNext(result -> assertAll("Ensure the Tenant",
                        () -> assertEquals(tenant.name(), result.name(), "has the correct name"),
                        () -> assertEquals(tenant.mnemonic(), result.mnemonic(), "has the correct mnemonic")
                    ))
                    .verifyComplete();

                verify(tenantRepository, times(1)).findById(any(SecurityInfo.class), anyInt());
                verify(tenantRepository, times(0)).findByMnemonic(any(SecurityInfo.class), anyString());
                verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }
        }

        @DisplayName("by mnemonic")
        @Nested
        class ByMnemonic {
            @DisplayName("should return an empty mono when not found.")
            @Test
            void testNotFound() {
                when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.empty());

                StepVerifier.create(service.findByMnemonic(securityInfo, "INVALID"))
                    .verifyComplete();

                verify(tenantRepository, times(0)).findById(any(SecurityInfo.class), anyInt());
                verify(tenantRepository, times(1)).findByMnemonic(any(SecurityInfo.class), anyString());
                verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }

            @DisplayName("should return an emitting mono when found.")
            @Test
            void testFound() {
                var tenant = new Tenant.Builder()
                    .tenantId(1)
                    .name("Sandbox")
                    .mnemonic("SANDBOX")
                    .build();

                when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.just(tenant));

                StepVerifier.create(service.findByMnemonic(securityInfo, "SANDBOX"))
                    .assertNext(result -> assertAll("Ensure the Tenant",
                        () -> assertEquals(tenant.name(), result.name(), "has the correct name"),
                        () -> assertEquals(tenant.mnemonic(), result.mnemonic(), "has the correct mnemonic")
                    ))
                    .verifyComplete();

                verify(tenantRepository, times(0)).findById(any(SecurityInfo.class), anyInt());
                verify(tenantRepository, times(1)).findByMnemonic(any(SecurityInfo.class), anyString());
                verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }
        }
    }

    @DisplayName("when finding Tenants")
    @Nested
    class WhenFindTenants {
        @DisplayName("by IDs")
        @Nested
        class ByIds {
            private final List<Integer> ids = Lists.newArrayList(1, 2, 3);

            @DisplayName("should return an empty flux when no Tenants are found")
            @Test
            void testNoneFound() {
                when(tenantRepository.findByIds(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));
                when(tenantRepository.findByMnemonics(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));

                StepVerifier.create(service.findByIds(securityInfo, ids))
                    .verifyComplete();

                verify(tenantRepository, times(1)).findByIds(any(SecurityInfo.class), anyCollection());
                verify(tenantRepository, times(0)).findByMnemonics(any(SecurityInfo.class), anyCollection());
            }

            @DisplayName("should return an emitting flux of Tenants when found")
            @Test
            void testFound() {
                var tenants = Lists.newArrayList(
                    new Tenant.Builder().tenantId(3).name("Build").mnemonic("BUILD").build(),
                    new Tenant.Builder().tenantId(2).name("Demo").mnemonic("DEMO").build(),
                    new Tenant.Builder().tenantId(1).name("Sandbox").mnemonic("SANDBOX").build()
                );

                when(tenantRepository.findByIds(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(tenants));
                when(tenantRepository.findByMnemonics(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));

                StepVerifier.create(service.findByIds(securityInfo, ids))
                    .assertNext(tenant -> assertAll("Ensure the Tenants",
                        () -> assertNotNull(tenant, "is valid"),
                        () -> assertEquals("Build", tenant.name())
                    ))
                    .expectNextCount(2)
                    .verifyComplete();

                verify(tenantRepository, times(1)).findByIds(any(SecurityInfo.class), anyCollection());
                verify(tenantRepository, times(0)).findByMnemonics(any(SecurityInfo.class), anyCollection());
            }
        }

        @DisplayName("by meanings")
        @Nested
        class ByMeanings {
            private final List<String> mnemonics = Lists.newArrayList("SANDBOX", "DEMO", "BUILD");

            @DisplayName("should return an empty flux when no Tenants are found")
            @Test
            void testNoneFound() {
                when(tenantRepository.findByIds(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));
                when(tenantRepository.findByMnemonics(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));

                StepVerifier.create(service.findByMnemonics(securityInfo, mnemonics))
                    .verifyComplete();

                verify(tenantRepository, times(0)).findByIds(any(SecurityInfo.class), anyCollection());
                verify(tenantRepository, times(1)).findByMnemonics(any(SecurityInfo.class), anyCollection());
            }

            @DisplayName("should return an emitting flux of Tenants when found")
            @Test
            void testFound() {
                var tenants = Lists.newArrayList(
                    new Tenant.Builder().tenantId(3).name("Build").mnemonic("BUILD").build(),
                    new Tenant.Builder().tenantId(2).name("Demo").mnemonic("DEMO").build(),
                    new Tenant.Builder().tenantId(1).name("Sandbox").mnemonic("SANDBOX").build()
                );

                when(tenantRepository.findByIds(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));
                when(tenantRepository.findByMnemonics(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(tenants));

                StepVerifier.create(service.findByMnemonics(securityInfo, mnemonics))
                    .assertNext(tenant -> assertAll("Ensure the Tenants",
                        () -> assertNotNull(tenant, "is valid"),
                        () -> assertEquals("Build", tenant.name())
                    ))
                    .expectNextCount(2)
                    .verifyComplete();

                verify(tenantRepository, times(0)).findByIds(any(SecurityInfo.class), anyCollection());
                verify(tenantRepository, times(1)).findByMnemonics(any(SecurityInfo.class), anyCollection());
            }
        }

        @DisplayName("by criteria")
        @Nested
        class ByCriteria {
            @DisplayName("should return an empty flux when no Tenants are found")
            @Test
            void testNoneFound() {
                when(tenantRepository.findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class))).thenReturn(Flux.empty());

                StepVerifier.create(service.findByCriteria(securityInfo, searchCriteria))
                    .verifyComplete();
            }

            @DisplayName("should return an emitting flux of Tenants when found")
            @Test
            void testFound() {
                var tenant1 = new Tenant.Builder()
                    .tenantId(1)
                    .name("Sandbox")
                    .mnemonic("SANDBOX")
                    .build();

                var tenant2 = new Tenant.Builder()
                    .tenantId(2)
                    .name("Demo")
                    .mnemonic("DEMO")
                    .build();

                when(tenantRepository.findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class)))
                    .thenReturn(Flux.fromIterable(List.of(tenant2, tenant1)));

                StepVerifier.create(service.findByCriteria(securityInfo, searchCriteria))
                    .assertNext(result -> assertAll("Ensure the Tenant",
                        () -> assertEquals(tenant2.name(), result.name(), "has the correct name"),
                        () -> assertEquals(tenant2.mnemonic(), result.mnemonic(), "has the correct mnemonic")
                    ))
                    .expectNextCount(1L)
                    .verifyComplete();

                verify(tenantRepository, times(0)).findById(any(SecurityInfo.class), anyInt());
                verify(tenantRepository, times(0)).findByMnemonic(any(SecurityInfo.class), anyString());
                verify(tenantRepository, times(1)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));
            }
        }

        @DisplayName("by identifiers")
        @Nested
        class ByIdentifiers {
            private final List<String> identifiers = Lists.newArrayList("1", "DEMO", "3");

            @DisplayName("should return an empty flux when no Tenants are found")
            @Test
            void testNoneFound() {
                when(tenantRepository.findByIds(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));
                when(tenantRepository.findByMnemonics(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));

                StepVerifier.create(service.findByIdentifiers(securityInfo, identifiers))
                    .verifyComplete();

                verify(tenantRepository, times(1)).findByIds(any(SecurityInfo.class), anyCollection());
                verify(tenantRepository, times(1)).findByMnemonics(any(SecurityInfo.class), anyCollection());
            }

            @DisplayName("should return an emitting flux of Tenants when found")
            @Test
            void testFound() {
                var tenants = Lists.newArrayList(
                    new Tenant.Builder().tenantId(3).name("Build").mnemonic("BUILD").build(),
                    new Tenant.Builder().tenantId(2).name("Demo").mnemonic("DEMO").build(),
                    new Tenant.Builder().tenantId(1).name("Sandbox").mnemonic("SANDBOX").build()
                );

                when(tenantRepository.findByIds(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(Collections.emptyList()));
                when(tenantRepository.findByMnemonics(any(SecurityInfo.class), anyCollection()))
                    .thenReturn(Flux.fromIterable(tenants));

                StepVerifier.create(service.findByIdentifiers(securityInfo, identifiers))
                    .assertNext(tenant -> assertAll("Ensure the Tenants",
                        () -> assertNotNull(tenant, "is valid"),
                        () -> assertEquals("Build", tenant.name())
                    ))
                    .expectNextCount(2)
                    .verifyComplete();

                verify(tenantRepository, times(1)).findByIds(any(SecurityInfo.class), anyCollection());
                verify(tenantRepository, times(1)).findByMnemonics(any(SecurityInfo.class), anyCollection());
            }
        }
    }

    @DisplayName("when creating a Tenant")
    @Nested
    class WhenCreateTenant {
        @DisplayName("should return a mono that emits an error when the new Tenant would create a duplicate")
        @Test
        void testDuplicate() {
            var existingTenant = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            var newTenant = new Tenant.Builder()
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.just(existingTenant));

            StepVerifier.create(service.insert(securityInfo, newTenant, null))
                .expectError(ConflictResourceException.class)
                .verify();

            verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }

        @DisplayName("should return a mono that emits an error when the Tenant is invalid")
        @Test
        void testInvalid() {
            var newTenant = new Tenant.Builder()
                .name("Sandbox")
                .mnemonic("12345")
                .build();

            StepVerifier.create(service.insert(securityInfo, newTenant, null))
                .expectError(BadRequestResourceException.class)
                .verify();

            verify(tenantRepository, times(0)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(0)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }

        @DisplayName("should return a mono that emits the Tenant after it has been inserted")
        @Test
        void testCreate() {
            var newTenant = new Tenant.Builder()
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            var inserted = new Tenant.Builder()
                .from(newTenant)
                .tenantId(10)
                .build();

            when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.empty());
            when(tenantRepository.insertTenant(any(SecurityInfo.class), any(Tenant.class))).thenReturn(Mono.just(inserted));

            StepVerifier.create(service.insert(securityInfo, newTenant, null))
                .assertNext(result -> assertAll("Ensure the Tenant",
                    () -> assertEquals(inserted.name(), result.name(), "has the correct name"),
                    () -> assertEquals(inserted.mnemonic(), result.mnemonic(), "has the correct mnemonic")
                ))
                .verifyComplete();

            verify(tenantRepository, times(0)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(1)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(1)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }
    }

    @DisplayName("when updating a Tenant")
    @Nested
    class WhenUpdateTenant {
        @DisplayName("should return a mono that emits an error when the existing Tenant could not be found")
        @Test
        void testNotFound() {
            var existing = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.empty());
            when(tenantRepository.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.empty());

            StepVerifier.create(service.update(securityInfo, existing))
                .expectError(NotFoundResourceException.class)
                .verify();

            verify(tenantRepository, times(1)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(1)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }

        @DisplayName("should return a mono that emits an error when the existing Tenant would create a duplicate")
        @Test
        void testDuplicate() {
            var existing = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            var duplicate = new Tenant.Builder()
                .tenantId(2)
                .name("Demo")
                .mnemonic("DEMO")
                .build();

            var updated = new Tenant.Builder()
                .from(existing)
                .mnemonic("DEMO")
                .name("Demonstration")
                .build();

            when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.just(duplicate));

            StepVerifier.create(service.update(securityInfo, updated))
                .expectError(ConflictResourceException.class)
                .verify();

            verify(tenantRepository, times(1)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(0)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }

        @DisplayName("should return a mono that emits an error when the Tenant is invalid")
        @Test
        void testInvalid() {
            var existing = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            var updated = new Tenant.Builder()
                .from(existing)
                .mnemonic("12345")
                .name("Demonstration")
                .build();

            StepVerifier.create(service.update(securityInfo, updated))
                .expectError(BadRequestResourceException.class)
                .verify();

            verify(tenantRepository, times(0)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(0)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }

        @DisplayName("should return a mono that emits the Tenant after it has been updated")
        @Test
        void testUpdate_DuplicateCheck_Same() {
            var existing = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            var changed = new Tenant.Builder()
                .from(existing)
                .name("Test")
                .build();

            when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.just(existing));
            when(tenantRepository.updateTenant(any(SecurityInfo.class), any(Tenant.class)))
                .thenReturn(Mono.just(new Tenant.Builder().from(changed).updatedCount(changed.updatedCount() + 1).build()));

            StepVerifier.create(service.update(securityInfo, changed))
                .assertNext(result -> assertAll("Ensure the Tenant",
                    () -> assertEquals(changed, result, "is the same Tenant"),
                    () -> assertEquals(changed.updatedCount() + 1, result.updatedCount(), "has been updated")
                ))
                .verifyComplete();

            verify(tenantRepository, times(1)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(0)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(1)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }

        @DisplayName("should return a mono that emits the Tenant after it has been updated")
        @Test
        void testUpdate_DuplicateCheck_Empty() {
            var existing = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            var changed = new Tenant.Builder()
                .from(existing)
                .name("Test")
                .mnemonic("SANDYBOX")
                .build();

            when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.empty());
            when(tenantRepository.findById(any(SecurityInfo.class), anyInt()))
                .thenReturn(Mono.just(existing));
            when(tenantRepository.updateTenant(any(SecurityInfo.class), any(Tenant.class)))
                .thenReturn(Mono.just(new Tenant.Builder().from(changed).updatedCount(changed.updatedCount() + 1).build()));

            StepVerifier.create(service.update(securityInfo, changed))
                .assertNext(result -> assertAll("Ensure the Tenant",
                    () -> assertEquals(changed, result, "is the same Tenant"),
                    () -> assertEquals(changed.updatedCount() + 1, result.updatedCount(), "has been updated")
                ))
                .verifyComplete();

            verify(tenantRepository, times(1)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(1)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(1)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }

        @DisplayName("should return a mono that emits the Tenant without updating it due to no change")
        @Test
        void testUpdate_NoChange() {
            var existing = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.just(existing));
            when(tenantRepository.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.just(existing));

            StepVerifier.create(service.update(securityInfo, existing))
                .assertNext(result -> assertAll("Ensure the Tenant",
                    () -> assertEquals(existing, result, "has the same Tenant"),
                    () -> assertEquals(existing.updatedCount(), result.updatedCount(), "has the same update count (wasn't updated)")
                ))
                .verifyComplete();

            verify(tenantRepository, times(1)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(1)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }
    }

    @DisplayName("when deleting a Tenant")
    @Nested
    class WhenDeleteTenant {
        @DisplayName("by ID")
        @Nested
        class ById {
            @DisplayName("should return a mono that emits an error when the existing Tenant could not be found")
            @Test
            void testNotFound() {
                when(tenantRepository.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.empty());

                StepVerifier.create(service.deleteById(securityInfo, 1))
                    .expectError(NotFoundResourceException.class)
                    .verify();

                verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
                verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
            }

            @DisplayName("should return a mono that emits when the Tenant was deleted")
            @Test
            void testDelete() {
                var existing = new Tenant.Builder()
                    .tenantId(1)
                    .name("Sandbox")
                    .mnemonic("SANDBOX")
                    .build();

                var deleted = new Tenant.Builder()
                    .from(existing)
                    .active(false)
                    .build();

                when(tenantRepository.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.just(existing));
                when(tenantRepository.updateTenant(any(SecurityInfo.class), any(Tenant.class))).thenReturn(Mono.just(deleted));

                StepVerifier.create(service.deleteById(securityInfo, 1))
                    .verifyComplete();

                verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
                verify(tenantRepository, times(1)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
            }

            @DisplayName("should return a mono that emits when successful (but no delete was necessary)")
            @Test
            void testDelete_NotNeeded() {
                var existing = new Tenant.Builder()
                    .tenantId(1)
                    .name("Sandbox")
                    .mnemonic("SANDBOX")
                    .active(false)
                    .build();

                when(tenantRepository.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.just(existing));

                StepVerifier.create(service.deleteById(securityInfo, 1))
                    .verifyComplete();

                verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
                verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
            }
        }

        @DisplayName("by Mnemonic")
        @Nested
        class ByMnemonic {
            @DisplayName("should return a mono that emits an error when the existing Tenant could not be found")
            @Test
            void testNotFound() {
                when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.empty());

                StepVerifier.create(service.deleteByMnemonic(securityInfo, "SANDBOX"))
                    .expectError(NotFoundResourceException.class)
                    .verify();

                verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
                verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
            }

            @DisplayName("should return a mono that emits when successful (but no delete was necessary)")
            @Test
            void testDelete_NotNeeded() {
                var existing = new Tenant.Builder()
                    .tenantId(1)
                    .name("Sandbox")
                    .mnemonic("SANDBOX")
                    .active(false)
                    .build();

                when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.just(existing));

                StepVerifier.create(service.deleteByMnemonic(securityInfo, "SANDBOX"))
                    .verifyComplete();

                verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
                verify(tenantRepository, times(0)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
            }

            @DisplayName("should return a mono that emits when the Tenant was deleted")
            @Test
            void testDelete() {
                var existing = new Tenant.Builder()
                    .tenantId(1)
                    .name("Sandbox")
                    .mnemonic("SANDBOX")
                    .build();

                var deleted = new Tenant.Builder()
                    .from(existing)
                    .active(false)
                    .build();

                when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString())).thenReturn(Mono.just(existing));
                when(tenantRepository.updateTenant(any(SecurityInfo.class), any(Tenant.class))).thenReturn(Mono.just(deleted));

                StepVerifier.create(service.deleteByMnemonic(securityInfo, "SANDBOX"))
                    .verifyComplete();

                verify(tenantRepository, times(0)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
                verify(tenantRepository, times(1)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
            }
        }
    }

    @DisplayName("when saving Tenants")
    @Nested
    class WhenSaveTenants {
        @DisplayName("should return a flux that emits the Tenants after they have been persisted")
        @Test
        void testSave() {
            var existing = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            var changed = new Tenant.Builder()
                .from(existing)
                .name("Test")
                .build();

            var newTenant = new Tenant.Builder()
                .name("My Tenant")
                .mnemonic("MY_TENANT")
                .build();

            var inserted = new Tenant.Builder()
                .from(newTenant)
                .tenantId(10)
                .build();

            when(tenantRepository.findByMnemonic(any(SecurityInfo.class), anyString()))
                .thenReturn(Mono.empty());
            when(tenantRepository.findById(any(SecurityInfo.class), anyInt()))
                .thenReturn(Mono.just(existing));
            when(tenantRepository.updateTenant(any(SecurityInfo.class), any(Tenant.class)))
                .thenReturn(Mono.just(new Tenant.Builder().from(changed).updatedCount(changed.updatedCount() + 1).build()));

            when(tenantRepository.insertTenant(any(SecurityInfo.class), any(Tenant.class)))
                .thenReturn(Mono.just(inserted));

            StepVerifier.create(service.save(securityInfo, Flux.fromIterable(Lists.newArrayList(changed, newTenant))))
                .assertNext(result -> assertAll("Ensure the Tenant",
                    () -> assertEquals(changed, result, "is the same Tenant"),
                    () -> assertEquals(changed.updatedCount() + 1, result.updatedCount(), "has been updated")
                ))
                .expectNextCount(1L)
                .verifyComplete();

            verify(tenantRepository, times(1)).findById(any(SecurityInfo.class), anyInt());
            verify(tenantRepository, times(2)).findByMnemonic(any(SecurityInfo.class), anyString());
            verify(tenantRepository, times(0)).findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class));

            verify(tenantRepository, times(1)).insertTenant(any(SecurityInfo.class), any(Tenant.class));
            verify(tenantRepository, times(1)).updateTenant(any(SecurityInfo.class), any(Tenant.class));
        }
    }
}
