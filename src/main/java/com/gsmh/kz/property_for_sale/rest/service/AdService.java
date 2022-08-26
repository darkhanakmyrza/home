package com.gsmh.kz.property_for_sale.rest.service;

import com.gsmh.kz.property_for_sale.rest.entity.Ad;

import java.util.List;

public interface AdService {

  List<Ad> getAllAds();

  void saveAd(Ad ad);

  Ad getAd(int id);

  void deleteAd(int id);
}
