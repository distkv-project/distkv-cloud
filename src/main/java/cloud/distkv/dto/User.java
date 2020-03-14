package cloud.distkv.dto;

import lombok.Data;

@Data
public class User {
  //TODO （senyer） add validate check.
  private String username;
  private String password;

}
