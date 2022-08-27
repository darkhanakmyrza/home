package com.gsmh.kz.property_for_sale.rest.service;

import com.gsmh.kz.property_for_sale.rest.entity.User;

import java.util.List;

public interface UserService {

  List<User> getAllUsers();

  void saveUser (User user);

  void updateUser(User user);

  User getClient(Long id);

  void deleteClient(Long id);
}
