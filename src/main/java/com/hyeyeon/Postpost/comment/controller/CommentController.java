package com.hyeyeon.Postpost.comment.controller;

import com.hyeyeon.Postpost.comment.model.dto.CommentResponseDto;
import com.hyeyeon.Postpost.comment.model.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentController {

    private final CommentService commentService;

    // 댓글창
    @GetMapping("")
    public ResponseEntity<List<CommentResponseDto>> getPostComments(@PathVariable Long postId) {
        List<CommentResponseDto> commentsList = commentService.getPostComments(postId);
        return ResponseEntity.ok(commentsList);
    }

    // 댓글 작성
    @PostMapping("/{userId}")
    public ResponseEntity<String> newComment(@PathVariable Long postId, @PathVariable Long userId, String content) {
        commentService.newComment(postId, userId, content);
        return ResponseEntity.status(HttpStatus.CREATED).body("댓글 작성 완료");
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<String> editComment(@PathVariable Long commentId, String content) {
        commentService.editComment(commentId, content);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("댓글 수정 완료");
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("댓글 삭제 완료");
    }
}
