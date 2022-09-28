package com.gsmh.kz.home.controller;


import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.dto.AdsResponse;
import com.gsmh.kz.home.model.dto.FavAdsRequestDto;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import com.gsmh.kz.home.service.AdService;
import com.gsmh.kz.home.service.FavAdsService;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
@CrossOrigin
@AllArgsConstructor
public class AdsController {
    private final AdService adService;
    private final SecurityService securityService;
    private final FavAdsService favAdsService;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/myAdsByStatus/{status}")
    public List<Ad> myAdsByStatus(@PathVariable AdModeratorStatusEnum status) {
        return adService.getAllMyAdsByStatus(status);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/favAds/addOrRemove")
    public AdsDto addOrRemoveFavAds(@RequestBody FavAdsRequestDto favAdsRequestDto) {
        return favAdsService.addOrRemoveFavAds(favAdsRequestDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/favAds/list")
    public List<AdsDto> getFavAdsDto(){
        return favAdsService.getFavAds();
    }

}
