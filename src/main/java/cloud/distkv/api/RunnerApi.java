package cloud.distkv.api;

import cloud.distkv.common.annotations.NoAuth;
import cloud.distkv.common.response.BaseController;
import cloud.distkv.service.DistkvService;
import javax.annotation.Resource;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

/**
 * Distkv dashboard.
 */
@Controller
@RequestMapping("/sh")
@CrossOrigin
public class RunnerApi extends BaseController {

  @Resource
  private DistkvService distkvService;

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
   * Execute a command.
   *
   * @param command The command to execute.
   * @return Execute result.
   */
  @PostMapping("command")
  @ResponseBody
  @NoAuth
  public String cmd(@RequestBody JSONObject command) {
    String code = command.get("command").toString();
    //TODO (senyer) Add validate check. Make sure the format of each command is legal.
    return distkvService.exec(code);
  }


}
