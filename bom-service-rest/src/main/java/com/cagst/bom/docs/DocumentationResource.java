package com.cagst.bom.docs;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Defines endpoints to retrieve documentation about the REST API provided by this service.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("docs")
public class DocumentationResource {
    private String documentation;

    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public Mono<String> getDocumentation() {
        return Mono.just(loadDocumentation());
    }

    private String loadDocumentation() {
        if (StringUtils.isEmpty(documentation)) {
            try (InputStream in = new ClassPathResource("docs/index.html").getInputStream()) {
                documentation = new String(in.readAllBytes());
            } catch (IOException ex) {
                // do nothing
            }
        }

        return documentation;
    }
}
