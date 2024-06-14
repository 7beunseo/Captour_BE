package com.mobileapp.Captour_BE.service;

import com.mobileapp.Captour_BE.dto.*;
import com.mobileapp.Captour_BE.entity.Follow;
import com.mobileapp.Captour_BE.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
                .createdDate(LocalDate.now())
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
    public ResponseDTO<FollowDTO> readFollowing(String follower) {
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
        List<Follow> follows = followRepository.findAllByFollowerAndFollowing(follower, following);
        followRepository.deleteAll(follows);

        return ResponseDTO.<FollowDTO>builder()
                .message("팔로우 삭제 완료")
                .data(null)
                .build();
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

    public ResponseDTO<FollowDTO> readFollower(String following) {
        List<Follow> follow = followRepository.findByFollowing(following);
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

    public ResponseDTO<GetStatisticDTO> weekStatistics(String following) {
        LocalDate endDate = LocalDate.now(); // 현재 날짜
        LocalDate startDate = endDate.minusDays(6); // 6일 전

        List<Follow> follows = followRepository.findAllByFollowingAndCreatedDateBetween(following, startDate, endDate);

        // 각 날짜별 팔로워 수 저장
        int[] followerCounts = new int[7];

        // 팔로우 데이터에서 각 날짜에 해당하는 팔로워 수 계산
        for (Follow follow : follows) {
            int daysAgo = (int) (endDate.toEpochDay() - follow.getCreatedDate().toEpochDay());
            if (daysAgo >= 0 && daysAgo < 7) {
                followerCounts[daysAgo]++;
            }
        }

        List<GetStatisticDTO> dtos = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            GetStatisticDTO dto = GetStatisticDTO.builder()
                    .day(7 - i) // 7일 전부터 오늘까지의 날짜를 의미
                    .followerNum(followerCounts[i])
                    .build();
            dtos.add(dto);
        }

        return ResponseDTO.<GetStatisticDTO>builder()
                .message("주간 통계 조회 완료")
                .data(dtos)
                .build();
    }
}
