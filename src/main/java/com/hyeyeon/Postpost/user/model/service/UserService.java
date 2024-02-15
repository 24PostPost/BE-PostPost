package com.hyeyeon.Postpost.user.model.service;

import com.hyeyeon.Postpost.exception.ConflictException;
import com.hyeyeon.Postpost.exception.InvalidException;
import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void updateNickname(Long userId, String nickname) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));
        validationDuplicationNickname(user.getEmail(), nickname, user);
        user.updateNickname(nickname);
    }

    public void updateBirthday(Long userId, LocalDate birthday) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));
        user.updateBirthday(birthday);
    }

    private void validationDuplicationNickname(String email, String nickname, User user) {
        if (user.getNickname() != null && !user.getNickname().equals(nickname)) {
            if (nickname.isEmpty() || nickname.length() > 16)
                throw new InvalidException(InvalidException.DEFAULT);
            if (userRepository.isDuplicationNickname(email, nickname))
                throw new ConflictException(ConflictException.MEMBER);
        }
    }
}
