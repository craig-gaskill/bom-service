package com.cagst.bom.spring.security;

import com.cagst.bom.security.SecurityInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Mono;

/**
 * Allows for getting the Spring {@link SecurityContext}.
 *
 * @author Craig Gaskill
 */
public abstract class ReactiveSecurityContextHolder {
    public static Mono<SecurityInfo> getSecurityInfo() {
        return org.springframework.security.core.context.ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
            .map(Authentication::getPrincipal)
            .map(principal -> (SecurityInfo)principal);
    }
}
