package com.gsmh.kz.property_for_sale.rest.service;

import com.gsmh.kz.property_for_sale.rest.model.entity.Ad;
import com.gsmh.kz.property_for_sale.rest.repository.AdRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {

  private final AdRepository adRepository;

  public AdServiceImpl(AdRepository adRepository) {
    this.adRepository = adRepository;
  }

  @Override
  public List<Ad> getAllAds() {
    return adRepository.findAll();
  }

  @Override
  public void saveAd(Ad ad) {
    adRepository.save(ad);
  }

  @Override
  public Ad getAd(int id) {
    Ad ad = null;
    Optional<Ad> optionalAd = adRepository.findById(id);
    if (optionalAd.isPresent()) {
      ad = optionalAd.get();
    }
    return ad;
  }

  @Override
  public void deleteAd(int id) {
    adRepository.deleteById(id);
  }
}
