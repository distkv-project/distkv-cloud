package cloud.distkv.common.response;

import cloud.distkv.common.constants.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public R handHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    return new R(Msg.HTTP_REQUEST_ERROR, ex.getMessage());
  }

  @ExceptionHandler(DistkvException.class)
  public R handDistkvException(DistkvException ex) {
    log.error("Distkv operation Error  {} ", ex.toString());
    return new R(Msg.DISTKV_ERROR, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public R handUnkonwException(Exception ex) {
    log.error("Unknown error  {} ", ex.toString());
    return new R(Msg.ERROR, ex.getMessage());
  }
}
