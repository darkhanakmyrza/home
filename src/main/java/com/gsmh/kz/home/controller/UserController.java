package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.LoginRequest;
import com.gsmh.kz.home.model.dto.SignupRequest;
import com.gsmh.kz.home.model.dto.UserDto;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest){
        return userService.registerUser(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }


    }
