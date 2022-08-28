package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.entity.Ad;

import java.util.List;

public interface AdService {

  List<Ad> getAllAds();

  Ad saveAd(AdsDto adsDto);

  Ad getAd(Long id);

  void deleteAd(Long id);
}
