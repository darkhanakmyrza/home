package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.dto.AdsResponse;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;

import java.util.List;

public interface AdService {
  List<Ad> getAllAds();

  Ad saveAd(AdsDto adsDto);

  Ad getAd(Long id);

  void deleteAd(Long id);

  List<Ad> getAdsByUser(Long userId);

  List<Ad> getMyAds();

  AdsResponse filterAds(Integer limit, Integer offset);

  List<Ad> getAllModeratingAds();

  void updateModeratorStatus(Long id, AdModeratorStatusEnum moderatorStatus);

  List<Ad> getAllMyAdsByStatus(AdModeratorStatusEnum moderatorStatus);
}
