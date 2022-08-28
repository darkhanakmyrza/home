package com.gsmh.kz.home.service;

import com.gsmh.kz.home.model.dto.AdsDto;
import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.entity.User;
import com.gsmh.kz.home.repository.AdRepository;
import com.gsmh.kz.home.service.security.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final UserService userService;
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
                user);
        return adRepository.save(ads);
    }

    @Override
    public Ad getAd(Long id) {
        Ad ad =  adRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ads not found "));
        if (!ad.getUser().equals(securityService.getCurrentUser())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return ad;
    }

    @Override
    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }

    @Override
    public List<Ad> getAdsByUser(Long userId) {
        return adRepository.findByUserId(userId);
    }

    @Override
    public List<Ad> getMyAds() {
        User user = securityService.getCurrentUser();
        return getAdsByUser(user.getId());
    }


}
