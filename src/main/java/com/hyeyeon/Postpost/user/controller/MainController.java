package com.hyeyeon.Postpost.user.controller;

import com.hyeyeon.Postpost.post.model.dto.SharePostDto;
import com.hyeyeon.Postpost.post.model.dto.UserPostDto;
import com.hyeyeon.Postpost.post.model.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

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

        // 닉네임, 작성한 post수, 아이콘 Top3


        return ResponseEntity.ok(result);
    }
}
