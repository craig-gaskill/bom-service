package com.cagst.bom.security.authentication;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Objects;

import com.cagst.bom.person.Person;
import com.cagst.bom.person.PersonService;
import com.cagst.bom.role.Role;
import com.cagst.bom.role.RoleRepository;
import com.cagst.bom.security.SecurityInfo;
import com.cagst.bom.security.SecurityPolicyService;
import com.cagst.bom.spring.webflux.exception.BadRequestResourceException;
import com.cagst.bom.subscription.SubscriptionType;
import com.cagst.bom.tenant.Tenant;
import com.cagst.bom.tenant.TenantService;
import com.cagst.bom.user.AccountLockedType;
import com.cagst.bom.user.User;
import com.cagst.bom.user.UserConverter;
import com.cagst.bom.user.UserEntity;
import com.cagst.bom.user.UserRepository;
import com.cagst.bom.user.access.UserAccess;
import com.cagst.bom.user.access.UserAccessRepository;
import com.cagst.bom.user.role.UserRole;
import com.cagst.bom.user.role.UserRoleRepository;
import com.cagst.bom.util.CommonStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

/**
 * An implementation of the {@link AuthenticationService} that provides the business rules
 * for authenticating a {@link User}.
 *
 * @author Craig Gaskill
 */
@Service
/* package */ class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SecurityPolicyService securityPolicyService;
    private final PersonService personService;
    private final TenantService tenantService;
    private final RoleRepository roleRepository;
    private final UserAccessRepository userAccessRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public AuthenticationServiceImpl(PasswordEncoder passwordEncoder,
                                     UserRepository userRepository,
                                     SecurityPolicyService securityPolicyService,
                                     PersonService personService,
                                     TenantService tenantService,
                                     RoleRepository roleRepository,
                                     UserAccessRepository userAccessRepository,
                                     UserRoleRepository userRoleRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.securityPolicyService = securityPolicyService;
        this.personService = personService;
        this.tenantService = tenantService;
        this.roleRepository = roleRepository;
        this.userAccessRepository = userAccessRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Mono<User> login(@NonNull String username,
                            @NonNull String password,
                            @Nullable String remoteAddress
    ) {
        Assert.hasText(username, "Argument [username] cannot be null or empty");
        Assert.hasText(password, "Argument [password] cannot be null or empty");

        return userRepository.findByUsername(username)
            .flatMap(userRepository::incrementAttempt)
            .flatMap(user -> {
                var securityPolicy = securityPolicyService.defaultSecurityPolicy();

                user = new UserEntity.Builder()
                    .from(user)
                    .securityPolicy(securityPolicyService.defaultSecurityPolicy())
                    .build();

                // validate the password
                if (!passwordEncoder.matches(password, user.password())) {
                    LOGGER.warn("Invalid password for user [{}]", username);

                    // if the account isn't locked, see if it needs to be locked (because they exceeded their login attempts)
                    if (user.accountLockedDateTime() == null) {
                        // if the security policy has a maximum number of login attempts > 0
                        // and we have exceeded the number of maximum attempts
                        // then lock the account.
                        if (securityPolicy.maxAttempts() > 0 && user.loginAttempts() > securityPolicy.maxAttempts()) {
                            LOGGER.info("User account [{}] was locked", username);
                            return userRepository.lockUser(user);
                        } else {
                            // if the password is invalid and we don't need to lock the account yer
                            // simply emit nothing to terminate the flow
                            return Mono.empty();
                        }
                    } else {
                        // if the user's account is locked, we want to return the User
                        // so we can properly generate a token with a status of ACCOUNT_LOCKED
                        return Mono.just(user);
                    }
                } else {
                    // password is valid, check to see if we should unlock the account (if it was
                    // automatically locked previously due to exceeding the number of login attempts
                    if (user.accountLockedDateTime() != null && Objects.equals(user.accountLockedType(), AccountLockedType.Automatic)) {
                        // account may be automatically unlocked
                        if (securityPolicy.lockedInMinutes() > 0) {
                            OffsetDateTime lockedDateTime = user.accountLockedDateTime();
                            OffsetDateTime unlockAfter = lockedDateTime.plusMinutes(securityPolicy.lockedInMinutes());

                            if (OffsetDateTime.now().isAfter(unlockAfter)) {
                                LOGGER.info("User account [{}] was unlocked", username);
                                return userRepository.unlockUser(user);
                            }

                        }
                    }

                    return Mono.just(user);
                }
            })
            .flatMap(user -> {
                if (user.securityPolicy() != null && user.securityPolicy().expiryInDays() > 0) {
                    // check to see if the user's password has expired
                    var changedOn = (user.passwordChangedDateTime() != null ? user.passwordChangedDateTime() : user.createdDateTime());
                    var validUntil = changedOn.plusDays(user.securityPolicy().expiryInDays());

                    if (OffsetDateTime.now().isAfter(validUntil)) {
                        LOGGER.info("User account [{}] has expired", username);
                        return userRepository.expirePassword(user);
                    }
                }

                return Mono.just(user);
            })
            .flatMap(userRepository::resetAttempts)
            .flatMap(usr -> userAccessRepository.findDefault(usr.userId())
                .map(userAccess ->
                    UserConverter.convert(new UserEntity.Builder()
                        .from(usr)
                        .access(Collections.singletonList(userAccess))
                        .build()
                    )
                )
            );
    }

    @Override
    @Transactional
    public Mono<User> register(@NonNull RegisterRequest registerRequest, String remoteAddress) {
        var tempSecurityInfo = new SecurityInfo.Builder()
            .tenantId(0) // this isn't needed / used when creating a Tenant or a User
            .userId(1L)
            .source("REGISTER")
            .build();

        return userRepository.insert(tempSecurityInfo, new UserEntity.Builder()
            .username(registerRequest.email())
            .temporaryPassword(false)
            .password(passwordEncoder.encode(registerRequest.password()))
            .build()
        ).flatMap(user ->
            insertTenant(tempSecurityInfo, user, registerRequest)
                .flatMap(si ->
                    insertPerson(si, registerRequest)
                        .flatMap(person -> insertUserAccess(si, person, remoteAddress))
                )
                .flatMap(this::insertAdminRole)
                .map(__ -> UserConverter.convert(user))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<Boolean> checkEmail(@NonNull String email) {
        Assert.hasText(email, "Argument [email] cannot be null or empty.");

        return userRepository.findByUsername(email)
            .map(Objects::nonNull)
            .defaultIfEmpty(false);
    }

    @Override
    @Transactional
    public Mono<User> changePassword(@NonNull SecurityInfo securityInfo,
                                     @NonNull String newPassword,
                                     @NonNull String confirmationPassword
    ) {
        Assert.notNull(securityInfo, "Argument [securityInfo] cannot be null");
        Assert.hasText(newPassword, "Argument [newPassword] cannot be null or empty");
        Assert.hasText(confirmationPassword, "Argument [confirmationPassword] cannot be null or empty");

        return userRepository.findById(securityInfo, securityInfo.userId())
            .flatMap(user -> {
                if (!StringUtils.equalsIgnoreCase(newPassword, confirmationPassword)) {
                    return Mono.error(new BadRequestResourceException("New password and confirmation password do not match"));
                } else if (passwordEncoder.matches(newPassword, user.password())) {
                    return Mono.error(new BadRequestResourceException("New password was not change"));
                } else {
                    return userRepository.changePassword(user, passwordEncoder.encode(newPassword));
                }
            })
            .map(UserConverter::convert);
    }

    private Mono<SecurityInfo> insertTenant(SecurityInfo securityInfo, UserEntity user, RegisterRequest registerRequest) {
        return tenantService.insert(
            new SecurityInfo.Builder()
                .from(securityInfo)
                .userId(user.userId())
                .build(),
            new Tenant.Builder()
                .name(registerRequest.tenantName())
                .mnemonic(CommonStringUtils.normalizeToKey(registerRequest.tenantName()))
                .subscriptionType(SubscriptionType.EarlyAdopter)
                .subscriptionStartDate(LocalDate.now())
                .build(),
            registerRequest.features()
        ).map(tenant -> new SecurityInfo.Builder()
            .from(securityInfo)
            .userId(user.userId())
            .tenantId(tenant.tenantId())
            .build()
        );
    }

    private Mono<Role> insertAdminRole(SecurityInfo securityInfo) {
        return roleRepository.insert(
            securityInfo,
            new Role.Builder()
                .name("Administrator")
                .fullAccess(true)
                .build()
        ).map(role -> {
            userRoleRepository.merge(
                securityInfo,
                new UserRole.Builder()
                    .userId(securityInfo.userId())
                    .roleId(role.roleId())
                    .build()
            );

            return role;
        });
    }

    private Mono<Person> insertPerson(SecurityInfo securityInfo, RegisterRequest registerRequest) {
        return personService.insert(
            securityInfo,
            new Person.Builder()
                .givenName(registerRequest.firstName())
                .familyName(registerRequest.lastName())
                .build()
        );
    }

    private Mono<SecurityInfo> insertUserAccess(SecurityInfo securityInfo, Person person, String remoteAddress) {
        return userAccessRepository.merge(
            securityInfo,
            new UserAccess.Builder()
                .tenantId(securityInfo.tenantId())
                .userId(securityInfo.userId())
                .personId(person.personId())
                .defaultIndicator(true)
                .lastLoginDateTime(OffsetDateTime.now())
                .lastLoginIp(remoteAddress)
                .build()
        ).map(__ -> securityInfo);
    }
}
