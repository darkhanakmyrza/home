package com.gsmh.kz.home.repository;

import com.gsmh.kz.home.model.entity.Ad;
import com.gsmh.kz.home.model.enumers.AdModeratorStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
  List<Ad> findByCreatedBy(Long id);

  @Query(value = "select * from ads order by created_date desc offset :offset limit :limit", nativeQuery = true)
  List<Ad> filterAd(Integer limit, Integer offset);

  @Query(value = "select count(id) from ads", nativeQuery = true)
  Integer filterAdCount();

  @Query(value = "select * from ads where created_by=:userId and moderator_status=:moderatorStatusOrder order by created_date desc", nativeQuery = true)
  List<Ad> getAdsByUserIdAndStatus(Long userId, int moderatorStatusOrder);

  List<Ad> findByModeratorStatus(AdModeratorStatusEnum moderatorStatus);
}
