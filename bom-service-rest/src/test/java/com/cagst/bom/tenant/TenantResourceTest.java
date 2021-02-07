package com.cagst.bom.tenant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.removeHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

import java.time.OffsetDateTime;
import java.util.List;

import com.cagst.bom.config.ApplicationSecurityConfig;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.security.ReactiveSecurityAutoConfiguration;
import com.cagst.bom.spring.security.jwt.JwtReactiveAuthenticationManager;
import com.cagst.bom.spring.security.jwt.JwtService;
import com.cagst.bom.spring.security.jwt.JwtServiceImpl;
import com.cagst.bom.spring.webflux.exception.BadRequestResourceException;
import com.cagst.bom.spring.webflux.exception.NotFoundResourceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Test class for the {@link TenantResource} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("TenantResource")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@Import({
    JwtServiceImpl.class,
    JwtReactiveAuthenticationManager.class,
    ReactiveSecurityAutoConfiguration.class,
    ApplicationSecurityConfig.class
})
@WebFluxTest(TenantResource.class)
class TenantResourceTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private TenantService tenantService;

    private WebTestClient webClient;

    private final FieldDescriptor[] tenantFields = new FieldDescriptor[]{
        fieldWithPath("tenantId")
            .description("The unique identifier for the Tenant"),
        fieldWithPath("mnemonic")
            .description("A unique identifier for the Tenant that is consistent between environments"),
        fieldWithPath("name")
            .description("The name of the Tenant"),
        fieldWithPath("locale")
            .description("The locale the Tenant is in"),
        fieldWithPath("subscriptionType")
            .description("The type of subscription the Tenant is signing up for"),
        fieldWithPath("subscriptionStartDate")
            .description("When the subscription started")
            .type(OffsetDateTime.class)
            .optional(),
        fieldWithPath("subscriptionEndDate")
            .description("When the subscription ends")
            .type(OffsetDateTime.class)
            .optional(),
        fieldWithPath("createdId")
            .description("The unique identifier of the User that created the record")
            .type(Long.class)
            .optional(),
        fieldWithPath("createdDateTime")
            .description("The date/time the record was created"),
        fieldWithPath("active")
            .description("Indicates if the record is active"),
        fieldWithPath("updatedId")
            .description("The unique identifier of the User that last updated the record")
            .type(Long.class)
            .optional(),
        fieldWithPath("updatedDateTime")
            .description("The date/time the record was last updated"),
        fieldWithPath("updatedCount")
            .description("The number of times the record has been updated")
    };

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        webClient = WebTestClient.bindToApplicationContext(applicationContext)
            .configureClient()
            .filter(documentationConfiguration(restDocumentation)
                .operationPreprocessors()
                    .withRequestDefaults(prettyPrint())
                    .withResponseDefaults(prettyPrint())
            )
            .build();
    }

    @DisplayName("when GET /tenants")
    @Nested
    class WhenFindTenants {
        @DisplayName("should return UNAUTHORIZED when no Authorization header is provided")
        @Test
        void testNotAuthorized() {
            webClient.get()
                .uri("/tenants")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
        }

        @DisplayName("should return NO_CONTENT when no Tenants are found")
        @Test
        void testNoneFound() {
            when(tenantService.findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class))).thenReturn(Flux.empty());

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("/tenants")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
        }

        @DisplayName("should return OK when Tenants are found")
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

            when(tenantService.findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class)))
                .thenReturn(Flux.fromIterable(List.of(tenant1, tenant2)));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("/tenants?start=0&limit=25&includeInactive=false")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Tenant.class)
                .hasSize(2)
                .consumeWith(document("tenants/get-tenants",
                    preprocessRequest(
                        removeHeaders(HttpHeaders.AUTHORIZATION)
                    ),
                    requestParameters(
                        parameterWithName("start")
                            .description("Specifies the position in the collection to start retrieving records from."),
                        parameterWithName("limit")
                            .description("Specifies the maximum number of records to retrieve."),
                        parameterWithName("includeInactive")
                            .description("Indicates if inactive records are to be returned.")
                    )
                ));
        }
    }

    @DisplayName("when GET a specific Tenant")
    @Nested
    class WhenFindByIdentifier {
        @DisplayName("should return UNAUTHORIZED when no Authorization header is provided")
        @Test
        void testNotAuthorized() {
            webClient.get()
                .uri("/tenants/{identifier}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
        }

        @DisplayName("should return NO_CONTENT when no Tenant is found for the specified identifier")
        @Test
        void testNotFound() {
            when(tenantService.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.empty());

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("/tenants/{identifier}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
        }

        @DisplayName("should return OK when Tenant is found for the specified identifier")
        @Test
        void testFound() {
            var tenant = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            when(tenantService.findById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.just(tenant));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("/tenants/{identifier}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Tenant.class)
                .isEqualTo(tenant)
                .consumeWith(document("tenants/get-tenant",
                    preprocessRequest(
                        removeHeaders(HttpHeaders.AUTHORIZATION)
                    ),
                    pathParameters(
                        parameterWithName("identifier")
                            .description("The identifier of the Tenant to retrieve. Could either be the unique identifier (tenantId) or the mnemonic.")
                    ),
                    responseFields(tenantFields)
                ));
        }
    }

    @DisplayName("when POST a new Tenant")
    @Nested
    class WhenCreateTenant {
        @DisplayName("should return UNAUTHORIZED when no Authorization header is provided")
        @Test
        void testNotAuthorized() {
            webClient.post()
                .uri("/tenants")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
        }

        @DisplayName("should return BAD_REQUEST when the Tenant would cause a duplicate")
        @Test
        void testBadRequest() {
            var newTenant = new Tenant.Builder()
                .name("New Tenant")
                .mnemonic("NEW_TENANT")
                .build();

            when(tenantService.insert(any(SecurityInfo.class), any(Tenant.class), isNull()))
                .thenReturn(Mono.error(new BadRequestResourceException("Tenant already exists")));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .post()
                .uri("/tenants")
                .body(BodyInserters.fromValue(newTenant))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
        }

        @DisplayName("should return CREATED when the Tenant is created")
        @Test
        void testPost() {
            var newTenant = new Tenant.Builder()
                .name("New Tenant")
                .mnemonic("NEW_TENANT")
                .build();

            var created = new Tenant.Builder()
                .from(newTenant)
                .tenantId(101)
                .build();

            when(tenantService.insert(any(SecurityInfo.class), any(Tenant.class), isNull())).thenReturn(Mono.just(created));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .post()
                .uri("/tenants")
                .body(BodyInserters.fromValue(newTenant))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Tenant.class)
                .isEqualTo(created)
                .consumeWith(document("tenants/create-tenant",
                    preprocessRequest(
                        removeHeaders(HttpHeaders.AUTHORIZATION)
                    ),
                    relaxedResponseFields(tenantFields)
                ));
        }
    }

    @DisplayName("when PUT an existing Tenant")
    @Nested
    class WhenUpdateTenant {
        @DisplayName("should return UNAUTHORIZED when no Authorization header is provided")
        @Test
        void testNotAuthorized() {
            webClient.put()
                .uri("/tenants/{tenantId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
        }

        @DisplayName("should return NOT_FOUND when the Tenant could not be found for updating")
        @Test
        void testNotFound() {
            var existingTenant = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            when(tenantService.update(any(SecurityInfo.class), any(Tenant.class)))
                .thenReturn(Mono.error(new NotFoundResourceException("Tenant not found")));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .put()
                .uri("/tenants/{tenantId}", 1)
                .body(BodyInserters.fromValue(existingTenant))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
        }

        @DisplayName("should return BAD_REQUEST when the Tenant would cause a duplicate")
        @Test
        void testBadRequest() {
            var existingTenant = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            when(tenantService.update(any(SecurityInfo.class), any(Tenant.class)))
                .thenReturn(Mono.error(new BadRequestResourceException("Tenant already exists")));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .put()
                .uri("/tenants/{tenantId}", 1)
                .body(BodyInserters.fromValue(existingTenant))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
        }

        @DisplayName("should return OK when the Tenant is updated")
        @Test
        void testPut() {
            var existingTenant = new Tenant.Builder()
                .tenantId(1)
                .name("Sandbox")
                .mnemonic("SANDBOX")
                .build();

            when(tenantService.update(any(SecurityInfo.class), any(Tenant.class)))
                .thenReturn(Mono.just(existingTenant));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .put()
                .uri("/tenants/{tenantId}", 1)
                .body(BodyInserters.fromValue(existingTenant))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Tenant.class)
                .isEqualTo(existingTenant)
                .consumeWith(document("tenants/update-tenant",
                    preprocessRequest(
                        removeHeaders(HttpHeaders.AUTHORIZATION)
                    ),
                    responseFields(tenantFields)
                ));
        }
    }

    @DisplayName("when DELETE an existing Tenant")
    @Nested
    class WhenDeleteTenant {
        @DisplayName("should return UNAUTHORIZED when no Authorization header is provided")
        @Test
        void testNotAuthorized() {
            webClient.delete()
                .uri("/tenants/{tenantId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
        }

        @DisplayName("should return NOT_FOUND when the Tenant could not be found for deleting")
        @Test
        void testNotFound() {
            when(tenantService.deleteById(any(SecurityInfo.class), anyInt()))
                .thenReturn(Mono.error(new NotFoundResourceException("Tenant not found")));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .delete()
                .uri("/tenants/{tenantId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
        }

        @DisplayName("should return NO_CONTENT when the Tenant was deleted")
        @Test
        void testDelete() {
            when(tenantService.deleteById(any(SecurityInfo.class), anyInt())).thenReturn(Mono.empty());

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .delete()
                .uri("/tenants/{tenantId}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent()
                .expectBody()
                .consumeWith(document("tenants/delete-tenant",
                    preprocessRequest(
                        removeHeaders(HttpHeaders.AUTHORIZATION)
                    ),
                    pathParameters(
                        parameterWithName("tenantId").description("The unique identifier of the Tenant to retrieve")
                    )
                ));
        }
    }

    private ExchangeFilterFunction authentication() {
        return (clientRequest, next) -> next.exchange(ClientRequest.from(clientRequest)
            .headers(headers -> headers.setBearerAuth(jwtService.generateAccessToken(1, 1L, OffsetDateTime.now().plusMinutes(5))))
            .build());
    }
}
