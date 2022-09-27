package com.gsmh.kz.home.service;

import com.gsmh.kz.home.config.security.jwt.JwtUtils;
import com.gsmh.kz.home.model.dto.*;
import com.gsmh.kz.home.model.entity.Role;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.model.entity.VerificationCode;
import com.gsmh.kz.home.model.enumers.ERole;
import com.gsmh.kz.home.repository.RoleRepository;
import com.gsmh.kz.home.repository.UserRepository;
import com.gsmh.kz.home.repository.VerificationCodeRepository;
import com.gsmh.kz.home.service.security.SecurityService;
import com.gsmh.kz.home.service.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.gsmh.kz.home.constants.ServiceConstants.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private VerificationCodeRepository verificationCodeRepository;
    private VerificationCodeService verificationCodeService;
    private SmsService smsService;
    private SecurityService securityService;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return user;
    }


    @Override
    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
        String jwt = jwtUtils.generateJwtToken(authentication, getByPhone(loginRequest.getPhone()).getId(), roles);

        return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.id(),
            userDetails.username(),
            userDetails.email(),
            roles));
    }

    @Override
    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (signUpRequest.getPassword().length() < 6)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Пароль должен быть не менее 6 символов");

        if (signUpRequest.getCode() == null || verificationCodeRepository.getByPhoneAndCode(signUpRequest.getPhone(), signUpRequest.getCode()) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "неправильный код");
        }

        if (userRepository.existsByUsername(signUpRequest.getPhone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NAME_ALREADY_TAKEN);
        }


        // Create new user's account
        User user = new User();
        user.setPhone(signUpRequest.getPhone());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setActive(true);

        Set<Role> roles = new HashSet<>();
        Role clientRole = roleRepository.findByName(ERole.ROLE_CLIENT
        ).orElseThrow(() -> new RuntimeException(ROLE_NOT_FOUND));
        roles.add(clientRole);
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(USER_REGISTERED_SUCCESSFULLY));
    }

    @Override
    public User getByPhone(String phone) {
        User user = userRepository.findByUsername(phone)
            .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_WITH_NAME + phone));
        return user;
    }

    @Override
    public Boolean isUserExists(String phone) {
        return userRepository.existsByUsername(phone);
    }

    public Response sendVerificationCode(String phone) {
        if (verificationCodeRepository.alreadySend(phone) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "СМС уже отправлено");
        }
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setPhone(phone);
        verificationCode.setCode(verificationCodeService.getVerificationCodeRandomString());
        verificationCodeRepository.save(verificationCode);
        smsService.sendSmsVerification(phone, verificationCode.getCode() + " код подтверждения GSMH");
        return new Response(null, "SUCCESS", "СМС успешно отправлено");
    }

    public UserDto getProfile() {
        Long currentUserId = securityService.getCurrentUserId();
        User currentUser = getUser(currentUserId);
        return currentUser.getUserDto();
    }

    public UserDto updateProfile(UserDto userDto) {
        Long currentUserId = securityService.getCurrentUserId();
        User currentUser = getUser(currentUserId);
        if (userDto.getEmail() != null && !userDto.getEmail().isEmpty() && !userDto.getEmail().isBlank() && !userDto.getEmail().equals(currentUser.getEmail())) {
            if (userRepository.existsByEmail(userDto.getEmail()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Эл. адрес уже существует");
            currentUser.setEmail(userDto.getEmail());
        }
        if (userDto.getName() != null && !userDto.getName().isEmpty() && !userDto.getName().isBlank() && !userDto.getName().equals(currentUser.getName())) {
            currentUser.setName(userDto.getName());
        }

        if (userDto.getAvatarUrl() != null && !userDto.getAvatarUrl().isEmpty() && !userDto.getAvatarUrl().isBlank() && !userDto.getAvatarUrl().equals(currentUser.getAvatarUrl())) {
            currentUser.setAvatarUrl(userDto.getAvatarUrl());
        }

        if (userDto.getCompany() != null && userDto.getCompany().length() != 0 && !userDto.getCompany().equals(currentUser.getCompany())) {
            currentUser.setCompany(userDto.getCompany());
        }

        if (userDto.getCity() != null && userDto.getCity().length() != 0 && !userDto.getCity().equals(currentUser.getCity())) {
            currentUser.setCity(userDto.getCity());
        }
//        if (!userDto.getPhone().isEmpty() && !userDto.getPhone().isBlank() && userDto.getPhone() != null && !userDto.getPhone().equals(currentUser.getPhone())) {
//            currentUser.setPhone(userDto.getPhone());
//        }
        return userRepository.save(currentUser).getUserDto();
    }
}
