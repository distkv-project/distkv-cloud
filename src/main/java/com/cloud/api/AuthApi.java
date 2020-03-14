package com.cloud.api;

import static com.cloud.common.constants.Msg.AUTHENTICATION;

import com.cloud.common.annotations.NoAuth;
import com.cloud.common.response.BaseController;
import com.cloud.common.response.R;
import com.cloud.common.utils.JwtUtil;
import com.cloud.dto.TokenDTO;
import com.cloud.dto.User;
import com.cloud.service.UserService;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthApi extends BaseController {

  @Resource
  private UserService userService;

  /**
   * Get a token by username and password.
   *
   * @param user user info.
   * @return token info.
   */
  @PostMapping("/")
  @NoAuth
  public R authc(@NotNull User user) {
    //TODO (senyer) Add validate check param
    boolean authc = userService.authc(user);
    if (authc) {
      TokenDTO tokenDTO = new TokenDTO();
      String refreshToken = JwtUtil.signRefreshToken(user.getUsername(), user.getPassword());
      String token = JwtUtil.sign(user.getUsername(), user.getPassword());
      tokenDTO.setRefresh_token(refreshToken);
      tokenDTO.setToken(token);
      return ok(tokenDTO);
    }
    return error(AUTHENTICATION);
  }

  /**
   * Get a new token by refresh token.
   *
   * @param refreshToken refresh token.
   * @return token
   */
  @PostMapping("refreshToken")
  @NoAuth
  public R getUserInfo(String refreshToken) {
    User user = userService.getUserByUsername(JwtUtil.getUsername(refreshToken));
    if (user != null) {
      if (JwtUtil.verify(refreshToken, user.getUsername(), user.getPassword())) {
        return ok(JwtUtil.sign(user.getUsername(), user.getPassword()));
      }
    }
    return error("Token Expired!");
  }

}
