package com.hyeyeon.Postpost.user.model.dto;

import com.hyeyeon.Postpost.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@Getter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String nickname;

    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto(user.getNickname());
    }
}
