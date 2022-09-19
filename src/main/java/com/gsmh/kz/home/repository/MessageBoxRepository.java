package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.MessageBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageBoxRepository extends JpaRepository<MessageBox, Long> {

  @Query(value = "select * from messages_box where (from_user_id = :fromUserId and to_user_id = :toUserId) or " +
      "(from_user_id = :toUserId and to_user_id = :fromUserId)", nativeQuery = true)
  public MessageBox findMessageBoxIfExits(Long fromUserId, Long toUserId);

  @Query(value = "select * from messages_box where from_user_id = :userId or to_user_id = :userId", nativeQuery = true)
  public List<MessageBox> findMessageBoxByUserId(Long userId);
}
