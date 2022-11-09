package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.MessageBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MessageBoxRepository extends JpaRepository<MessageBox, Long> {

    @Query(value = "select * from messages_box where ads_id = :adsId and ((from_user_id = :fromUserId and to_user_id = :toUserId) or " +
        "(from_user_id = :toUserId and to_user_id = :fromUserId))", nativeQuery = true)
    public MessageBox findMessageBoxIfExits(Long fromUserId, Long toUserId, Long adsId);

    @Query(value = "select * from messages_box where from_user_id = :userId or to_user_id = :userId", nativeQuery = true)
    public List<MessageBox> findMessageBoxByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "delete from messages_box where ads_id =:adsId", nativeQuery = true)
    public void deleteAllByAdsId(Long adsId);

    @Transactional
    @Modifying
    @Query(value = "delete from messages_box where ads_id =:adsId and ((from_user_id = :fromUserId and to_user_id = :toUserId) or (from_user_id = :toUserId and to_user_id = :fromUserId))", nativeQuery = true)
    public void deleteAllByAdsIdAndUserId(Long adsId, Long fromUserId, Long toUserId);
}
