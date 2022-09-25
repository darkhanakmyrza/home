package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.LoginRequest;
import com.gsmh.kz.home.model.dto.Response;
import com.gsmh.kz.home.model.dto.SignupRequest;
import com.gsmh.kz.home.model.dto.UserDto;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
  private final UserService userService;

  @PreAuthorize("isAnonymous()")
  @PostMapping("isUserExists/{phone}")
  public ResponseEntity<Boolean> isUserExists(@PathVariable String phone){
    return ResponseEntity.ok(userService.isUserExists(phone));
  }


  @PreAuthorize("isAnonymous() or isAuthenticated()")
  @PostMapping("sendVerificationCode/{phone}")
  public ResponseEntity<Response> sendVerificationCode(@PathVariable String phone){
    return ResponseEntity.ok(userService.sendVerificationCode(phone));
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return userService.registerUser(signUpRequest);
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    return userService.authenticateUser(loginRequest);
  }
}
