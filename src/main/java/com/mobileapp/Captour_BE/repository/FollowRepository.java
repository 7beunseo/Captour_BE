package com.mobileapp.Captour_BE.repository;

import com.mobileapp.Captour_BE.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollower(String follower);
    List<Follow> findAllByFollowerAndFollowing(String follower, String following);
    List<Follow> findByFollowing(String following);

    // 7일의 통계
    @Query("SELECT f FROM Follow f WHERE f.following = :following AND f.createdDate >= :startDate AND f.createdDate <= :endDate")
    List<Follow> findAllByFollowingAndCreatedDateBetween(String following, LocalDate startDate, LocalDate endDate);
}
