package com.hyeyeon.Postpost.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IconRankDto {

    String icon;
    int rank;

    @Builder
    public IconRankDto(String icon, int rank) {
        this.icon = icon;
        this.rank = rank;
    }
}
