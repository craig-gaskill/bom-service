package com.cagst.bom.role.permission;

import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;

/**
 * Defines methods for retrieving / persisting {@link RolePermission} records.
 *
 * @author Craig Gaskill
 */
public interface RolePermissionRepository {
    /**
     * Finds all {@link RolePermission} associated with the specified Role (identified by the role's
     * unique identifier).
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user performing the request.
     * @param roleId
     *      The unique identifier of the Role to return Role Permissions for.
     *
     * @return A {@link Flux} that emits all the Role Permissions for the specified Role.
     */
    @NonNull
    Flux<RolePermission> findForRoleId(@NonNull SecurityInfo securityInfo, long roleId);
}
