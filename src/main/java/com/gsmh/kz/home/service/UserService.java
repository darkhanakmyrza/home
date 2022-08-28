package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.LoginRequest;
import com.gsmh.kz.home.model.dto.SignupRequest;
import com.gsmh.kz.home.model.dto.UserDto;
import com.gsmh.kz.home.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  User saveUser (UserDto userDto);

  User updateUser(UserDto userDto);

  User getUser(Long id);

  void deleteUser(Long id);

  ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

  ResponseEntity<?> registerUser(SignupRequest signUpRequest);
}
