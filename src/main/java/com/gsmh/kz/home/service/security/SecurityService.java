package com.gsmh.kz.home.service.security;

import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.repository.UserRepository;
import com.gsmh.kz.home.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SecurityService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userPhone = authentication.getName();
        return userService.getByPhone(userPhone);
    }

}
