package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}