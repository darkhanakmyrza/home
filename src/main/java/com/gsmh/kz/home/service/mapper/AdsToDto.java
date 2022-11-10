package com.gsmh.kz.home.service.mapper;

import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.entity.Ad;
import org.springframework.stereotype.Service;

@Service
public class AdsToDto {

    public AdsDto adsToAdsDto(Ad ad){
        AdsDto adsDto = new AdsDto();
        adsDto.setId(ad.getId());
        adsDto.setDescription(ad.getDescription());
        adsDto.setRoomsCount(ad.getRoomsCount());
        adsDto.setHouseNumber(ad.getHouseNumber());
        adsDto.setFloor(ad.getFloor());
        adsDto.setFloorsCount(ad.getFloorsCount());
        adsDto.setConstructionYear(ad.getCreationYear());
        adsDto.setPrice(ad.getPrice());
        adsDto.setIsPledged(ad.getIsPledged());
        adsDto.setBalcony(ad.getBalcony());
        adsDto.setIsBalconyGlazed(ad.getIsBalconyGlazed());
        adsDto.setFurniture(ad.getFurniture());
        adsDto.setCeilingHeight(ad.getCeilingHeight());
        adsDto.setSafety(ad.getSafety());
        adsDto.setExchange(ad.getExchange());
        adsDto.setPayment(ad.getPayment());
        adsDto.setRassrochkaPrice(ad.getRassrochkaPrice());
        adsDto.setPropertyType(ad.getPropertyType());
        adsDto.setRegion(ad.getRegion());
        adsDto.setCity(ad.getCity());
        adsDto.setPhotos(ad.getPhotos());
        adsDto.setIsFavAds(false);
        adsDto.setModerateMsg(ad.getModerateMsg());
        adsDto.setCreatedBy(ad.getCreatedBy());
        return adsDto;
    }
}
