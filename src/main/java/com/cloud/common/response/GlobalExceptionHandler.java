package com.cloud.common.response;

import com.cloud.common.constants.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public R handHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
    log.error("Distkv Cloud Unsupported Request : {}", ex.toString());
    return new R(Msg.HTTP_REQUEST_ERROR, ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public R handUnkonwException(Exception ex) {
    log.error("Inner error  {} ", ex.toString());
    return new R(Msg.ERROR, ex.getMessage());
  }
}