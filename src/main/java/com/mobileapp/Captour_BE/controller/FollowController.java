package com.mobileapp.Captour_BE.controller;

import com.mobileapp.Captour_BE.dto.FollowDTO;
import com.mobileapp.Captour_BE.dto.GetStatisticDTO;
import com.mobileapp.Captour_BE.dto.ResponseDTO;
import com.mobileapp.Captour_BE.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/captour")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    // 팔로우 관계 설정 - GET
    /*
    @GetMapping("/create-follow")
    public ResponseEntity<ResponseDTO<FollowDTO>> createFollow(
            @RequestParam String follower,
            @RequestParam String following
    ) {
        ResponseDTO<FollowDTO> response = followService.createFollow(follower, following);
        return ResponseEntity.ok().body(response);
    }
     */

    // 팔로우 관계 설정 - POST
    @PostMapping("/create-follow")
    @Operation(summary = "follow 생성")
    public ResponseEntity<ResponseDTO<FollowDTO>> createFollow(@RequestBody FollowDTO followDTO){
        ResponseDTO<FollowDTO> response = followService.createFollow(followDTO.getFollower(), followDTO.getFollowing());
        return ResponseEntity.ok().body(response);
    }

    // 팔로우 조회
    @GetMapping("/read-following")
    @Operation(summary = "팔로잉 조회")
    public ResponseEntity<ResponseDTO<FollowDTO>> readFollowing(@RequestParam String follower) {
        ResponseDTO<FollowDTO> response = followService.readFollowing(follower);
        return ResponseEntity.ok().body(response);
    }

    // 팔로워 조회
    @GetMapping("/read-follower")
    @Operation(summary = "팔로워 조회")
    public ResponseEntity<ResponseDTO<FollowDTO>> readFollower(@RequestParam String following) {
        ResponseDTO<FollowDTO> response = followService.readFollower(following);
        return ResponseEntity.ok().body(response);
    }

    // 팔로우 상태인지 확인
    @GetMapping("/get-follow-status")
    @Operation(summary = "팔로잉 상태 조회")
    public ResponseEntity<ResponseDTO<FollowDTO>> getFollowStatus(
            @RequestParam String follower,
            @RequestParam String following
    ) {
        ResponseDTO<FollowDTO> response = followService.getFollowStatus(follower, following);
        return ResponseEntity.ok().body(response);
    }
    // 팔로우 삭제
    @DeleteMapping("/delete-follow")
    @Operation(summary = "팔로우 삭제")
    public ResponseEntity<ResponseDTO<FollowDTO>> deleteFollow(
            @RequestParam String follower,
            @RequestParam String following
    ) {
        ResponseDTO<FollowDTO> response = followService.deleteFollow(follower, following);
        return ResponseEntity.ok().body(response);
    }

    // 한 주간 팔로워 통계
    @GetMapping("/week-statistics")
    @Operation(summary = "한 주간 팔로워 통계")
    public ResponseEntity<ResponseDTO<GetStatisticDTO>> weekStatistics(@RequestParam String following) {
        ResponseDTO<GetStatisticDTO> response = followService.weekStatistics(following);
        return ResponseEntity.ok().body(response);
    }

    // 월간 팔로워 통계
    @GetMapping("/month-statistics")
    @Operation(summary = "1년 간 팔로워 통계")
    public ResponseEntity<ResponseDTO<GetStatisticDTO>> monthStatistics(@RequestParam String following) {
        ResponseDTO<GetStatisticDTO> response = followService.monthStatistics(following);
        return ResponseEntity.ok().body(response);
    }

    // 팔로우 상태인지 확인
    @GetMapping("/test")
    @Operation(summary = "서버 테스트")
    public ResponseEntity<ResponseDTO<String>> testController() {
        ResponseDTO<String> response = ResponseDTO.<String>builder()
                .message("test 성공")
                .build();
        return ResponseEntity.ok().body(response);
    }
}
