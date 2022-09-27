package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.FavAds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavAdsRepository extends JpaRepository<FavAds, Long> {

    @Query(value = "select * from fav_ads where ads_id=:adsId and created_by=:userId limit 1", nativeQuery = true)
    FavAds getByUserIdAndAdsId(Long userId, Long adsId);

    @Query(value = "select * from fav_ads where created_by=:userId order by created_date desc", nativeQuery = true)
    public List<FavAds> getAllFavAdsByUserId(Long userId);
}
