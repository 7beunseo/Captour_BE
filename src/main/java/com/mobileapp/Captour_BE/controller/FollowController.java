package com.mobileapp.Captour_BE.controller;

import com.mobileapp.Captour_BE.dto.FollowDTO;
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
    @GetMapping("/read-follow")
    public ResponseEntity<ResponseDTO<FollowDTO>> readFollow(@RequestParam String follower) {
        ResponseDTO<FollowDTO> response = followService.readFollow(follower);
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
}
