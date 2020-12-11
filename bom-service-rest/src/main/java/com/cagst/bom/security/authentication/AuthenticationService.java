package com.cagst.bom.security.authentication;

import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.user.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import reactor.core.publisher.Mono;

/**
 * Defines methods to authenticate a {@link User}.
 *
 * @author Craig Gaskill
 */
public interface AuthenticationService {
    /**
     * Will find the {@link User} for the specified username and attempt to authenticate them.
     *
     * @param username
     *    The username of the User to login.
     * @param password
     *    The raw text password used to authenticate with.
     * @param remoteAddress
     *    The remote address from where the call initiated.
     *
     * @return A {@link Mono} that may emit a {@link User} if found and authenticated.
     */
    Mono<User> login(@NonNull String username, @NonNull String password, @Nullable String remoteAddress);

    /**
     * Will register a new Tenant and User.
     *
     * @param registerRequest
     *      The {@link RegisterRequest} that contains the information needed to register a new Tenant and User.
     * @param remoteAddress
     *      The remote address from where the call initiated.
     *
     * @return A {@link Mono} that may emit a {@link User} if registration was successful.
     */
    Mono<User> register(@NonNull RegisterRequest registerRequest, @Nullable String remoteAddress);

    /**
     * Will check if the email address is being used.
     *
     * @param email
     *      The email address to check if it is being used.
     *
     * @return A {@link Mono} that will emit {@code true} if the email address is already being used and {@code false} otherwise.
     */
    Mono<Boolean> checkEmail(@NonNull String email);

    /**
     * Will change the password of the specified {@link User}.
     *
     * @param securityInfo
     *      The {@link SecurityInfo} that contains the information about the user and tenant the request is for.
     * @param newPassword
     *      The new password for the user.
     * @param confirmationPassword
     *      The confirmation password.
     *
     * @return A {@link Mono} that may emit a {@link User} after the password has been changed.
     */
    Mono<User> changePassword(@NonNull SecurityInfo securityInfo,
                              @NonNull String newPassword,
                              @NonNull String confirmationPassword);
}
