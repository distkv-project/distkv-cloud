package com.cloud.dcloud.api;

import com.cloud.dcloud.common.constants.Msg;
import com.cloud.dcloud.common.response.R;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseController {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
  }

  public R ok() {
    return new R(Msg.OK);
  }

  public R ok(Object object) {
    return new R(Msg.OK, object);
  }

  public R ok(String message) {
    return new R(Msg.OK, message);
  }

  public R error() {
    return new R(Msg.ERROR);
  }

  public R error(Msg msg) {
    return new R(msg);
  }

  public R error(Msg msg, String message) {
    return new R(msg, message);
  }

}
