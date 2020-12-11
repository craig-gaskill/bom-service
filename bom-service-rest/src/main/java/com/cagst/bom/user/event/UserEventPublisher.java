package com.cagst.bom.user.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.user.AccountLockedType;
import com.cagst.bom.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Defines methods to publish events for a User.
 *
 * @author Craig Gaskill
 */
@Component
public class UserEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public UserEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishUserCreatedEvent(@NonNull SecurityInfo securityInfo, @NonNull User user) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");

        eventPublisher.publishEvent(new UserCreatedEvent(securityInfo, user));
    }

    public void publishUserUpdatedEvent(@NonNull SecurityInfo securityInfo, @NonNull User user) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");

        eventPublisher.publishEvent(new UserUpdatedEvent(securityInfo, user));
    }

    public void publishUserDeletedEvent(@NonNull SecurityInfo securityInfo, @NonNull User user) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");

        eventPublisher.publishEvent(new UserDeletedEvent(securityInfo, user));
    }

    public void publishUserLockedEvent(@NonNull SecurityInfo securityInfo,
                                       @NonNull User user,
                                       @NonNull AccountLockedType accountLockedType
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");
        Assert.notNull(accountLockedType, "Argument [accountLockedType] cannot be null");

        eventPublisher.publishEvent(new UserLockedEvent(securityInfo, user, accountLockedType));
    }

    public void publishUserUnlockedEvent(@NonNull SecurityInfo securityInfo, @NonNull User user) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(user, "Argument [user] cannot be null");

        eventPublisher.publishEvent(new UserUnlockedEvent(securityInfo, user));
    }
}
