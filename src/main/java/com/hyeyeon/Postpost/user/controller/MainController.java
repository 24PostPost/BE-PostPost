package com.hyeyeon.Postpost.user.controller;

import com.hyeyeon.Postpost.post.model.dto.IconRankDto;
import com.hyeyeon.Postpost.post.model.dto.SharePostDto;
import com.hyeyeon.Postpost.post.model.dto.UserPostDto;
import com.hyeyeon.Postpost.post.model.service.PostService;
import com.hyeyeon.Postpost.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final PostService postService;

    // 공유 일기창
    @GetMapping("/share")
    public ResponseEntity<List<SharePostDto>> sharePage() {
        List<SharePostDto> sharePostList = postService.getSharePost();
        return ResponseEntity.ok(sharePostList);
    }

    // 우체통창
    @GetMapping("/mailbox/{userId}")
    public ResponseEntity<Map<String, Object>> mailboxPage(@PathVariable Long userId, LocalDate targetDate) {

        Map<String, Object> result = new HashMap<>();

        // post 리스트
        List<UserPostDto> mailboxList = postService.getUserPost(userId, targetDate);
        result.put("mailboxList", mailboxList);

        // 닉네임
        String nickname = userService.getNickname(userId);
        result.put("userNickname", nickname);

        // 작성한 포스트 수
        int postCnt = mailboxList.size();
        result.put("postCnt", postCnt);

        //아이콘 Top3
        List<IconRankDto> top3Icon = postService.getTop3Icon(userId);
        result.put("top3Icon", top3Icon);

        return ResponseEntity.ok(result);
    }
}
