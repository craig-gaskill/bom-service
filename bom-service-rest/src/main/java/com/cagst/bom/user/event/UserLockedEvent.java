package com.cagst.bom.user.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.user.AccountLockedType;
import com.cagst.bom.user.User;

/**
 * An event that will be published when a User is locked.
 *
 * @author Craig Gaskill
 */
public class UserLockedEvent extends UserEvent {
    private final AccountLockedType accountLockedType;

    public UserLockedEvent(SecurityInfo securityInfo,
                           User user,
                           AccountLockedType accountLockedType
    ) {
        super(securityInfo, user);

        this.accountLockedType = accountLockedType;
    }

    public AccountLockedType getAccountLockedType() {
        return this.accountLockedType;
    }
}
