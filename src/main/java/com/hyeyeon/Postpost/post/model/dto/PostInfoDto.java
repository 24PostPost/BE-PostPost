package com.hyeyeon.Postpost.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class PostInfoDto {

    private Long postId;
    private String icon;
    private String title;
    private String content;
    private String imageUri;
    private LocalDate createdAt;
    private String day;

    @Builder
    public PostInfoDto(Long postId, String icon, String title, String content, String imageUri, LocalDate createdAt, String day) {
        this.postId = postId;
        this.icon = icon;
        this.title = title;
        this.content = content;
        this.imageUri = imageUri;
        this.createdAt = createdAt;
        this.day = day;
    }
}
