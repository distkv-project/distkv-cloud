package com.cloud.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cloud.common.utils.JwtUtil;
import com.cloud.dto.User;
import org.springframework.stereotype.Service;

@Service("tokenService")
public class JwtTokenService {

  /**
   * Give a jwt signature.
   *
   * @param user user info.
   * @return jwt token.
   */
  public String sign(User user) {
    return JwtUtil.sign(user.getUsername(), user.getPassword());
  }

  /**
   * Give a jwt signature directly.
   *
   * @param user user info.
   * @return jwt token.
   */
  public String getToken(User user) {
    return JWT.create().withAudience(user.getUsername())
        .sign(Algorithm.HMAC256(user.getPassword()));
  }

}
