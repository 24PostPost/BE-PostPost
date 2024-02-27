package com.hyeyeon.Postpost.notification.controller;

import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.notification.model.dto.NotificationDto;
import com.hyeyeon.Postpost.notification.model.entity.Notification;
import com.hyeyeon.Postpost.notification.model.repository.NotificationRepository;
import com.hyeyeon.Postpost.notification.model.service.NotificationService;
import com.hyeyeon.Postpost.user.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final UserService userService;
    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;

    // 알림창
    @GetMapping("/notifications/{userId}")
    public ResponseEntity<List<NotificationDto>> notiPage(@PathVariable Long userId) {
        List<NotificationDto> notificationList = notificationService.getListOfNotification(userId);
        for (NotificationDto notificationDto : notificationList) {
            Notification notification = notificationRepository.findById(notificationDto.getNotiId())
                    .orElseThrow(() -> new NotFoundException(NotFoundException.NOTIFICATION));
            notification.updateIsRead('Y');
            notificationRepository.save(notification);
        }
        return ResponseEntity.ok(notificationList);
    }

    // 생일편지창
    @GetMapping("/notifications/{userId}/birth")
    public ResponseEntity<?> birthdayCard(@PathVariable Long userId) {
        String nickname = userService.getNickname(userId);
        return ResponseEntity.ok(nickname);
    }
}
