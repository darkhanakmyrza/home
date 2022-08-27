package com.gsmh.kz.property_for_sale.rest.service;

import com.gsmh.kz.property_for_sale.rest.model.dto.AdsDto;
import com.gsmh.kz.property_for_sale.rest.model.entity.Ad;

import java.util.List;

public interface AdService {

  List<Ad> getAllAds();

  Ad saveAd(AdsDto adsDto);

  Ad getAd(int id);

  void deleteAd(int id);
}
