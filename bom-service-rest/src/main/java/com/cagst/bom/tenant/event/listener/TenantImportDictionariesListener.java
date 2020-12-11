package com.cagst.bom.tenant.event.listener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.cagst.bom.dictionary.Dictionary;
import com.cagst.bom.dictionary.DictionaryService;
import com.cagst.bom.imports.ImportType;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.tenant.Tenant;
import com.cagst.bom.tenant.event.TenantCreatedEvent;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;

/**
 * Listens for {@link TenantCreatedEvent} and imports the dictionaries for the new Tenant.
 *
 * @author Craig Gaskill
 */
@Component
/* package */ class TenantImportDictionariesListener implements ApplicationListener<TenantCreatedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TenantImportDictionariesListener.class);

    private final DictionaryService dictionaryService;
    private final ObjectMapper objectMapper;

    @Autowired
    public TenantImportDictionariesListener(DictionaryService dictionaryService,
                                            ObjectMapper objectMapper
    ) {
        this.dictionaryService = dictionaryService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void onApplicationEvent(TenantCreatedEvent event) {
        var tenant = (Tenant)event.getSource();

        if (CollectionUtils.isEmpty(tenant.features())) {
            // this shouldn't happen since we should associate the Tenant to at least
            // the core feature
            LOGGER.warn("No features associated to Tenant [{}], skipping Dictionary Import", tenant.mnemonic());
            return;
        }

        tenant.features().forEach(feature -> importDictionary(event.getSecurityInfo(), feature.meaning()));
    }

    private void importDictionary(SecurityInfo securityInfo, String featureMeaning) {
        var dictionaryResource = new ClassPathResource(featureMeaning.toLowerCase() + "-dictionaries.json");
        if (dictionaryResource.exists()) {
            try (InputStream in = dictionaryResource.getInputStream()) {
                var json = StreamUtils.copyToString(in, StandardCharsets.UTF_8);
                var dictionaries = objectMapper.readValue(json, new TypeReference<List<Dictionary>>() {});

                dictionaryService.importDictionaries(
                    securityInfo,
                    ImportType.ADD_ONLY,
                    featureMeaning,
                    Flux.fromIterable(dictionaries)
                ).subscribe();
            } catch (IOException ex) {
                LOGGER.error("Failed to import Dictionaries for Feature [" + featureMeaning + "]", ex);
            }
        }
    }
}
