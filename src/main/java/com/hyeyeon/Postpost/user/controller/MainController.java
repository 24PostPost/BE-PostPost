package com.hyeyeon.Postpost.user.controller;

import com.hyeyeon.Postpost.post.model.dto.SharePostDto;
import com.hyeyeon.Postpost.post.model.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

//    // 알림창
//    @GetMapping("/notifications/{userId}")
//    public ResponseEntity<List<NotificationDto>> notiPage() {
//        List<NotificationDto> notificationList = notificationService.getListOfNotification();
//        return ResponseEntity.ok(notificationList)
//    }

//    // 우체통창
//    @GetMapping("/mailbox/{userId}")
//    public String mailboxPage() {
//        return "우체통 창";
//    }
}
