package cloud.distkv.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import cloud.distkv.common.constants.Magic;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtil {

  private static long EXPIRE_TIME ;
  @Value(value = "${jwt.token.expire-time}")
  public void setExpireTime(long expireTime) {
    EXPIRE_TIME = expireTime;
  }

  private static long REFRESH_EXPIRE_TIME ;
  @Value(value = "${jwt.refresh-token.expire-time}")
  public void setRefreshExpireTime(long expireTime) {
    REFRESH_EXPIRE_TIME = expireTime;
  }


  private static final String USERNAME = Magic.USERNAME;

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

  public static String signRefreshToken(String username, String secret) {
    Date date = new Date(System.currentTimeMillis() + REFRESH_EXPIRE_TIME);
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withClaim(USERNAME, username)
        .withExpiresAt(date)
        .sign(algorithm);
  }
}
