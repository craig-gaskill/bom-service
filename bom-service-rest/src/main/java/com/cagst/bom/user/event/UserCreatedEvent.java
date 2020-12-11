package com.cagst.bom.user.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.user.User;

/**
 * An event that will be published when a User is created.
 *
 * @author Craig Gaskill
 */
public class UserCreatedEvent extends UserEvent {
    public UserCreatedEvent(SecurityInfo securityInfo, User user) {
        super(securityInfo, user);
    }
}
