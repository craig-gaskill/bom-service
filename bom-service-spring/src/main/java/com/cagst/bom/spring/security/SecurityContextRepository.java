package com.cagst.bom.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Implements the {@link ServerSecurityContextRepository} to forward the JWT Token to the AuthenticationManager.
 *
 * @author Craig Gaskill
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {
    private static final String BEARER_TOKEN = "Bearer ";
    private static final String SOURCE = "X-Source";
    private static final String TIME_ZONE = "X-Time-Zone";

    private final ReactiveAuthenticationManager authenticationManager;

    @Autowired
    public SecurityContextRepository(ReactiveAuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        String accessToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (StringUtils.startsWithIgnoreCase(accessToken, BEARER_TOKEN)) {
            String source = request.getHeaders().getFirst(SOURCE);
            String timeZone = request.getHeaders().getFirst(TIME_ZONE);

            Authentication auth = new AuthenticationToken(accessToken.replace(BEARER_TOKEN, ""), source, timeZone);

            return authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        } else {
            return Mono.empty();
        }
    }
}
