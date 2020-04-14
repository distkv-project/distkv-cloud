package cloud.distkv.common.response;

/**
 * The exception of distkv client operation.
 */
public class DistkvException extends RuntimeException {

  public DistkvException(String errorMessage) {
    super(errorMessage);
  }

  public DistkvException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

}
