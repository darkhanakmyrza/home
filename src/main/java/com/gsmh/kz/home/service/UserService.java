package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.*;
import com.gsmh.kz.home.model.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User getUser(Long id);

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);

    User getByPhone(String phone);

    Boolean isUserExists(String phone);

    Response sendVerificationCode(String phone);

    Boolean checkVerificationCode(VerificationDto verificationDto);

    UserDto updateProfile(UserDto userDto);

    UserDto getProfile();

    UserDto getProfileByUserId(Long userId);

    Response restorePassword(UserDto userDto);
}
