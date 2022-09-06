package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "select * from messages where ((from_user_id = :fromUserId and to_user_id = :toUserId) or " +
            "(from_user_id = :toUserId and to_user_id = :fromUserId)) and ads_id = :adsId order by created_date desc", nativeQuery = true)
    public List<Message> getMessageBoxByUsers(Long fromUserId, Long toUserId, Long adsId);

}
