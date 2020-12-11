package com.cagst.bom.security;

import com.cagst.bom.user.User;
import org.springframework.stereotype.Service;

/**
 * An implementation of the {@link SecurityPolicyService} that provides the business rules needed to properly
 * retrieve and persist {@link SecurityPolicy} resources.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class SecurityPolicyServiceImpl implements SecurityPolicyService {
    @Override
    public SecurityPolicy findSecurityPolicyForUser(User user) {
        return DEFAULT_SECURITY_POLICY;
    }

    @Override
    public SecurityPolicy defaultSecurityPolicy() {
        return DEFAULT_SECURITY_POLICY;
    }
}
