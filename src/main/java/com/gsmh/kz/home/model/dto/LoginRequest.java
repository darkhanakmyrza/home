package com.gsmh.kz.home.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
  private String phone;
  private String password;
}
