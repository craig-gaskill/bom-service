package com.cagst.bom.tenant.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.tenant.Tenant;
import org.springframework.context.ApplicationEvent;

/**
 * Class to be extended by all Tenant events. Abstract as it
 * doesn't make sense for generic tenant events to be published directly.
 *
 * @author Craig Gaskill
 */
public abstract class TenantEvent extends ApplicationEvent {
    private final SecurityInfo securityInfo;

    public TenantEvent(SecurityInfo securityInfo, Tenant source) {
        super(source);

        this.securityInfo = securityInfo;
    }

    public SecurityInfo getSecurityInfo() {
        return securityInfo;
    }
}
