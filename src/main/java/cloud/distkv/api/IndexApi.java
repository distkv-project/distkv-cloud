package cloud.distkv.api;

import cloud.distkv.common.annotations.DoAuth;
import cloud.distkv.common.response.R;
import cloud.distkv.common.response.BaseController;
import cloud.distkv.dto.Conditions;
import cloud.distkv.service.QueryService;
import cloud.distkv.service.WriteService;
import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Distkv restful request by distkv-cloud.
 */
@RestController
@RequestMapping("/")
@Slf4j
public class IndexApi extends BaseController {

  @Resource
  private QueryService queryService;
  @Resource
  private WriteService writeService;

  @GetMapping("query")
  @DoAuth
  public R query(@NotNull Conditions conditions) {
    // TODO(senyer) valid check. Conditions need to be properly verified.
    return ok(queryService.query(conditions));
  }

  @PostMapping("write")
  @DoAuth
  public R write(@NotNull Conditions conditions) {
    // TODO(senyer) valid check. Conditions need to be properly verified.
    return ok(writeService.write(conditions));
  }
}
