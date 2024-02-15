package com.hyeyeon.Postpost.comment.model.service;

import com.hyeyeon.Postpost.comment.model.dto.CommentResponseDto;
import com.hyeyeon.Postpost.comment.model.entity.Comment;
import com.hyeyeon.Postpost.comment.model.repository.CommentRepository;
import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.post.model.repository.PostRepository;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public List<CommentResponseDto> getPostComments(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 포스트가 존재하지 않습니다."));

        List<Comment> commentsList = commentRepository.findAllByPost(post);

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentsList) {
            CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                    .commentId(comment.getCommentId())
                    .nickname(comment.getUser().getNickname())
                    .content(comment.getContent())
                    .createdAt(comment.getCreatedAt())
                    .build();

            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }

    public void newComment(Long postId, Long userId, String content) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 포스트가 존재하지 않습니다."));

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .build();

        commentRepository.save(comment);
    }

    public void editComment(Long commentId, String content) {
        System.out.println("editComment");
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 댓글이 존재하지 않습니다"));
        comment.updateComment(content);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
