package com.cagst.bom.user.event.listener;

import com.cagst.bom.user.User;
import com.cagst.bom.user.event.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Listens for the {@link UserCreatedEvent} and writes out to the audit-service.
 *
 * @author Craig Gaskill
 */
@Component
public class UserCreatedEventListener implements ApplicationListener<UserCreatedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreatedEventListener.class);

    @Override
    public void onApplicationEvent(UserCreatedEvent event) {
        LOGGER.info("User [{}] was created.", ((User)event.getSource()).username());
    }
}
