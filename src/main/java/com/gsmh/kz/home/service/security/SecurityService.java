package com.gsmh.kz.home.service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.gsmh.kz.home.config.security.SpringSecurityUser;
import com.gsmh.kz.home.config.security.jwt.JwtUtils;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService {

  private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

  public Long getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ((UserDetailsImpl) authentication.getPrincipal()).getId();
  }
}

