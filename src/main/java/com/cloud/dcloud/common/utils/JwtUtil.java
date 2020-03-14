package com.cloud.dcloud.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {

  //TODO (senyer) improve this
  private static final long EXPIRE_TIME = (long) (3 * 60 * 1000);

  private static final String USERNAME = "username";

  public static boolean verify(String token, String username, String secret) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      JWTVerifier verifier = JWT.require(algorithm)
              .withClaim(USERNAME, username)
              .build();
      verifier.verify(token);
      return true;
    } catch (Exception exception) {
      return false;
    }
  }

  public static String getUsername(String token) {
    try {
      DecodedJWT jwt = JWT.decode(token);
      return jwt.getClaim(USERNAME).asString();
    } catch (JWTDecodeException e) {
      return null;
    }
  }

  public static String sign(String username, String secret) {
    Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
            .withClaim(USERNAME, username)
            .withExpiresAt(date)
            .sign(algorithm);

  }
}
