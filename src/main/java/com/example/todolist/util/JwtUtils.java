package com.example.todolist.util;

import io.jsonwebtoken.Claims;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * The Interface JwtUtils.
 */
public interface JwtUtils {

  /**
   * Gets the key.
   *
   * @return the key
   */
  Key getKey();

  /**
   * Extract username.
   *
   * @param token the token
   * @return the string
   */
  String extractUsername(String token);

  /**
   * Extract expiration.
   *
   * @param token the token
   * @return the date
   */
  Date extractExpiration(String token);

  /**
   * Extract claim.
   *
   * @param <T> the generic type
   * @param token the token
   * @param claimsResolver the claims resolver
   * @return the t
   */
  <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  /**
   * Extract all claims.
   *
   * @param token the token
   * @return the claims
   */
  Claims extractAllClaims(String token);

  /**
   * Checks if is token expired.
   *
   * @param token the token
   * @return true, if is token expired
   */
  boolean isTokenExpired(String token);

  /**
   * Generate token.
   *
   * @param username the username
   * @param roles the roles
   * @return the string
   */
  String generateToken(String username, List<String> roles);

  /**
   * Creates the token.
   *
   * @param claims the claims
   * @param subject the subject
   * @param roles the roles
   * @return the string
   */
  String createToken(Map<String, Object> claims, String subject, List<String> roles);

  /**
   * Validate token.
   *
   * @param token the token
   * @param user the user
   * @return the boolean
   */
  Boolean validateToken(String token, String user);

  /**
   * Gets the roles token.
   *
   * @param token the token
   * @return the roles token
   */
  List<String> getRolesToken(String token);
}
