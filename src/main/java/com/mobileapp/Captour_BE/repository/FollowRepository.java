package com.mobileapp.Captour_BE.repository;

import com.mobileapp.Captour_BE.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollower(String follower);
}
