package com.hyeyeon.Postpost.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    private String icon;
    private String title;
    private String content;
}
