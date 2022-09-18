package com.gsmh.kz.home.config.security.jwt;

import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.service.UserService;
import com.gsmh.kz.home.service.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

import static com.gsmh.kz.home.constants.JWTConstants.*;

@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${home.app.jwtSecret}")
  private String jwtSecret;

  @Value("${home.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  public String generateJwtToken(Authentication authentication, Long id) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    HashMap<String, Object> map = new HashMap<>();
    map.put("id", id);
    map.put("username", userPrincipal.getUsername());

    return Jwts.builder()
        .setClaims(map)
        .setSubject((userPrincipal.username()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error(INVALID_JWT_SIGNATURE, e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error(INVALID_JWT_TOKEN, e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error(JWT_TOKEN_IS_EXPIRED, e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error(JWT_TOKEN_IS_UNSUPPORTED, e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error(JWT_CLAIMS_IS_EMPTY, e.getMessage());
    }
    return false;
  }
}
