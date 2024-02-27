package com.hyeyeon.Postpost.post.model.service;

import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.post.model.dto.*;
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
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public TodayDateDto getTodayDate() {

        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN);

        TodayDateDto todayDateDto = TodayDateDto.builder()
                .createdAt(date)
                .day(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN))
                .build();

        return todayDateDto;
    }

    public PostInfoDto getPostInfo(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));

        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        PostInfoDto postInfoDto = PostInfoDto.builder()
                .postId(post.getPostId())
                .icon(post.getIcon())
                .title(post.getTitle())
                .content(post.getContent())
                .imageUri(post.getImageUri())
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

        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        List<MyPostDto> postInfoDtoList = new ArrayList<>();
        for (Post post : myPostInfoList) {
            MyPostDto myPostDto = MyPostDto.builder()
                    .postId(post.getPostId())
                    .icon(post.getIcon())
                    .createdAt(date)
                    .day(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN))
                    .build();

            postInfoDtoList.add(myPostDto);
        }

        return postInfoDtoList;
    }

//    public String getIcon(Long postId) {
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));
//        return post.getIcon();
//    }

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

    public List<IconRankDto> getTop3Icon(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        return getIconRankDtos(user);
    }

    private List<IconRankDto> getIconRankDtos(User user) {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        List<Post> allUserPostList = postRepository.findAllByUser(user);

        Map<String, Integer> monthlyPostIconList = new HashMap<>();
        monthlyPostIconList.put("proud", 0);
        monthlyPostIconList.put("happy", 0);
        monthlyPostIconList.put("calm", 0);
        monthlyPostIconList.put("tired", 0);
        monthlyPostIconList.put("depressed", 0);
        monthlyPostIconList.put("angry", 0);
        monthlyPostIconList.put("sad", 0);

        for (Post post : allUserPostList) {
            if (post.getCreatedAt().getYear() == year && post.getCreatedAt().getMonthValue() == month) {
                if ("proud".equals(post.getIcon())) {
                    monthlyPostIconList.replace("proud", monthlyPostIconList.get("proud") + 1);
                }
                else if ("happy".equals(post.getIcon())) {
                    monthlyPostIconList.replace("happy", monthlyPostIconList.get("happy") + 1);
                }
                else if ("calm".equals(post.getIcon())) {
                    monthlyPostIconList.replace("calm", monthlyPostIconList.get("calm") + 1);
                }
                else if ("tired".equals(post.getIcon())) {
                    monthlyPostIconList.replace("tired", monthlyPostIconList.get("tired") + 1);
                }
                else if ("depressed".equals(post.getIcon())) {
                    monthlyPostIconList.replace("depressed", monthlyPostIconList.get("depressed") + 1);
                }
                else if ("angry".equals(post.getIcon())) {
                    monthlyPostIconList.replace("angry", monthlyPostIconList.get("angry") + 1);
                }
                else {
                    monthlyPostIconList.replace("sad", monthlyPostIconList.get("sad") + 1);
                }
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(monthlyPostIconList.entrySet());

        Collections.sort(list, new Comparator<>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue()); // 내림차순
            }
        });

        Map<String, Integer> result = new HashMap<>();
        List<IconRankDto> iconRankDtoList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            result.put(list.get(i).getKey(), list.get(i).getValue());
            IconRankDto iconRankDto = IconRankDto.builder()
                    .icon(list.get(i).getKey())
                    .rank(i+1)
                    .build();

            iconRankDtoList.add(iconRankDto);
        }

        return iconRankDtoList;
    }
}