package com.gsmh.kz.home.controller;


import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.dto.AdsResponse;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.service.AdService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
@AllArgsConstructor
public class AdsController {
  private final AdService adService;

  @GetMapping
  public List<Ad> shawAllAds() {
    List<Ad> allAds = adService.getAllAds();
    return allAds;
  }

  @GetMapping("/{id}")
  public Ad getAd(@PathVariable Long id) {
    Ad ad = adService.getAd(id);
    return ad;
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public Ad addNewAd(@RequestBody AdsDto adsDto) {
    return adService.saveAd(adsDto);
  }

  @GetMapping("/findByUserId/{id}")
  public List<Ad> findByUserId(@PathVariable Long id) {
    return adService.getAdsByUser(id);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("isAuthenticated()")
  public String deleteAds(@PathVariable Long id) {
    adService.deleteAd(id);
    return "ads removed";
  }

  @PostMapping("/filter/{limit}/{offset}")
  public AdsResponse filterAds(@PathVariable Integer limit, @PathVariable Integer offset) {
    return adService.filterAds(limit, offset);
  }

  @GetMapping("/allModeratingAds")
  public List<Ad> shawAllModeratingAds() {
    List<Ad> allModeratingAds = adService.getAllModeratingAds();
    return allModeratingAds;
  }
}
