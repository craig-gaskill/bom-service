package com.cagst.bom.security.authentication.event;

import org.springframework.context.ApplicationEvent;

/**
 * Class to be extended by all Authentication events. Abstract as it
 * doesn't make sense for generic authentication events to be published directly.
 */
public abstract class AuthenticationEvent extends ApplicationEvent {
    private final String remoteIpAddress;

    public AuthenticationEvent(String username, String remoteIpAddress) {
        super(username);

        this.remoteIpAddress = remoteIpAddress;
    }

    public String getRemoteIpAddress() {
        return remoteIpAddress;
    }
}
