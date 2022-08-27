package com.gsmh.kz.property_for_sale.rest.controller;


import com.gsmh.kz.property_for_sale.rest.model.dto.AdsDto;
import com.gsmh.kz.property_for_sale.rest.model.entity.Ad;
import com.gsmh.kz.property_for_sale.rest.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdsController {

    @Autowired
    public AdService adService;

    @GetMapping
    public List<Ad> shawAllAds() {
        List<Ad> allAds = adService.getAllAds();
        return allAds;
    }

    @GetMapping("/{id}")
    public Ad getAd(@PathVariable int id) {
        Ad ad = adService.getAd(id);
        return ad;
    }

    @PostMapping
    public Ad addNewAd(@RequestBody AdsDto adsDto) {
        return adService.saveAd(adsDto);
    }

}
