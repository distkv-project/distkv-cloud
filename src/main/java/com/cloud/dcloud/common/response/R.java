package com.cloud.dcloud.common.response;

import com.cloud.dcloud.common.constants.Msg;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.Data;

@Data
public class R implements Serializable {

  private static final long serialVersionUID = -3477609529314194539L;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object data;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String msg;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Integer code;

  public R(Msg msg, Object data) {
    this.msg = msg.getDesc();
    this.code = msg.getStatusCode();
    this.data = data;
  }

  public R(Msg msg) {
    super();
    this.code = msg.getStatusCode();
    this.msg = msg.getDesc();
  }
}
