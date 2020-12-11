package com.cagst.bom.security.authentication.event;

import com.cagst.bom.security.authentication.LoginStatus;

/**
 * An event that will be published upon a successful authentication.
 *
 * @author Craig Gaskill
 */
public class AuthenticationSuccessfulEvent extends AuthenticationEvent {
    private final LoginStatus loginStatus;

    public AuthenticationSuccessfulEvent(String username,
                                         String remoteIpAddress,
                                         LoginStatus loginStatus
    ) {
        super(username, remoteIpAddress);

        this.loginStatus = loginStatus;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }
}
