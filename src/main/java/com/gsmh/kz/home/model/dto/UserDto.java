package com.gsmh.kz.home.model.dto;


import com.gsmh.kz.home.model.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
  private Long id;
  private String name;
  private String email;
  private String phone;

  public User toEntity() {
    User user = new User();
    user.setName(name);
    user.setPhone(phone);
    user.setEmail(email);
    user.setActive(true);
    return user;
  }
}
