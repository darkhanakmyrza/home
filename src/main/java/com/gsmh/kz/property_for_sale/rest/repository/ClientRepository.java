package com.gsmh.kz.property_for_sale.rest.repository;

import com.gsmh.kz.property_for_sale.rest.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}