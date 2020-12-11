package com.cagst.bom.dictionary;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

import java.time.OffsetDateTime;
import java.util.List;

import com.cagst.bom.config.SecurityConfig;
import com.cagst.bom.search.SearchCriteria;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.security.ReactiveSecurityAutoConfiguration;
import com.cagst.bom.spring.security.jwt.JwtReactiveAuthenticationManager;
import com.cagst.bom.spring.security.jwt.JwtService;
import com.cagst.bom.spring.security.jwt.JwtServiceImpl;
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
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import reactor.core.publisher.Flux;

/**
 * Test class for the {@link DictionaryResource} class.
 *
 * @author Craig Gaskill
 */
@DisplayName("DictionaryResource")
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@Import({
    JwtServiceImpl.class,
    JwtReactiveAuthenticationManager.class,
    ReactiveSecurityAutoConfiguration.class,
    SecurityConfig.class
})
@WebFluxTest(DictionaryResource.class)
class DictionaryResourceTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JwtService jwtService;

    @MockBean
    private DictionaryService dictionaryService;

    private WebTestClient webClient;

    private final FieldDescriptor[] dictionaryFields = new FieldDescriptor[]{
        fieldWithPath("dictionaryId").description("The unique identifier for the Dictionary"),
        fieldWithPath("meaning").description("A unique identifier for the Dictionary that is consistent between environments"),
        fieldWithPath("display").description("The display value for the Dictionary"),
        fieldWithPath("description").description("A longer textual description that describes the purpose of the Dictionary"),
        fieldWithPath("viewable").description("Indicates if this dictionary is viewable to the user"),
        fieldWithPath("editable").description("Indicates if this dictionary can be edited by the user"),
        fieldWithPath("deletable").description("Indicates if this dictionary can be deleted by the user"),
        fieldWithPath("locale").description("The locale the Tenant is in")
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

    @DisplayName("when GET /dictionaries")
    @Nested
    class WhenFindDictionaries {
        @DisplayName("should return UNAUTHORIZED when no Authorization header is provided")
        @Test
        void testNotAuthorized() {
            webClient.get()
                .uri("/dictionaries")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isUnauthorized();
        }

        @DisplayName("should return NO_CONTENT when no Dictionaries are found")
        @Test
        void testNoneFound() {
            when(dictionaryService.findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class))).thenReturn(Flux.empty());

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("/dictionaries")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();
        }

        @DisplayName("should return OK when Dictionaries are found")
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

            when(dictionaryService.findByCriteria(any(SecurityInfo.class), any(SearchCriteria.class)))
                .thenReturn(Flux.fromIterable(List.of(addressType, phoneType, emailType)));

            webClient.mutate()
                .filter(authentication())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri("/dictionaries")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Dictionary.class)
                .hasSize(3)
                .consumeWith(document("dictionaries/get-dictionaries"));
        }
    }

    private ExchangeFilterFunction authentication() {
        return (clientRequest, next) -> next.exchange(ClientRequest.from(clientRequest)
            .headers(headers -> headers.setBearerAuth(jwtService.generateAccessToken(1, 1L, OffsetDateTime.now().plusMinutes(5))))
            .build());
    }
}
