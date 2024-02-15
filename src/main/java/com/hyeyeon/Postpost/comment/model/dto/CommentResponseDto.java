package com.hyeyeon.Postpost.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private String nickname;
    private String content;
    private Timestamp createdAt;
}
