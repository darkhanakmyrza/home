package com.gsmh.kz.home.controller;

import com.gsmh.kz.home.model.dto.AdsStatusDto;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import com.gsmh.kz.home.service.AdService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
@CrossOrigin
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class RoleController {

    private final AdService adService;

    @PostMapping("/moderator/ads/{status}")
    public List<Ad> getAdsByStatus(@PathVariable AdModeratorStatusEnum status) {
        return adService.getAllAdsByStatus(status);
    }

    @PostMapping("/moderator")
    public ResponseEntity<Void> moderateAds(@RequestBody AdsStatusDto adsStatusDto) {
        adService.moderateAds(adsStatusDto);
        return ResponseEntity.ok().build();
    }


}
