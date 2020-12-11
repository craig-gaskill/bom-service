package com.cagst.bom.security.authentication;

/**
 * Defines possible statuses that can be returned upon login.
 *
 * @author Craig Gaskill
 */
public enum LoginStatus {
  ACCOUNT_DISABLED,
  ACCOUNT_LOCKED,
  ACCOUNT_EXPIRED,
  PASSWORD_EXPIRED,
  PASSWORD_TEMPORARY,
  INVALID,
  VALID
}
