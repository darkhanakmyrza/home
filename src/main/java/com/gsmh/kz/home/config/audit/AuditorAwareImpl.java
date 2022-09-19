package com.gsmh.kz.home.config.audit;

import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AuditorAwareImpl implements AuditorAware<Long> {

  private SecurityService securityService;

  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.of(securityService.getCurrentUserId());
  }
}
