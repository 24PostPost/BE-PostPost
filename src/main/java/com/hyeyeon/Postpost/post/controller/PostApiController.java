package com.hyeyeon.Postpost.post.controller;

import com.hyeyeon.Postpost.post.model.dto.PostRequestDto;
import com.hyeyeon.Postpost.post.model.service.PostOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostApiController {

    private final PostOperationService postOperationService;

    // 아이콘 선택
    @PostMapping("/icon/{userId}")
    public ResponseEntity<String> icon(@PathVariable Long userId, @RequestPart String icon) {
        postOperationService.newIcon(userId, icon);
        return ResponseEntity.status(HttpStatus.CREATED).body("아이콘 선택 완료");
    }

    // 포스트 작성
    @PostMapping("/new/{postId}")
    public ResponseEntity<String> newPost(@PathVariable Long postId, @RequestPart PostRequestDto postRequestDto) {
        postOperationService.newPost(postId, postRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("포스트 작성 완료");
    }

    // 포스트 수정
    @PatchMapping("/edit/{postId}")
    public ResponseEntity<String> editPost(@PathVariable Long postId, @RequestPart PostRequestDto postRequestDto) {
        postOperationService.editPost(postId, postRequestDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("포스트 수정 완료");
    }

    // 포스트 공유
    @PostMapping("/share/{postId}")
    public ResponseEntity<String> sharePost(@PathVariable Long postId) {
        postOperationService.sharePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("포스트 공유 완료");
    }

    // 포스트 공유
    @PostMapping("/cancel/{postId}")
    public ResponseEntity<String> cancelSharePost(@PathVariable Long postId) {
        postOperationService.cancelSharePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("포스트 공유 취소 완료");
    }


    // 포스트 삭제
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        postOperationService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("포스트 삭제 완료");
    }
}
