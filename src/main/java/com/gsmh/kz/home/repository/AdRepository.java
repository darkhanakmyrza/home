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

    @Query(value = "select * from ads where moderator_status=:moderatorStatusOrder order by created_date desc", nativeQuery = true)
    List<Ad> getAdsByStatus(int moderatorStatusOrder);

    List<Ad> findByModeratorStatus(AdModeratorStatusEnum moderatorStatus);

    @Query(value = "select * from ads where description like %?1% and moderator_status=:2", nativeQuery = true)
    List<Ad> findAllWhereDescription(String description);
}
