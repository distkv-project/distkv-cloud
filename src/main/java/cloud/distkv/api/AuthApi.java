package cloud.distkv.api;

import cloud.distkv.common.constants.Msg;
import cloud.distkv.common.response.R;
import cloud.distkv.common.utils.JwtUtil;
import cloud.distkv.common.annotations.NoAuth;
import cloud.distkv.common.response.BaseController;
import cloud.distkv.dto.TokenDTO;
import cloud.distkv.dto.User;
import cloud.distkv.service.UserService;
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
   * @param user User info.
   * @return Token info.
   */
  @PostMapping("/")
  @NoAuth
  public R authc(@NotNull User user) {
    //TODO (senyer) Add validate check param.
    boolean authc = userService.authc(user);
    if (authc) {
      TokenDTO tokenDTO = new TokenDTO();
      String refreshToken = JwtUtil.signRefreshToken(user.getUsername(), user.getPassword());
      String token = JwtUtil.sign(user.getUsername(), user.getPassword());
      tokenDTO.setRefresh_token(refreshToken);
      tokenDTO.setToken(token);
      return ok(tokenDTO);
    }
    return error(Msg.AUTHENTICATION);
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
