package cloud.distkv.dto;

import lombok.Data;

@Data
public class TokenDTO {

  private String token;
  private String refresh_token;
}
