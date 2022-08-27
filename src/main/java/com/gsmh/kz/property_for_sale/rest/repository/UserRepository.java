package com.gsmh.kz.property_for_sale.rest.repository;

import com.gsmh.kz.property_for_sale.rest.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}