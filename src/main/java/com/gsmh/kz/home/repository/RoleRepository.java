package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.Role;
import com.gsmh.kz.home.model.enumers.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
