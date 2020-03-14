package com.cloud.common.response;

import com.cloud.common.constants.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public R handHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    return new R(Msg.HTTP_REQUEST_ERROR, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public R handUnkonwException(Exception ex) {
    log.error("Unknown error  {} ", ex.toString());
    return new R(Msg.ERROR, ex.getMessage());
  }
}
