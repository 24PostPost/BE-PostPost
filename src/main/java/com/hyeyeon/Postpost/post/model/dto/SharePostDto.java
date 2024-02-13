package com.hyeyeon.Postpost.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class SharePostDto {

    private Long postId;
    private String icon;
    private String title;
    private String nickname;

    @Builder
    public SharePostDto(Long postId, String icon, String title, String nickname) {
        this.postId = postId;
        this.icon = icon;
        this.title = title;
        this.nickname = nickname;
    }
}
