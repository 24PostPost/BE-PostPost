package com.hyeyeon.Postpost.post.model.service;

import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.post.model.dto.PostRequestDto;
import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.post.model.repository.PostRepository;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class PostOperationService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void newIcon(Long userId, String icon) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        Post post = Post.builder()
                .user(user)
                .icon(icon)
                .build();

        postRepository.save(post);
    }

    public void newPost(Long postId, PostRequestDto postRequestDto) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));

        User user = userRepository.findById(post.getUser().getUserId())
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        post.newPost(user, postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getImageUri(), LocalDate.now(), 'N');
        postRepository.save(post);
    }

    public void editPost(Long postId, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));
        post.updatePost(postRequestDto.getIcon(), postRequestDto.getTitle(), postRequestDto.getContent(), postRequestDto.getImageUri());
    }

    public void sharePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));
        post.sharePost();
    }

    public void cancelSharePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));
        post.cancelSharePost();
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
