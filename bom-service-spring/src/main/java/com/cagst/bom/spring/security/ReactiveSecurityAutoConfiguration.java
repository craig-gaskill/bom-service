package com.cagst.bom.spring.security;

import com.auth0.jwt.algorithms.Algorithm;
import com.cagst.bom.spring.security.jwt.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

/**
 * {@link EnableAutoConfiguration Auto-Configuration} for Spring Security using JWT.
 *
 * @author Craig Gaskill
 */
@Configuration
@ConditionalOnClass({ReactiveAuthenticationManager.class, ServerSecurityContextRepository.class})
@EnableConfigurationProperties(JwtProperties.class)
public class ReactiveSecurityAutoConfiguration {
    private final JwtProperties jwtProperties;

    @Autowired
    public ReactiveSecurityAutoConfiguration(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Bean
    @Autowired
    public ServerSecurityContextRepository getSecurityContextRepository(@NonNull ReactiveAuthenticationManager reactiveAuthenticationManager) {
        return new SecurityContextRepository(reactiveAuthenticationManager);
    }

    @Bean
    public Algorithm getAlgorithm() {
        return Algorithm.HMAC512(jwtProperties.getSecretKey());
    }
}
