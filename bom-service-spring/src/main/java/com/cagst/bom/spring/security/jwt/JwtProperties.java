package com.cagst.bom.spring.security.jwt;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Defines properties and their defaults for Security settings.
 *
 * @author Craig Gaskill
 */
@ConfigurationProperties(prefix = "bom.security.jwt")
public class JwtProperties {
  private static final int KEY_LENGTH = 32;

  private String secretKey;

  public void setSecretKey(String key) {
    this.secretKey = key;
  }

  public String getSecretKey() {
    if (StringUtils.isEmpty(secretKey)) {
      SecureRandom random = new SecureRandom();
      BigInteger randomInt = new BigInteger(KEY_LENGTH * 5, random);

      secretKey = randomInt.toString(32);
    }

    return secretKey;
  }
}
