package com.cloud.api;

import com.cloud.common.annotations.NoAuth;
import com.cloud.common.response.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Distkv dashboard.
 */
@Controller
@RequestMapping("/run")
public class RunnerApi extends BaseController {

  /**
   * Navigate to run dashboard.
   * @return run/run.html
   */
  @GetMapping()
  @NoAuth
  public String index() {
    return "run/run";
  }

}
