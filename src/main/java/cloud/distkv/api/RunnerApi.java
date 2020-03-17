package cloud.distkv.api;

import cloud.distkv.common.annotations.NoAuth;
import cloud.distkv.common.response.BaseController;
import cloud.distkv.distkvclient.DistkvCommandClient;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Distkv dashboard.
 */
@Controller
@RequestMapping("/run")
@CrossOrigin
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
  @PostMapping("cmd")
  @ResponseBody
  @NoAuth
  public String cmd(@RequestBody JSONObject cmd) {
    String code = cmd.get("cmd").toString();
    //TODO (senyer) Add validate check. Make sure the format of each command is legal.
    return distkvCommandClient.exec(code);
  }


}
