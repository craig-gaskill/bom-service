package com.cagst.bom.tenant.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.tenant.Tenant;

/**
 * An event that will be published when a Tenant is created.
 *
 * @author Craig Gaskill
 */
public class TenantCreatedEvent extends TenantEvent {
    public TenantCreatedEvent(SecurityInfo securityInfo, Tenant source) {
        super(securityInfo, source);
    }
}
