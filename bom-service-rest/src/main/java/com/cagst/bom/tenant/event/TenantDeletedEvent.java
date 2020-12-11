package com.cagst.bom.tenant.event;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.tenant.Tenant;

/**
 * An event that will be published when a Tenant is deleted.
 *
 * @author Craig Gaksill
 */
public class TenantDeletedEvent extends TenantEvent {
    public TenantDeletedEvent(SecurityInfo securityInfo, Tenant source) {
        super(securityInfo, source);
    }
}
