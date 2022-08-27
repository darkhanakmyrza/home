package com.gsmh.kz.property_for_sale.rest.service;

import com.gsmh.kz.property_for_sale.rest.model.dto.UserDto;
import com.gsmh.kz.property_for_sale.rest.model.entity.User;

import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  User saveUser (UserDto userDto);

  User updateUser(UserDto userDto);

  User getUser(Long id);

  void deleteUser(Long id);
}
