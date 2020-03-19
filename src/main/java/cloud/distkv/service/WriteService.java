package cloud.distkv.service;

import static cloud.distkv.common.constants.Msg.INCORRECT_TYPE;
import static cloud.distkv.common.constants.Type.EXPIRE;
import static cloud.distkv.common.constants.Type.LIST_PUT;
import static cloud.distkv.common.constants.Type.PINE_TOPPER;
import static cloud.distkv.common.constants.Type.STR_PUT;
import cloud.distkv.dto.Conditions;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service(value = "writeService")
@Slf4j
public class WriteService {

  @Resource
  private DistkvService distkvService;

  /**
   * Execute a write operation.
   *
   * @param conditions The parameters.
   * @return Result from distkv.
   */
  public Object write(Conditions conditions) {
    String type = conditions.getType();
    String key = conditions.getKey();
    switch (type) {
      case STR_PUT:
        Object strValue = conditions.getValue();
        // TODO (senyer) Assert.assertString(String Value);
        // TODO (senyer) value can not be null;
        distkvService.strsPut(key, String.valueOf(strValue));
        return null;
      case LIST_PUT:
        //TODO (senyer)
        break;
      case EXPIRE:
        //TODO (senyer)
        break;
      case PINE_TOPPER:
        //TODO (senyer)
        break;
      default:
        log.debug("Incorrect request type {}.", type);
        return INCORRECT_TYPE.toString();
    }
    return null;
  }

}
