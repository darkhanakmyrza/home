package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.dto.AdsResponse;
import com.gsmh.kz.home.model.dto.AdsStatusDto;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import com.gsmh.kz.home.repository.AdRepository;
import com.gsmh.kz.home.service.mapper.AdsToDto;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static com.gsmh.kz.home.constants.ServiceConstants.ADS_NOT_FOUND;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final SecurityService securityService;
    private AdsToDto adsToDtoMapper;

    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public Ad saveAd(AdsDto adsDto) {
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
        if (!ad.getCreatedBy().equals(securityService.getCurrentUserId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        adRepository.deleteById(id);
    }

    @Override
    public List<Ad> getAdsByUser(Long userId) {
        return adRepository.findByCreatedBy(userId);
    }

    @Override
    public List<Ad> getMyAds() {
        Long userId = securityService.getCurrentUserId();
        return getAdsByUser(userId);
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

    public List<Ad> getAllMyAdsByStatus(AdModeratorStatusEnum moderatorStatus) {
        Long currentUserId = securityService.getCurrentUserId();
        return adRepository.getAdsByUserIdAndStatus(currentUserId, moderatorStatus.ordinal());
    }

    @Override
    public List<Ad> getAllAdsByStatus(AdModeratorStatusEnum moderatorStatus) {
        return adRepository.getAdsByStatus(moderatorStatus.ordinal());
    }

    public void moderateAds(AdsStatusDto adsStatusDto){
        Ad ad = getAd(adsStatusDto.getId());
        ad.setModeratorStatus(adsStatusDto.getStatus());
        ad.setModerateMsg(adsStatusDto.getModerateMsg());
        adRepository.save(ad);
    }

    @Override
    public List<Ad> searchByDescription(String description) {
        List<Ad> adList = adRepository.findAllWhereDescription(description);
        return adList;
    }

}
