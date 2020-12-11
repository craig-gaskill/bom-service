package com.cagst.bom.security.authentication;

import java.net.InetSocketAddress;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import com.cagst.bom.security.authentication.event.AuthEventPublisher;
import com.cagst.bom.spring.security.ReactiveSecurityContextHolder;
import com.cagst.bom.spring.security.jwt.JwtService;
import com.cagst.bom.user.User;
import com.cagst.bom.user.access.UserAccess;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Defines endpoints used for authentication.
 *
 * @author Craig Gaskill
 */
@RestController
@RequestMapping("auth")
public class AuthenticationResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationResource.class);

    private static final long ACCESS_EXPIRY_IN_MINUTES  = 12 * 60;       // 12 hours
    private static final long REFRESH_EXPIRY_IN_MINUTES = 12 * 60 + 15;  // 12 hours + 15 minutes

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final AuthEventPublisher authEventPublisher;

    @Autowired
    public AuthenticationResource(AuthenticationService authenticationService,
                                  JwtService jwtService,
                                  AuthEventPublisher authEventPublisher
    ) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.authEventPublisher = authEventPublisher;
    }

    /**
     * The {@code /login} endpoint to authenticate a user by username / password.
     *
     * @param loginRequest
     *    A {@link LoginRequest} that contains the username / password of the User trying to authenticate.
     * @param request
     *    The {@link ServerHttpRequest} that initiated the call.
     *
     * @return A {@link Mono} containing the {@link LoginResponse} that may contain a JWT access and refresh token
     * if the authentication was successful, otherwise it will simply contain the Status of why the login failed.
     */
    @PostMapping("login")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody LoginRequest loginRequest, ServerHttpRequest request) {
        LOGGER.debug("Received request to login");

        String remoteAddress = getRemoteAddress(request);
        return authenticationService.login(loginRequest.username(), loginRequest.password(), remoteAddress)
            .flatMap(user -> {
                if (!user.active()) {
                    authEventPublisher.publishAuthenticationSuccessfulEvent(loginRequest.username(), remoteAddress, LoginStatus.ACCOUNT_DISABLED);
                    return Mono.just(generateResponse(LoginStatus.ACCOUNT_DISABLED));
                } else if (user.accountLockedDateTime() != null) {
                    authEventPublisher.publishAuthenticationSuccessfulEvent(loginRequest.username(), remoteAddress, LoginStatus.ACCOUNT_LOCKED);
                    return Mono.just(generateResponse(LoginStatus.ACCOUNT_LOCKED));
                } else if (user.accountExpiredDateTime() != null) {
                    authEventPublisher.publishAuthenticationSuccessfulEvent(loginRequest.username(), remoteAddress, LoginStatus.ACCOUNT_EXPIRED);
                    return Mono.just(generateResponse(LoginStatus.ACCOUNT_EXPIRED));
                } else if (user.passwordExpiredDateTime() != null) {
                    authEventPublisher.publishAuthenticationSuccessfulEvent(loginRequest.username(), remoteAddress, LoginStatus.PASSWORD_EXPIRED);
                    return Mono.just(generateResponse(LoginStatus.PASSWORD_EXPIRED));
                } else {
                    authEventPublisher.publishAuthenticationSuccessfulEvent(loginRequest.username(), remoteAddress, LoginStatus.VALID);
                    return Mono.just(generateResponse(user));
                }
            })
            .switchIfEmpty(Mono.defer(() -> {
                authEventPublisher.publishAuthenticationFailureEvent(loginRequest.username(), remoteAddress);

                return Mono.just(new ResponseEntity<>(
                    new LoginResponse.Builder().loginStatus(LoginStatus.INVALID).build(),
                    HttpStatus.UNAUTHORIZED
                ));
            }));
    }

    /**
     * Will check to see if the specified {@code email} is already being used.
     *
     * @param email
     *      The email to check to see if it is being used.
     *
     * @return A status of 200 (OK) if it is being used and a status of 204 (NO_CONTENT) if not being used (not found).
     */
    @RequestMapping(value = "register/{email}", method = RequestMethod.HEAD)
    public Mono<ResponseEntity<Void>> checkEmail(@PathVariable("email") String email) {
        LOGGER.debug("Received request to checkEmail [{}]", email);

        return authenticationService.checkEmail(email)
            .map(exists -> exists ? ResponseEntity.ok().build() : ResponseEntity.noContent().build());
    }

    /**
     * The {@code /register} endpoint to register a new Tenant and User.
     *
     * @param registerRequest
     *      A {@link RegisterRequest} that contains the information needed to register a new Tenant and User.
     * @param request
     *      The {@link ServerHttpRequest} that initiated the call.
     *
     * @return A {@link Mono} containing the {@link LoginResponse} that may contain a JWT access and refresh token
     * if the registration was successful, otherwise it will simply contain the Status of why the registration failed.
     */
    @PostMapping("register")
    public Mono<ResponseEntity<LoginResponse>> register(@RequestBody RegisterRequest registerRequest, ServerHttpRequest request) {
        LOGGER.debug("Received request to register");

        String remoteAddress = getRemoteAddress(request);
        return authenticationService.register(registerRequest, remoteAddress)
            .flatMap(user -> Mono.just(generateResponse(user)))
            .switchIfEmpty(Mono.defer(() -> Mono.just(new ResponseEntity<>(
                new LoginResponse.Builder().loginStatus(LoginStatus.INVALID).build(),
                HttpStatus.UNAUTHORIZED
            ))));
    }

    /**
     * The {@code /changePassword} endpoint to change a user's password.
     *
     * @param changePasswordRequest
     *    A {@link ChangePasswordRequest} that contains the new and confirmation passwords.
     *
     * @return A {@link Mono} containing the {@link LoginResponse} that may contain a JWT access and refresh token
     * if the password was successfully changed
     */
    @PostMapping("changePassword")
    public Mono<ResponseEntity<LoginResponse>> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        LOGGER.debug("Received request to login");

        return ReactiveSecurityContextHolder.getSecurityInfo()
            .flatMap(securityInfo ->
                authenticationService.changePassword(
                    securityInfo,
                    changePasswordRequest.newPassword(),
                    changePasswordRequest.confirmationPassword()
                ).flatMap(user -> Mono.just(generateResponse(user))
            ));
    }

    private String getRemoteAddress(ServerHttpRequest request) {
        String clientAddress = null;

        HttpHeaders headers = request.getHeaders();
        if (!CollectionUtils.isEmpty(headers)) {
            List<String> realIps = headers.get("X-Real-IP");
            if (!CollectionUtils.isEmpty(realIps)) {
                clientAddress = realIps.get(0);
            }

            if (StringUtils.isEmpty(clientAddress)) {
                List<String> forwarded = headers.get("X-Forwarded-For");
                if (!CollectionUtils.isEmpty(forwarded)) {
                    clientAddress = forwarded.get(0);
                }
            }
        }

        if (StringUtils.isEmpty(clientAddress)) {
            InetSocketAddress remoteAddress = request.getRemoteAddress();
            if (remoteAddress != null) {
                clientAddress = remoteAddress.getAddress().getHostAddress();
            }
        }

        return clientAddress;
    }

    private ResponseEntity<LoginResponse> generateResponse(@NonNull LoginStatus loginStatus) {
        switch (loginStatus) {
            case ACCOUNT_DISABLED:
            case ACCOUNT_LOCKED:
            case ACCOUNT_EXPIRED:
            case PASSWORD_EXPIRED:
                return new ResponseEntity<>(new LoginResponse.Builder().loginStatus(loginStatus).build(), HttpStatus.UNAUTHORIZED);

            default:
                return new ResponseEntity<>(new LoginResponse.Builder().loginStatus(LoginStatus.INVALID).build(), HttpStatus.UNAUTHORIZED);
        }
    }

    private ResponseEntity<LoginResponse> generateResponse(@NonNull User user) {
        if (CollectionUtils.isEmpty(user.access())) {
            return generateResponse(LoginStatus.INVALID);
        }

        Optional<UserAccess> findAccess = user.access().stream().findFirst();
        if (findAccess.isEmpty()) {
            return generateResponse(LoginStatus.INVALID);
        }

        UserAccess userAccess = findAccess.get();
        OffsetDateTime expiryDateTime = OffsetDateTime.now().plusMinutes(ACCESS_EXPIRY_IN_MINUTES);

        return ResponseEntity.ok(new LoginResponse.Builder()
            .loginStatus(user.temporaryPassword() ? LoginStatus.PASSWORD_TEMPORARY : LoginStatus.VALID)
            .accessToken(jwtService.generateAccessToken(userAccess.tenantId(), userAccess.userId(), expiryDateTime))
            .build());
    }
}
