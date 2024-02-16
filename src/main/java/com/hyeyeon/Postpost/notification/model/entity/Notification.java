package com.hyeyeon.Postpost.notification.model.entity;

import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.user.model.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notiId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    private NotificationType notiType;
    private char isRead;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Notification(Long notiId, User user, Post post, NotificationType notiType, char isRead, Timestamp createdAt) {
        this.notiId = notiId;
        this.user = user;
        this.post = post;
        this.notiType = notiType;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    // 알림 읽음 처리
    public void updateIsRead(char isRead) {
        this.isRead = isRead;
    }
}
