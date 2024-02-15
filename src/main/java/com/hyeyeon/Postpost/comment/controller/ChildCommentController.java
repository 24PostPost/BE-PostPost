package com.hyeyeon.Postpost.comment.controller;

import com.hyeyeon.Postpost.comment.model.service.ChildCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment/child")
public class ChildCommentController {

    private final ChildCommentService childCommentService;

    // 대댓글 작성
    @PostMapping("/{userId}")
    public ResponseEntity<String> newChildComment(@PathVariable Long postId,
                                                  @PathVariable Long userId,
                                                  Long commentId,
                                                  String content) {
        childCommentService.newChildComment(postId, userId, commentId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body("대댓글 작성 완료");
    }

    // 대댓글 수정
    @PatchMapping("/{childCommentId}")
    public ResponseEntity<String> editChildComment(@PathVariable Long childCommentId, String content) {
        childCommentService.editChildComment(childCommentId, content);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("대댓글 수정 완료");
    }

    // 대댓글 삭제
    @DeleteMapping("/{childCommentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long childCommentId) {
        childCommentService.deleteChildComment(childCommentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("대댓글 삭제 완료");
    }
}
