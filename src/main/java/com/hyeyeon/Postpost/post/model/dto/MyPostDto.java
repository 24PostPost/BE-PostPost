package com.hyeyeon.Postpost.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class MyPostDto {

    private Long postId;
    private String icon;
    private LocalDate createdAt;

    @Builder
    public MyPostDto(Long postId, String icon, LocalDate createdAt) {
        this.postId = postId;
        this.icon = icon;
        this.createdAt = createdAt;
    }
}
