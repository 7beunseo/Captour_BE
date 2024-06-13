package com.mobileapp.Captour_BE.controller;

import com.mobileapp.Captour_BE.dto.CreateFollowDTO;
import com.mobileapp.Captour_BE.dto.DeleteFollowDTO;
import com.mobileapp.Captour_BE.dto.FollowDTO;
import com.mobileapp.Captour_BE.dto.GetFollowDTO;
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
    public ResponseEntity<CreateFollowDTO> createFollow(
            @RequestParam String follower,
            @RequestParam String following
    ) {
        CreateFollowDTO response = followService.createFollow(follower, following);
        return ResponseEntity.ok().body(response);
    }

    // 팔로우 조회
    @GetMapping("/get-follow")
    public ResponseEntity<GetFollowDTO<FollowDTO>> readFollow(@RequestParam String follower) {
        GetFollowDTO<FollowDTO> response = followService.readFollow(follower);
        return ResponseEntity.ok().body(response);
    }

    // 팔로우 삭제
    @GetMapping("/delete-follow")
    public ResponseEntity<DeleteFollowDTO> deleteFollow(
            @RequestParam String follower,
            @RequestParam String following
    ) {
        DeleteFollowDTO response = followService.deleteFollow(follower, following);
        return ResponseEntity.ok().body(response);
    }
}
