package com.cloud.dto;

import lombok.Data;

@Data
public class TokenDTO {

  private String token;
  private String refresh_token;
}
