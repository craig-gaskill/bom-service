package com.cagst.bom.security;

import com.cagst.bom.user.User;

/**
 * Defines methods to retrieve / persist {@link SecurityPolicy} resources.
 *
 * @author Craig Gaskill
 */
public interface SecurityPolicyService {
    SecurityPolicy DEFAULT_SECURITY_POLICY = new SecurityPolicy.Builder()
        .policyName("Default")
        .build();

    /**
     * Retrieves the {@link SecurityPolicy} for the specified {@link User user}. Will look for any
     * security policy associated to the specific user, if not found will the retrieve the default
     * {@link SecurityPolicy}.
     *
     * @param user
     *     The {@link User} to retrieve the {@link SecurityPolicy} for
     *
     * @return The {@link SecurityPolicy} associated with the specified user.
     */
    SecurityPolicy findSecurityPolicyForUser(User user);

    /**
     * Retrieves the default security policy.
     *
     * @return The default {@link SecurityPolicy}.
     */
    SecurityPolicy defaultSecurityPolicy();
}
