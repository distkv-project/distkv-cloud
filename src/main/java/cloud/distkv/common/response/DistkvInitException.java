package cloud.distkv.common.response;

/**
 * The exception of distkv init connection;
 */
public class DistkvInitException extends DistkvException {

  public DistkvInitException(String errorMessage) {
    super(errorMessage);
  }

  public DistkvInitException(String errorMessage, Exception e) {
    super(errorMessage, e);
  }

}
