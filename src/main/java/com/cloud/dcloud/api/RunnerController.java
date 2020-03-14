package com.cloud.dcloud.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Distkv dashboard
 */
@Controller
@RequestMapping("/run")
public class RunnerController extends BaseController{

  @GetMapping()
  public String index() {
    return "run/run";
  }

}
