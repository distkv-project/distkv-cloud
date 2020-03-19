package cloud.distkv.service;

import static cloud.distkv.common.constants.Msg.INCORRECT_TYPE;
import static cloud.distkv.common.constants.Type.LIST_GET;
import static cloud.distkv.common.constants.Type.STR_GET;
import cloud.distkv.dto.Conditions;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "queryService")
@Slf4j
public class QueryService {

  @Resource
  private DistkvService distkvService;

  /**
   * Execute a query operation.
   *
   * @param conditions The parameters.
   * @return Result from distkv.
   */
  public Object query(Conditions conditions) {
    String type = conditions.getType();
    String key = conditions.getKey();
    switch (type) {
      case STR_GET:
        return distkvService.strsGet(key);
      case LIST_GET:
        //TODO (senyer)
        break;
      default:
        log.debug("Incorrect request type {}.", type);
        return INCORRECT_TYPE;
    }
    return null;
  }

}