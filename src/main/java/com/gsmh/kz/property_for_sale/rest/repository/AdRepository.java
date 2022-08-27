package com.gsmh.kz.property_for_sale.rest.repository;

import com.gsmh.kz.property_for_sale.rest.model.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Integer> {
}
