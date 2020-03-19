package cloud.distkv.dto;

import lombok.Data;

@Data
// TODO improve this.
public class Conditions {


  //TODO (senyer) valid check

  //Request type for operation.
  private String type;

  //Key to be operated.
  private String key;

  private Object value;



}
