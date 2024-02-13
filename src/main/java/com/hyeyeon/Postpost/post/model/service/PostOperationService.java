package com.hyeyeon.Postpost.post.model.service;

import com.hyeyeon.Postpost.post.model.dto.PostRequestDto;
import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.post.model.repository.PostRepository;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class PostOperationService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void newIcon(Long userId, String icon) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        Post post = Post.builder()
                .user(user)
                .icon(icon)
                .build();

        postRepository.save(post);
    }

    public void newPost(Long postId, PostRequestDto postRequestDto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 포스트가 존재하지 않습니다."));

        User user = userRepository.findById(post.getUser().getUserId())
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        post.newPost(user, postRequestDto.getTitle(), postRequestDto.getContent(), Timestamp.valueOf(LocalDateTime.now()), 'N');
        postRepository.save(post);
    }

    public void editPost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 포스트가 존재하지 않습니다."));
        post.updatePost(postRequestDto.getIcon(), postRequestDto.getTitle(), postRequestDto.getContent());
    }

    public void sharePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 포스트가 존재하지 않습니다."));
        post.sharePost();
    }

    public void cancelSharePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 포스트가 존재하지 않습니다."));
        post.cancelSharePost();
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
