package com.hyeyeon.Postpost.user.model.service;

import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.user.model.dto.UserInfoDto;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
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
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();

        return userInfoDto;
    }

}
