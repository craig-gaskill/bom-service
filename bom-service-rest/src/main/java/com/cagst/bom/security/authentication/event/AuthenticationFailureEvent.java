package com.cagst.bom.security.authentication.event;

/**
 * An event that will be published upon a failed authentication.
 *
 * @author Craig Gaskill
 */
public class AuthenticationFailureEvent extends AuthenticationEvent {
    public AuthenticationFailureEvent(String username, String remoteIpAddress) {
        super(username, remoteIpAddress);
    }
}
