package com.hyeyeon.Postpost.post.model.service;

import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.post.model.dto.MyPostDto;
import com.hyeyeon.Postpost.post.model.dto.PostInfoDto;
import com.hyeyeon.Postpost.post.model.dto.SharePostDto;
import com.hyeyeon.Postpost.post.model.dto.UserPostDto;
import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.post.model.repository.PostRepository;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostInfoDto getPostInfo(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));

        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);

        PostInfoDto postInfoDto = PostInfoDto.builder()
                .postId(post.getPostId())
                .icon(post.getIcon())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(LocalDate.now())
                .day(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN))
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
                    .createdAt(LocalDate.now())
                    .build();

            postInfoDtoList.add(myPostDto);
        }

        return postInfoDtoList;
    }

    public String getIcon(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));
        return post.getIcon();
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

    public List<UserPostDto> getUserPost(Long userId, LocalDate targetDate) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        int year = targetDate.getYear();
        int month = targetDate.getMonthValue();

        List<Post> allUserPostList = postRepository.findAllByUser(user);

        // 해당 연, 월의 포스트만 담은 리스트
        List<UserPostDto> userPostDtoList = new ArrayList<>();

        for (Post post : allUserPostList) {
            if (post.getCreatedAt().getYear() == year && post.getCreatedAt().getMonthValue() == month) {
                UserPostDto userPostDto = UserPostDto.builder()
                        .postId(post.getPostId())
                        .icon(post.getIcon())
                        .day(post.getCreatedAt().getDayOfMonth())
                        .nickname(user.getNickname())
                        .build();

                userPostDtoList.add(userPostDto);
            }
        }

        return userPostDtoList;
    }

    public void getTop3Icon(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        List<Post> allUserPostList = postRepository.findAllByUser(user);

        List<Post> monthPostList = new ArrayList<>();
        int proudCnt = 0;
        int happyCnt = 0;
        int calmCnt = 0;
        int tiredCnt = 0;
        int depressedCnt = 0;
        int angryCnt = 0;
        int sadCnt = 0;

        for (Post post : allUserPostList) {
            if (post.getCreatedAt().getYear() == year && post.getCreatedAt().getMonthValue() == month) {
                if (post.getIcon() == "proud") proudCnt += 1;
                else if (post.getIcon() == "happy") happyCnt += 1;
                else if (post.getIcon() == "calm") calmCnt += 1;
                else if (post.getIcon() == "tired") tiredCnt += 1;
                else if (post.getIcon() == "depressed") depressedCnt += 1;
                else if (post.getIcon() == "angry") angryCnt += 1;
                else if (post.getIcon() == "sad") sadCnt += 1;
            }
        }
    }
}
