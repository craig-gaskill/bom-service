package com.cagst.bom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

/**
 * Provides configuration of the systems Security.
 *
 * @author Craig Gaskill
 */
@Configuration
@EnableWebFluxSecurity
public class ApplicationSecurityConfig {
    private static final int STRENGTH = 12;

    private final ReactiveAuthenticationManager authenticationManager;
    private final ServerSecurityContextRepository securityContextRepository;

    @Autowired
    public ApplicationSecurityConfig(@NonNull ReactiveAuthenticationManager authenticationManager,
                                     @NonNull ServerSecurityContextRepository securityContextRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    /**
     * @return The {@link PasswordEncoder} to use to encode and validate a user's password.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(STRENGTH);
    }

    /**
     * Configure a {@link SecurityWebFilterChain} for JWT authentication / authorization.
     *
     * @param http
     *    The {@link ServerHttpSecurity} to configure.
     *
     * @return A {@link SecurityWebFilterChain} configured for JWT authentication / authorization.
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        // Disable CSRF (cross site request forgery) ... for now
        http.csrf().disable();

        // Disable Basic Authentication, Form Login, and Logout
        http.httpBasic().disable();
        http.formLogin().disable();
        http.logout().disable();

        // Specify the SecurityContextRepository to provide the JWT token to the AuthenticationManager
        http.securityContextRepository(securityContextRepository);

        // Specify the AuthenticationManager to use to validate the JWT token
        http.authenticationManager(authenticationManager);

        // Allow all routes to '/auth/**' and '/docs/**'
        http.authorizeExchange().pathMatchers(
            "/auth/**",
            "/docs/**"
        ).permitAll();

        // Allow OPTIONS
        http.authorizeExchange().pathMatchers(HttpMethod.OPTIONS).permitAll();

        // Secure all other routes
        http.authorizeExchange().anyExchange().authenticated();

        return http.build();
    }
}
