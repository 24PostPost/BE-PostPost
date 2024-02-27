package com.hyeyeon.Postpost.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class TodayDateDto {

    private LocalDate createdAt;
    private String day;

    @Builder
    public TodayDateDto(LocalDate createdAt, String day) {
        this.createdAt = createdAt;
        this.day = day;
    }
}
