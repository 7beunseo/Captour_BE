package com.mobileapp.Captour_BE.service;

import com.mobileapp.Captour_BE.dto.CreateFollowDTO;
import com.mobileapp.Captour_BE.dto.DeleteFollowDTO;
import com.mobileapp.Captour_BE.dto.FollowDTO;
import com.mobileapp.Captour_BE.dto.GetFollowDTO;
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
    public CreateFollowDTO createFollow(String follower, String following) {
        Follow follow = Follow.builder()
                .follower(follower)
                .following(following)
                .build();

        followRepository.save(follow);

        return CreateFollowDTO.builder()
                .message("팔로우 생성 완료")
                .build();
    }

    // 팔로우 조회
    public GetFollowDTO<FollowDTO> readFollow(String follower) {
        List<Follow> follow = followRepository.findAllByFollower(follower);

        List<FollowDTO> followDTOs = new ArrayList<>();

        follow.forEach(f -> {
            FollowDTO dto = FollowDTO.builder()
                    .follower(f.getFollower())
                    .following(f.getFollowing())
                    .build();
            followDTOs.add(dto);
        });

        return GetFollowDTO.<FollowDTO>builder()
                .message("팔로우 조회 완료")
                .data(followDTOs)
                .build();

    }

    // 팔로우 삭제
    public DeleteFollowDTO deleteFollow(String follower, String following) {
        return null;
    }
}
