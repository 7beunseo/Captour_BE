package com.mobileapp.Captour_BE.service;

import com.mobileapp.Captour_BE.dto.*;
import com.mobileapp.Captour_BE.entity.Follow;
import com.mobileapp.Captour_BE.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {
    private final FollowRepository followRepository;

    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    // 팔로우 생성
    public ResponseDTO<FollowDTO> createFollow(String follower, String following) {
        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(follow);

        FollowDTO dto = FollowDTO.builder()
                .follower(follow.getFollower())
                .following(follow.getFollowing())
                .build();

        List<FollowDTO> followDTOs = new ArrayList<>();
        followDTOs.add(dto);

        return ResponseDTO.<FollowDTO>builder()
                .message("팔로우 생성 완료")
                .data(followDTOs)
                .build();
    }

    // 팔로우 조회
    public ResponseDTO<FollowDTO> readFollow(String follower) {
        List<Follow> follow = followRepository.findAllByFollower(follower);

        List<FollowDTO> followDTOs = new ArrayList<>();

        follow.forEach(f -> {
            FollowDTO dto = FollowDTO.builder()
                    .follower(f.getFollower())
                    .following(f.getFollowing())
                    .build();
            followDTOs.add(dto);
        });

        return ResponseDTO.<FollowDTO>builder()
                .message("팔로우 조회 완료")
                .data(followDTOs)
                .build();

    }

    // 팔로우 삭제
    public ResponseDTO<FollowDTO> deleteFollow(String follower, String following) {
        return null;
    }

    public ResponseDTO<FollowDTO> getFollowStatus(String follower, String following) {
        List<Follow> follow = followRepository.findAllByFollowerAndFollowing(follower, following);

        if(follow.isEmpty()) {
            return ResponseDTO.<FollowDTO>builder()
                    .message("false")
                    .data(null)
                    .build();
        }
        return ResponseDTO.<FollowDTO>builder()
                .message("true")
                .data(null)
                .build();
    }
}
