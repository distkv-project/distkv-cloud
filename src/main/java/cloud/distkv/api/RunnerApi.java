package cloud.distkv.api;

import cloud.distkv.common.annotations.NoAuth;
import cloud.distkv.common.response.BaseController;
import cloud.distkv.distkvclient.DistkvCommandClient;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Distkv dashboard.
 */
@Controller
@RequestMapping("/run")
public class RunnerApi extends BaseController {

  @Resource
  private DistkvCommandClient distkvCommandClient;

  /**
   * Navigate to run dashboard.
   *
   * @return run/run.html
   */
  @GetMapping()
  @NoAuth
  public String index() {
    return "run/run";
  }

  /**
   * Execute cmd.
   *
   * @param cmd cmd content
   * @return result
   */
  @GetMapping("cmd")
  @ResponseBody
  @NoAuth
  public String cmd(String cmd) {
    //TODO (senyer) Add validate check. Make sure the format of each command is legal.
    return distkvCommandClient.exec(cmd);
  }


}
