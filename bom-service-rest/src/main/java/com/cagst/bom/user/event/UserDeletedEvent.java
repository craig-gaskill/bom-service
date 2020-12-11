package com.cagst.bom.user.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.user.User;

/**
 * An event that will be published when a User is deleted.
 *
 * @author Craig Gaskill
 */
public class UserDeletedEvent extends UserEvent {
    public UserDeletedEvent(SecurityInfo securityInfo, User user) {
        super(securityInfo, user);
    }
}
