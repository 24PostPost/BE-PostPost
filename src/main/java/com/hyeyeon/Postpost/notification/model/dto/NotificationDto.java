package com.hyeyeon.Postpost.notification.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class NotificationDto {

    private Long notiId;
    private LocalDateTime createdAt;
    private String content;

    @Builder
    public NotificationDto(Long notiId, LocalDateTime createdAt, String content) {
        this.notiId = notiId;
        this.createdAt = createdAt;
        this.content = content;
    }
}
