package com.hyeyeon.Postpost.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserPostDto {

    private Long postId;
    private String icon;
    private int day;
    private String nickname;

    @Builder
    public UserPostDto(Long postId, String icon, int day, String nickname) {
        this.postId = postId;
        this.icon = icon;
        this.day = day;
        this.nickname = nickname;
    }
}
