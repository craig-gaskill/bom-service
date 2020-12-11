package com.cagst.bom.user.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.user.User;

/**
 * An event that will be published when a User is updated.
 *
 * @author Craig Gaskill
 */
public class UserUpdatedEvent extends UserEvent {
    public UserUpdatedEvent(SecurityInfo securityInfo, User user) {
        super(securityInfo, user);
    }
}
