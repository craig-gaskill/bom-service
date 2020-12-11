package com.cagst.bom.spring.webflux.cors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * {@link EnableAutoConfiguration Auto-Configuration} for {@link CorsConfigurationSource}.
 *
 * @author Craig Gaskill
 */
@Configuration
@ConditionalOnClass({CorsConfigurationSource.class})
@EnableConfigurationProperties(CorsProperties.class)
public class CorsAutoConfiguration {
    private final CorsProperties corsProperties;

    @Autowired
    public CorsAutoConfiguration(@NonNull CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsProperties.toCorsConfiguration());

        return source;
    }
}
