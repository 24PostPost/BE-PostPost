package com.hyeyeon.Postpost.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class MyPostDto {

    private Long postId;
    private String icon;
    private Timestamp createdAt;

    @Builder
    public MyPostDto(Long postId, String icon, Timestamp createdAt) {
        this.postId = postId;
        this.icon = icon;
        this.createdAt = createdAt;
    }
}
