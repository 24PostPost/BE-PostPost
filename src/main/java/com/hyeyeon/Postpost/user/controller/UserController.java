package com.hyeyeon.Postpost.user.controller;

import com.hyeyeon.Postpost.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 닉네임 입력
    @PostMapping("/nickname/{userId}")
    public ResponseEntity<String> updateNickname(@PathVariable Long userId, @RequestParam String nickname) {
        userService.updateNickname(userId, nickname);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("닉네임 입력 완료");
    }

    // 생일 입력
    @PostMapping("/birthday/{userId}")
    public ResponseEntity<String> updateBirthday(@PathVariable Long userId, @RequestParam LocalDate birthday) {
        userService.updateBirthday(userId, birthday);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("생일 입력 완료");
    }
}
