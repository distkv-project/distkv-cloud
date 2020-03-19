package cloud.distkv.common.response;

import com.distkv.common.exception.ErrorCodeEnum;

/**
 * The exception of distkv init connection;
 */
public class DistkvInitException extends RuntimeException {

  public DistkvInitException(String errorMessage) {
    super(errorMessage);
  }

  public DistkvInitException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

}
