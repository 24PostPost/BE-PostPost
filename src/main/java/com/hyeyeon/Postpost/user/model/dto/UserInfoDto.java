package com.hyeyeon.Postpost.user.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfoDto {

    private String email;
    private String nickname;

    @Builder
    public UserInfoDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
