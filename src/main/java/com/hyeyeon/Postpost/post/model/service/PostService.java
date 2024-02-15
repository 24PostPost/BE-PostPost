package com.hyeyeon.Postpost.post.model.service;

import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.post.model.dto.MyPostDto;
import com.hyeyeon.Postpost.post.model.dto.PostInfoDto;
import com.hyeyeon.Postpost.post.model.dto.SharePostDto;
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
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostInfoDto getPostInfo(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));

        PostInfoDto postInfoDto = PostInfoDto.builder()
                .postId(post.getPostId())
                .icon(post.getIcon())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .build();

        return postInfoDto;
    }

    public List<MyPostDto> getMyPost(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        List<Post> myPostList = postRepository.findAllByUser(user);

        List<Post> myPostInfoList = new ArrayList<>();
        for (Post post : myPostList) {
            myPostInfoList.add(post);
        }

        List<MyPostDto> postInfoDtoList = new ArrayList<>();
        for (Post post : myPostInfoList) {
            MyPostDto myPostDto = MyPostDto.builder()
                    .postId(post.getPostId())
                    .icon(post.getIcon())
                    .createdAt(post.getCreatedAt())
                    .build();

            postInfoDtoList.add(myPostDto);
        }

        return postInfoDtoList;
    }

    public List<SharePostDto> getSharePost() {

        // 공유된 post 리스트 호출
        List<Post> sharePostList = postRepository.findAllByIsOpen('Y');

        List<Post> sharePostInfoList = new ArrayList<>();
        for (Post post : sharePostList) {
            sharePostInfoList.add(post);
        }

        // 공유된 post 정보 리스트에 저장
        // 정확한 기준 확인 필요!!!
        List<SharePostDto> sharePostDtoList = new ArrayList<>();
        for (Post post : sharePostInfoList) {
            SharePostDto sharePostDto = SharePostDto.builder()
                    .postId(post.getPostId())
                    .icon(post.getIcon())
                    .title(post.getTitle())
                    .nickname(post.getUser().getNickname())
                    .build();

            sharePostDtoList.add(sharePostDto);
        }

        return sharePostDtoList;
    }

    public String getIcon(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));
        return post.getIcon();
    }
}
