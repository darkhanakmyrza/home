package com.gsmh.kz.home.config.security;

import com.gsmh.kz.home.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpringSecurityUser  {
  private String id;
  private String username;
  private String email;
  private String password;
  private Collection<GrantedAuthority> authorities;
}
