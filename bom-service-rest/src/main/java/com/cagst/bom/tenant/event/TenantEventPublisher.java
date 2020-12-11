package com.cagst.bom.tenant.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.tenant.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Defines methods to publish events for a Tenant.
 *
 * @author Craig Gaskill
 */
@Component
public class TenantEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public TenantEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishTenantCreatedEvent(@NonNull SecurityInfo securityInfo, @NonNull Tenant tenant) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(tenant, "Argument [tenant] cannot be null");

        eventPublisher.publishEvent(new TenantCreatedEvent(securityInfo, tenant));
    }

    public void publishTenantDeletedEvent(@NonNull SecurityInfo securityInfo, @NonNull Tenant tenant) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.notNull(tenant, "Argument [tenant] cannot be null");

        eventPublisher.publishEvent(new TenantDeletedEvent(securityInfo, tenant));
    }
}
