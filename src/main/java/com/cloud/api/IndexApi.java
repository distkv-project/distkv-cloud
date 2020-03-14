package com.cloud.api;

import com.cloud.common.annotations.DoAuth;
import com.cloud.common.response.BaseController;
import com.cloud.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Distkv restful request by distkv-cloud
 */
@RestController
@RequestMapping("/")
@Slf4j
public class IndexApi extends BaseController {

  @PostMapping("query")
  @DoAuth
  public R query() {
    //TODO (senyer)
    return error("unimplemented");
  }

  @PostMapping("write")
  @DoAuth
  public R write() {
    //TODO (senyer)
    return error("unimplemented");
  }
}
