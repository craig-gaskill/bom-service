package com.cagst.bom.spring.security.jwt;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.spring.security.AuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

/**
 * Implementation of {@link org.springframework.security.authentication.ReactiveAuthenticationManager} to validate a JWT token from the Authorization header.
 *
 * @author Craig Gaskill
 */
@Component
public class JwtReactiveAuthenticationManager implements ReactiveAuthenticationManager {
    private final JwtService jwtService;

    @Autowired
    public JwtReactiveAuthenticationManager(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if (authentication == null) {
            return Mono.empty();
        }

        String accessToken = authentication.getCredentials().toString();
        if (StringUtils.isEmpty(accessToken)) {
            return Mono.empty();
        }

        SecurityInfo securityInfo = jwtService.decodeToken(accessToken, null, null);
        if (securityInfo == null) {
            return Mono.empty();
        }

        if (authentication.getPrincipal() == null || !(authentication.getPrincipal() instanceof SecurityInfo)) {
            return Mono.empty();
        }

        SecurityInfo tokenSourceInfo = (SecurityInfo) authentication.getPrincipal();

        return Mono.just(new AuthenticationToken(
            accessToken,
            new SecurityInfo.Builder().from(securityInfo)
                .source(tokenSourceInfo.source())
                .zoneId(tokenSourceInfo.zoneId())
                .build()
        ));
    }
}
