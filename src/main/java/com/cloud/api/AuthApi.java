package com.cloud.api;

import static com.cloud.common.constants.Msg.AUTHENTICATION;

import com.cloud.common.response.BaseController;
import com.cloud.common.response.R;
import com.cloud.common.utils.JwtUtil;
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
   * Get a token by username and password
   *
   * @param user user info
   * @return token
   */
  @PostMapping("/")
  public R authc(@NotNull User user) {
    //TODO (senyer) Add validate check param
    boolean authc = userService.authc(user);
    if (authc) {
      String token = JwtUtil.sign(user.getUsername(), user.getPassword());
      return ok(token);
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
  public R getUserInfo(String refreshToken) {
    //TODO finish it.
    return ok();
  }

}
