package com.hyeyeon.Postpost.user.model.repository;

import com.hyeyeon.Postpost.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    boolean isDuplicationNickname(String email, String nickname);

}
