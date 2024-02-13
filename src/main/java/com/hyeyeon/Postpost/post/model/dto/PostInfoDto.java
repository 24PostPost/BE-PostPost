package com.hyeyeon.Postpost.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class PostInfoDto {

    private Long postId;
    private String icon;
    private String title;
    private String content;
    private Timestamp createdAt;

    @Builder
    public PostInfoDto(Long postId, String icon, String title, String content, Timestamp createdAt) {
        this.postId = postId;
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}
