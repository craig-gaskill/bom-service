package com.cagst.bom.spring.security.jwt;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cagst.bom.security.SecurityInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * Defines methods for generating an Access and a Refresh token.
 *
 * @author Craig Gaskill
 */
@Service
public class JwtServiceImpl implements JwtService {
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtServiceImpl.class);

  private final Algorithm algorithm;
  private final JWTVerifier jwtVerifier;

  @Autowired
  public JwtServiceImpl(Algorithm algorithm) {
    this.algorithm   = algorithm;
    this.jwtVerifier = JWT.require(algorithm).build();

    OffsetDateTime fd = OffsetDateTime.now().plusWeeks(2);
    LOGGER.info("Token [{}]", generateAccessToken(1, 1, fd));
  }

  @Override
  public String generateAccessToken(int tenantId, long userId, OffsetDateTime expiryDateTime) {
    return JWT.create()
        .withAudience(String.valueOf(tenantId))
        .withSubject(String.valueOf(userId))
        .withExpiresAt(Date.from(expiryDateTime.toInstant()))
        .sign(algorithm);
  }

  @Override
  public boolean isTokenValid(String token) {
    DecodedJWT decodedJWT = decodeToken(token);
    return (decodedJWT != null);
  }

  @Override
  public SecurityInfo decodeToken(String token, @Nullable String source, @Nullable String timeZone) {
    DecodedJWT decodedJWT = decodeToken(token);
    if (decodedJWT == null) {
      return null;
    }

    if (CollectionUtils.isEmpty(decodedJWT.getAudience())) {
      return null;
    }

    String audience = decodedJWT.getAudience().get(0);
    if (!StringUtils.isNumeric(audience)) {
      return null;
    }

    String subject = decodedJWT.getSubject();
    if (!StringUtils.isNumeric(subject)) {
      return null;
    }

    return new SecurityInfo.Builder()
        .tenantId(Integer.parseInt(audience))
        .userId(Long.parseLong(subject))
        .source(source)
        .zoneId(StringUtils.isNotBlank(timeZone) ? ZoneId.of(timeZone) : ZoneOffset.UTC)
        .build();
  }

  @Nullable
  private DecodedJWT decodeToken(String token) {
    try {
      return jwtVerifier.verify(token);
    } catch (Exception ex) {
      LOGGER.error("Failed to decode token due to [{}]", ex.getMessage());
      return null;
    }
  }
}
