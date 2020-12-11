package com.cagst.bom.user.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.user.User;

/**
 * An event that will be published when a User is unlocked.
 *
 * @author Craig Gaskill
 */
public class UserUnlockedEvent extends UserEvent {
    public UserUnlockedEvent(SecurityInfo securityInfo, User user) {
        super(securityInfo, user);
    }
}
