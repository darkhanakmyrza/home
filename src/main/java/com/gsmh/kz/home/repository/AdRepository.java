package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Integer> {
}
