package com.gsmh.kz.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MessageBoxRepository extends JpaRepository<MessageBox, Long> {

    @Query(value = "select * from messages_box where (from_user_id = :fromUserId and to_user_id = :toUserId) or (from_user_id = :toUserId and to_user_id = :fromUserId)", nativeQuery = true)
    public MessageBox getMessageBoxByUsers(Long fromUserId, Long toUserId);
}
