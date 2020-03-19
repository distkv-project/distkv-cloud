package cloud.distkv.api;

import cloud.distkv.common.annotations.DoAuth;
import cloud.distkv.common.annotations.NoAuth;
import cloud.distkv.common.response.R;
import cloud.distkv.common.response.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Distkv restful request by distkv-cloud.
 */
@Controller
@RequestMapping("/")
@Slf4j
public class IndexApi extends BaseController {

  @GetMapping()
  @NoAuth
  public String index() {
    return "index";
  }

  @PostMapping("query")
  @ResponseBody
  @DoAuth
  public R query() {
    //TODO (senyer) finish it.
    return error("unimplemented");
  }

  @PostMapping("write")
  @ResponseBody
  @DoAuth
  public R write() {
    //TODO (senyer) finish it.
    return error("unimplemented");
  }
}
