package com.hyeyeon.Postpost.user.model.service;

import com.hyeyeon.Postpost.user.model.dto.UserInfoDto;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;

    public UserInfoDto getUserInfo(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저가 존재하지 않습니다."));

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();

        return userInfoDto;
    }

}
