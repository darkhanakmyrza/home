package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.UserDto;
import com.gsmh.kz.home.model.entity.User;

import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  User saveUser (UserDto userDto);

  User updateUser(UserDto userDto);

  User getUser(Long id);

  void deleteUser(Long id);
}
