package com.cagst.bom.security.authentication.event;

import com.cagst.bom.security.authentication.LoginStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Defines methods to publish events for Authentication.
 * Spring already defines an AuthenticationEventPublisher,
 * so we shortened this to avoid a conflict.
 *
 * @author Craig Gaskill
 */
@Component
public class AuthEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public AuthEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishAuthenticationSuccessfulEvent(@NonNull String username,
                                                     @NonNull String remoteIpAddress,
                                                     @NonNull LoginStatus loginStatus
    ) {
        Assert.hasText(username, "Argument [username] cannot be null or empty.");
        Assert.hasText(remoteIpAddress, "Argument [remoteIpAddress] cannot be null or empty.");
        Assert.notNull(loginStatus, "Argument [loginStatus] cannot be null.");

        eventPublisher.publishEvent(new AuthenticationSuccessfulEvent(username, remoteIpAddress, loginStatus));
    }

    public void publishAuthenticationFailureEvent(@NonNull String username, @NonNull String remoteIpAddress) {
        Assert.hasText(username, "Argument [username] cannot be null or empty.");
        Assert.hasText(remoteIpAddress, "Argument [remoteIpAddress] cannot be null or empty.");

        eventPublisher.publishEvent(new AuthenticationFailureEvent(username, remoteIpAddress));
    }
}
