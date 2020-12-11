package com.cagst.bom.user.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.user.User;
import org.springframework.context.ApplicationEvent;

/**
 * Class to be extended by all User events. Abstract as it
 * doesn't make sense for generic user events to be published directly.
 *
 * @author Craig Gaskill
 */
public abstract class UserEvent extends ApplicationEvent {
    private final SecurityInfo securityInfo;

    public UserEvent(SecurityInfo securityInfo, User user) {
        super(user);

        this.securityInfo = securityInfo;
    }

    public SecurityInfo getSecurityInfo() {
        return securityInfo;
    }
}
