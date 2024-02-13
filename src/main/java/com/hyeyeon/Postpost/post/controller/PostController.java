package com.hyeyeon.Postpost.post.controller;

import com.hyeyeon.Postpost.post.model.dto.MyPostDto;
import com.hyeyeon.Postpost.post.model.dto.PostInfoDto;
import com.hyeyeon.Postpost.post.model.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    // 포스트 내용창
    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoDto> getPost(@PathVariable Long postId) {
        PostInfoDto postInfoDto = postService.getPostInfo(postId);
        return ResponseEntity.ok(postInfoDto);
    }

    // 포스트 편집창
    @GetMapping("/edit/{postId}")
    public ResponseEntity<PostInfoDto> editPost(@PathVariable Long postId) {
        PostInfoDto postInfoDto = postService.getPostInfo(postId);
        return ResponseEntity.ok(postInfoDto);
    }

    // 포스트 공유창
    @GetMapping("/share/{userId}")
    public ResponseEntity<List<MyPostDto>> sharePost(@PathVariable Long userId) {
        List<MyPostDto> myPostList = postService.getMyPost(userId);
        return ResponseEntity.ok(myPostList);
    }

    // 포스트 작성 후, 확인 모달
    @GetMapping("/new/result/{postId}")
    public ResponseEntity<?> newResult(@PathVariable Long postId) {
        String icon = postService.getIcon(postId);
        return ResponseEntity.ok(icon);
    }

    // 포스트 공유 후, 확인 모달
    @GetMapping("/share/result/{postId}")
    public ResponseEntity<?> shareResult(@PathVariable Long postId) {
        String icon = postService.getIcon(postId);
        return ResponseEntity.ok(icon);
    }
}
