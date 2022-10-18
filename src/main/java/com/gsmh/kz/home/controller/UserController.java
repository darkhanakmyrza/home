package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.*;
import com.gsmh.kz.home.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;

    @PreAuthorize("isAnonymous()")
    @PostMapping("isUserExists/{phone}")
    public ResponseEntity<Boolean> isUserExists(@PathVariable String phone) {
        return ResponseEntity.ok(userService.isUserExists(phone));
    }

    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @PostMapping("sendVerificationCode/{phone}")
    public ResponseEntity<Response> sendVerificationCode(@PathVariable String phone) {
        return ResponseEntity.ok(userService.sendVerificationCode(phone));
    }

    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @PostMapping("/checkVerificationCode")
    public ResponseEntity<Boolean> checkVerificationCode(@RequestBody VerificationDto verificationDto) {
        return ResponseEntity.ok(userService.checkVerificationCode(verificationDto));
    }


    @PreAuthorize("isAnonymous() or isAuthenticated()")
    @PostMapping("/restoreUserPassword")
    public ResponseEntity<Response> restoreUserPassword(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.restorePassword(userDto));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return userService.registerUser(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return userService.authenticateUser(loginRequest);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    @PatchMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateProfile(userDto));
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<UserDto> getProfileByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getProfileByUserId(userId));
    }

}
