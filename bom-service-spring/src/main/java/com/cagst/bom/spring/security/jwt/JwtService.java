package com.cagst.bom.spring.security.jwt;

import java.time.OffsetDateTime;

import com.cagst.bom.security.SecurityInfo;
import org.springframework.lang.Nullable;

/**
 * Defines methods for generating an Access and a Refresh token.
 *
 * @author Craig Gaskill
 */
public interface JwtService {
  /**
   * Generates an access token for the specified User.
   *
   * @param tenantId
   *    The unique identifier of the Tenant the Token is to be generated for.
   * @param userId
   *    The unique identifier of the User the Token is to be generated for.
   * @param expiryDateTime
   *    The {@link OffsetDateTime} the token will expire.
   *
   * @return A String representation of a JWT for the specified User.
   */
  String generateAccessToken(int tenantId, long userId, OffsetDateTime expiryDateTime);

  /**
   * Will check the specified token to determine if it is valid or not.
   *
   * @param token
   *    The token to validate.
   *
   * @return {@code true} if the token is valid and has not expired.
   */
  boolean isTokenValid(String token);

  /**
   * Will decode the specified token.
   *
   * @param token
   *      The token to validate.
   * @param source
   *      An optional Source to associate with the generated {@link SecurityInfo} if the token is valid.
   * @param timeZone
   *      An optional TimeZone to associate with the generated {@link SecurityInfo} if the token is valid.
   *
   * @return A {@link SecurityInfo} if the token is valid, otherwise it will return {@code null}.
   */
  @Nullable
  SecurityInfo decodeToken(String token, @Nullable String source, @Nullable String timeZone);
}
