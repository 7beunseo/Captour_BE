package com.mobileapp.Captour_BE.controller;

import com.mobileapp.Captour_BE.dto.FollowDTO;
import com.mobileapp.Captour_BE.dto.GetStatisticDTO;
import com.mobileapp.Captour_BE.dto.ResponseDTO;
import com.mobileapp.Captour_BE.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/captour")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    // 팔로우 관계 설정
    @GetMapping("/create-follow")
    public ResponseEntity<ResponseDTO<FollowDTO>> createFollow(
            @RequestParam String follower,
            @RequestParam String following
    ) {
        ResponseDTO<FollowDTO> response = followService.createFollow(follower, following);
        return ResponseEntity.ok().body(response);
    }

    // 팔로우 조회
    @GetMapping("/read-following")
    public ResponseEntity<ResponseDTO<FollowDTO>> readFollowing(@RequestParam String follower) {
        ResponseDTO<FollowDTO> response = followService.readFollowing(follower);
        return ResponseEntity.ok().body(response);
    }

    // 팔로워 조회
    @GetMapping("/read-follower")
    public ResponseEntity<ResponseDTO<FollowDTO>> readFollower(@RequestParam String following) {
        ResponseDTO<FollowDTO> response = followService.readFollower(following);
        return ResponseEntity.ok().body(response);
    }

    // 팔로우 상태인지 확인
    @GetMapping("/get-follow-status")
    public ResponseEntity<ResponseDTO<FollowDTO>> getFollowStatus(
            @RequestParam String follower,
            @RequestParam String following
    ) {
        ResponseDTO<FollowDTO> response = followService.getFollowStatus(follower, following);
        return ResponseEntity.ok().body(response);
    }
    // 팔로우 삭제
    @GetMapping("/delete-follow")
    public ResponseEntity<ResponseDTO<FollowDTO>> deleteFollow(
            @RequestParam String follower,
            @RequestParam String following
    ) {
        ResponseDTO<FollowDTO> response = followService.deleteFollow(follower, following);
        return ResponseEntity.ok().body(response);
    }

    // 한 주간 팔로워 통계
    @GetMapping("/week-statistics")
    public ResponseEntity<ResponseDTO<GetStatisticDTO>> weekStatistics(@RequestParam String following) {
        ResponseDTO<GetStatisticDTO> response = followService.weekStatistics(following);
        return ResponseEntity.ok().body(response);
    }

    // 월간 팔로워 통계
    @GetMapping("/month-statistics")
    public ResponseEntity<ResponseDTO<GetStatisticDTO>> monthStatistics(@RequestParam String following) {
        ResponseDTO<GetStatisticDTO> response = followService.monthStatistics(following);
        return ResponseEntity.ok().body(response);
    }
}
