package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
