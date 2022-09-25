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
  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User saveUser(UserDto userDto) {
    return userRepository.save(userDto.toEntity());
  }

  @Override
  public User updateUser(UserDto userDto) {
    return userRepository.save(userDto.toEntity());
  }

  @Override
  public User getUser(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return user;
  }

  @Override
  public void deleteUser(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    userRepository.delete(user);
  }

  @Override
  public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getPhone(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication, getByPhone(loginRequest.getPhone()).getId());

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.id(),
        userDetails.username(),
        userDetails.email(),
        roles));
  }

  @Override
  public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {

    if (signUpRequest.getCode() == null || verificationCodeRepository.getByPhoneAndCode(signUpRequest.getPhone(), signUpRequest.getCode()) == null){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "неправильный код");
    }

    if (userRepository.existsByUsername(signUpRequest.getPhone())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NAME_ALREADY_TAKEN);
    }


    // Create new user's account
    User user = new User();
    user.setPhone(signUpRequest.getPhone());
    user.setPassword(encoder.encode(signUpRequest.getPassword()));

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

  public Response sendVerificationCode(String phone){
    if (verificationCodeRepository.alreadySend(phone) != null){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "СМС уже отправлен");
    }
    VerificationCode verificationCode = new VerificationCode();
    verificationCode.setPhone(phone);
    verificationCode.setCode(verificationCodeService.getVerificationCodeRandomString());
    verificationCodeRepository.save(verificationCode);
    smsService.sendSmsVerification(phone, verificationCode.getCode() + " код подтверждения GSMH");
    return new Response(null, "SUCCESS", "СМС успешнело отправлен");
  }

}
