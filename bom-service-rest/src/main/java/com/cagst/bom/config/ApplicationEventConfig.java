package com.cagst.bom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Provides configuration of the systems application events.
 *
 * @author Craig Gaskill
 */
@Configuration
public class ApplicationEventConfig {
    @Autowired
    @Bean
    public ApplicationEventMulticaster simpleApplicationEventMulticaster(ThreadPoolTaskExecutor taskExecutor) {
        var eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(taskExecutor);

        return eventMulticaster;
    }
}
