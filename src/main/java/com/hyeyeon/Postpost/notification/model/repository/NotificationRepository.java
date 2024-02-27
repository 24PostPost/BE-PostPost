package com.hyeyeon.Postpost.notification.model.repository;

import com.hyeyeon.Postpost.notification.model.entity.Notification;
import com.hyeyeon.Postpost.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserAndIsRead(User user, char isRead);
}
