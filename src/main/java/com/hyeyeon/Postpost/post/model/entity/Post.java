package com.hyeyeon.Postpost.post.model.entity;

import com.hyeyeon.Postpost.user.model.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String icon;
    private String title;
    private String content;

    private String imageUri;

    @CreationTimestamp
    private LocalDate createdAt;

    private char isOpen; //'Y', 'N'

    @Builder
    public Post(User user, String icon) {
        this.user = user;
        this.icon = icon;
    }

    // 포스트 작성
    public void newPost(User user, String title, String content, String imageUri, LocalDate createdAt, char isOpen) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.imageUri = imageUri;
        this.createdAt = createdAt;
        this.isOpen = isOpen;
    }

    // 포스트 수정
    public void updatePost(String icon, String title, String content, String imageUri) {
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.imageUri = imageUri;
    }

    // 포스트 공유
    public void sharePost() {
        this.isOpen = 'Y';
    }

    // 포스트 공유 취소
    public void cancelSharePost() {
        this.isOpen = 'N';
    }
}
