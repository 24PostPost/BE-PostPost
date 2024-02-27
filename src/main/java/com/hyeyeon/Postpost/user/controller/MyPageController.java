package com.hyeyeon.Postpost.user.controller;

import com.hyeyeon.Postpost.user.model.dto.UserInfoDto;
import com.hyeyeon.Postpost.user.model.service.MyPageService;
import com.hyeyeon.Postpost.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;
    private final MyPageService myPageService;

    // 마이페이지
    @GetMapping("/myPage/{userId}")
    public ResponseEntity<?> myPage(@PathVariable Long userId) {
        UserInfoDto userInfoDto = myPageService.getUserInfo(userId);
        return ResponseEntity.ok(userInfoDto);
    }

    // 마이페이지 - 계정
    @GetMapping("/myPage/account/{userId}")
    public ResponseEntity<?> myAccount(@PathVariable Long userId) {
        UserInfoDto userInfoDto = myPageService.getUserInfo(userId);
        return ResponseEntity.ok(userInfoDto);
    }

    // 마이페이지 - 닉네임 수정
    @PostMapping("/myPage/nickname/{userId}")
    public ResponseEntity<String> updateNickname(@PathVariable Long userId, @RequestParam String nickname) {
        userService.updateNickname(userId, nickname);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("닉네임 수정 완료");
    }
}