package com.gsmh.kz.property_for_sale.rest.service;

import com.gsmh.kz.property_for_sale.rest.model.dto.AdsDto;
import com.gsmh.kz.property_for_sale.rest.model.entity.Ad;
import com.gsmh.kz.property_for_sale.rest.model.entity.User;
import com.gsmh.kz.property_for_sale.rest.repository.AdRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;

    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public Ad saveAd(AdsDto adsDto) {
        User user = userService.getUser(adsDto.getUserId());
        Ad ads = new Ad(
                adsDto.getDescription(),
                adsDto.getRoomsCount(),
                adsDto.getHouseNumber(),
                adsDto.getFloor(),
                adsDto.getFloorsCount(),
                adsDto.getConstructionYear(),
                adsDto.getPrice(),
                user);
        return adRepository.save(ads);
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
