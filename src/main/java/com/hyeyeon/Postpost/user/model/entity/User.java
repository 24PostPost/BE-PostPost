package com.hyeyeon.Postpost.user.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;
    private String email;
    private String nickname;
    private String role;
    private LocalDate birthday;

    private String provider; // 어떤 OAuth인지 -> Google, Naver
    private String providerId; //

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public User(String email, String name, String nickname, String role, LocalDate birthday, String provider, String providerId, Timestamp createdAt) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.birthday = birthday;
        this.provider = provider;
        this.providerId = providerId;
        this.createdAt = createdAt;
    }

    // 닉네임 입력 & 변경
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    // 생일 입력 & 변경
    public void updateBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
