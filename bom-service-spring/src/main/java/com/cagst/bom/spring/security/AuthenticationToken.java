package com.cagst.bom.spring.security;

import java.time.ZoneId;
import java.time.ZoneOffset;

import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * An {@link org.springframework.security.core.Authentication} implementation designed for BOM Services.
 *
 * @author Craig Gaskill
 */
public class AuthenticationToken extends AbstractAuthenticationToken {
    private final String credentials;
    private final SecurityInfo securityInfo;

    /**
     * Constructor used to create an un-authenticated instance of {@code PlatformAuthenticationToken}.
     *
     * @param credentials
     *      The credentials to be validated (a JWT Token).
     * @param source
     *      The source / page the request was made from.
     * @param timeZone
     *      A {@link String} representation of the TimeZone the request was made from.
     */
    public AuthenticationToken(String credentials, @Nullable String source, @Nullable String timeZone) {
        super(null);

        Assert.hasText(credentials, "Argument [credentials] cannot be null or empty.");

        this.credentials = credentials;
        this.securityInfo = new SecurityInfo.Builder()
            .tenantId(0)
            .userId(0L)
            .source(source)
            .zoneId(StringUtils.isEmpty(timeZone) ? ZoneOffset.UTC : ZoneId.of(timeZone))
            .build();
    }

    /**
     * Constructor used to create an instance of {@code CagstAuthenticationToken}.
     *
     * @param credentials
     *      The credentials to be validated (a JWT Token).
     * @param securityInfo
     *      The {@link SecurityInfo} to associate with this authentication token.
     */
    public AuthenticationToken(String credentials, SecurityInfo securityInfo) {
        super(null);

        Assert.hasText(credentials, "Argument [credentials] cannot be null or empty.");
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null.");

        this.credentials = credentials;
        this.securityInfo = securityInfo;

        if (this.securityInfo.tenantId() > 0 && this.securityInfo.userId() > 0) {
            // if we have a Tenant ID and a User ID, we will assume we have been authenticated
            // we will allow an Application ID of 0 to possibly be used by a builder / design tool
            this.setAuthenticated(true);
        }
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return securityInfo;
    }
}
