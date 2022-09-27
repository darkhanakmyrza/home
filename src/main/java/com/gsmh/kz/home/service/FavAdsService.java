package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.dto.FavAdsRequestDto;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.entity.FavAds;
import com.gsmh.kz.home.repository.FavAdsRepository;
import com.gsmh.kz.home.service.mapper.AdsToDto;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavAdsService {
    private FavAdsRepository favAdsRepository;
    private SecurityService securityService;
    private AdService adService;
    private AdsToDto adsToDto;

    public AdsDto addOrRemoveFavAds(FavAdsRequestDto favAdsRequestDto) {
        Long currentUserId = securityService.getCurrentUserId();
        Ad ad = adService.getAd(favAdsRequestDto.getAdsId());
        AdsDto adsDto = adsToDto.adsToAdsDto(ad);
        FavAds favAds = favAdsRepository.getByUserIdAndAdsId(currentUserId, favAdsRequestDto.getAdsId());
        if (favAds != null) {
            favAdsRepository.delete(favAds);
            adsDto.setIsFavAds(false);
        } else {
            FavAds newFavAds = new FavAds();
            newFavAds.setAdsId(ad.getId());
            favAdsRepository.save(newFavAds);
            adsDto.setIsFavAds(true);
        }
        return adsDto;
    }

    public List<AdsDto> getFavAds() {
        Long currentUserId = securityService.getCurrentUserId();
        List<FavAds> favAdsList = favAdsRepository.getAllFavAdsByUserId(currentUserId);
        List<AdsDto> adsDtoList = favAdsList.stream().map(it -> adsToDto.adsToAdsDto(adService.getAd(it.getAdsId()))).collect(Collectors.toList());
        return adsDtoList;
    }
}
