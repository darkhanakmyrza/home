package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    public List<Ad> findByUserId(Long id);


    @Query(value = "select * from ads order by created_date desc offset :offset limit :limit", nativeQuery = true)
    public List<Ad> filterAd(Integer limit, Integer offset);

    @Query(value = "select count(id) from ads", nativeQuery = true)
    public Integer filterAdCount();
}
