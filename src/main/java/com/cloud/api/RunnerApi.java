package com.cloud.api;

import com.cloud.common.response.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Distkv dashboard
 */
@Controller
@RequestMapping("/run")
public class RunnerApi extends BaseController {

  @GetMapping()
  public String index() {
    return "run/run";
  }

}