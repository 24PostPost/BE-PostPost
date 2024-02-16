package com.hyeyeon.Postpost.notification.model.service;

import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.notification.model.dto.NotificationDto;
import com.hyeyeon.Postpost.notification.model.entity.Notification;
import com.hyeyeon.Postpost.notification.model.entity.NotificationType;
import com.hyeyeon.Postpost.notification.model.repository.NotificationRepository;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    // 오늘의 편지 알림 -> 오전 10시 스케줄러
    @Scheduled(cron = "0 0 10 * * *")
    public void todayPostNoti() {
        List<User> allUserList = userRepository.findAll();

        for (User user : allUserList) {
            Notification notification = Notification.builder()
                    .user(user)
                    .notiType(NotificationType.TodayPost)
                    .isRead('N')
                    .build();

            notificationRepository.save(notification);
        }
    }

    // 생일 알림 -> 오전 12시 스케줄러
    @Scheduled(cron = "0 0 0 * * *")
    public void birthdayNoti() {
        List<User> allUserList = userRepository.findAll();

        List<User> todayBirthUserList = new ArrayList<>();

        for (User user : allUserList) {
            if (user.getBirthday().getMonthValue() == LocalDate.now().getMonthValue()
            && user.getBirthday().getDayOfMonth() == LocalDate.now().getDayOfMonth())
                todayBirthUserList.add(user);
        }

        for (User user : todayBirthUserList) {
            Notification notification = Notification.builder()
                    .user(user)
                    .notiType(NotificationType.Birthday)
                    .isRead('N')
                    .build();

            notificationRepository.save(notification);
        }
    }

    public List<NotificationDto> getListOfNotification(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        List<Notification> notiListByUser = notificationRepository.findByUserAndIsRead(user, 'N');
        List<NotificationDto> notificationDtoList = new ArrayList<>();

        for (Notification notification : notiListByUser) {
            if (notification.getNotiType() == NotificationType.TodayPost) {
                NotificationDto notificationDto = NotificationDto.builder()
                        .notiId(notification.getNotiId())
                        .createdAt(LocalDateTime.now())
                        .content("오늘의 편지를 작성할 시간이에요!")
                        .build();
                notificationDtoList.add(notificationDto);
            } else if (notification.getNotiType() == NotificationType.Comment) {
                NotificationDto notificationDto = NotificationDto.builder()
                        .notiId(notification.getNotiId())
                        .createdAt(LocalDateTime.now())
                        .content("공유한 내 편지에 댓글이 달렸어요!")
                        .build();
                notificationDtoList.add(notificationDto);
            } else {
                NotificationDto notificationDto = NotificationDto.builder()
                        .notiId(notification.getNotiId())
                        .createdAt(LocalDateTime.now())
                        .content("생일 편지가 도착했어요!")
                        .build();
                notificationDtoList.add(notificationDto);
            }
        }

        return notificationDtoList;
    }
}
