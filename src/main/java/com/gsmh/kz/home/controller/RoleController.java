package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import com.gsmh.kz.home.service.AdService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
@AllArgsConstructor
@CrossOrigin
public class RoleController {

  private final AdService adService;

  @PutMapping("/{id}/{moderatorStatus}")
  public String updateModeratorStatus(@PathVariable Long id,
                                      @PathVariable AdModeratorStatusEnum moderatorStatus) {
    adService.updateModeratorStatus(id, moderatorStatus);
    return "Ad moderator status updated!";
  }
}
