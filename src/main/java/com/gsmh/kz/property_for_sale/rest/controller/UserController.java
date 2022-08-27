package com.gsmh.kz.property_for_sale.rest.controller;

import com.gsmh.kz.property_for_sale.rest.model.dto.UserDto;
import com.gsmh.kz.property_for_sale.rest.model.entity.User;
import com.gsmh.kz.property_for_sale.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/list")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public User addNewUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping
    public User updateUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User with id " + id + " was deleted";
    }


}
