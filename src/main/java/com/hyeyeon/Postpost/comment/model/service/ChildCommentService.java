package com.hyeyeon.Postpost.comment.model.service;

import com.hyeyeon.Postpost.comment.model.entity.ChildComment;
import com.hyeyeon.Postpost.comment.model.entity.Comment;
import com.hyeyeon.Postpost.comment.model.repository.ChildCommentRepository;
import com.hyeyeon.Postpost.comment.model.repository.CommentRepository;
import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.post.model.repository.PostRepository;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChildCommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ChildCommentRepository childCommentRepository;

    public void newChildComment(Long postId, Long userId, Long commentId, String content) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 포스트가 존재하지 않습니다."));

        ChildComment childComment = ChildComment.builder()
                .user(user)
                .parentCommentId(commentId)
                .content(content)
                .build();

        childCommentRepository.save(childComment);
    }

    public void editChildComment(Long childCommentId, String content) {
        System.out.println("editComment");
        ChildComment childComment = childCommentRepository.findById(childCommentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 댓글이 존재하지 않습니다"));
        childComment.updateChildComment(content);
    }

    public void deleteChildComment(Long childCommentId) {
        childCommentRepository.deleteById(childCommentId);
    }
}
