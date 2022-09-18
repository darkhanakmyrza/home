package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.dto.AdsResponse;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import com.gsmh.kz.home.repository.AdRepository;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

import static com.gsmh.kz.home.constants.ServiceConstants.ADS_NOT_FOUND;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {
  private final AdRepository adRepository;
  private final SecurityService securityService;

  @Override
  public List<Ad> getAllAds() {
    return adRepository.findAll();
  }

  @Override
  public Ad saveAd(AdsDto adsDto) {
    User user = securityService.getCurrentUser();
    Ad ads = new Ad(
        adsDto.getDescription(),
        adsDto.getRoomsCount(),
        adsDto.getHouseNumber(),
        adsDto.getFloor(),
        adsDto.getFloorsCount(),
        adsDto.getConstructionYear(),
        adsDto.getPrice(),
        adsDto.getIsPledged(),
        adsDto.getBalcony(),
        adsDto.getIsBalconyGlazed(),
        adsDto.getFurniture(),
        adsDto.getCeilingHeight(),
        adsDto.getSafety(),
        adsDto.getExchange(),
        adsDto.getPayment(),
        adsDto.getRassrochkaPrice(),
        adsDto.getPropertyType(),
        adsDto.getRegion(),
        adsDto.getCity(),
        AdModeratorStatusEnum.MODERATE,
        adsDto.getPhotos()
    );
    return adRepository.save(ads);
  }

  @Override
  public Ad getAd(Long id) {
    Ad ad = adRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ADS_NOT_FOUND));
    return ad;
  }

  @Override
  public void deleteAd(Long id) {
    Ad ad = adRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ADS_NOT_FOUND));
//    if (!ad.getUser().equals(securityService.getCurrentUser()))
//      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    adRepository.deleteById(id);
  }

  @Override
  public List<Ad> getAdsByUser(Long userId) {
    return adRepository.findByCreatedBy(userId);
  }

  @Override
  public List<Ad> getMyAds() {
    User user = securityService.getCurrentUser();
    return getAdsByUser(user.getId());
  }

  public AdsResponse filterAds(Integer limit, Integer offset) {
    List<Ad> adList = adRepository.filterAd(limit, offset);
    Integer count = adRepository.filterAdCount();
    return new AdsResponse(count, adList);
  }

  @Override
  public List<Ad> getAllModeratingAds() {
    List<Ad> allModeratingAds = adRepository.findByModeratorStatus(AdModeratorStatusEnum.MODERATE);
    return allModeratingAds;
  }

  @Override
  public void updateModeratorStatus(Long id, AdModeratorStatusEnum moderatorStatus) {
    Ad ad = getAd(id);
    ad.setModeratorStatus(moderatorStatus);
    adRepository.save(ad);
  }
}
