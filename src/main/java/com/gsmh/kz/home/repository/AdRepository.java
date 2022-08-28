package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    public List<Ad> findByUserId(Long id);
}
