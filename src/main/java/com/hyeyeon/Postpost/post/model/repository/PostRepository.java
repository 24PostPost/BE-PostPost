package com.hyeyeon.Postpost.post.model.repository;

import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByIsOpen(char isOpen);

    List<Post> findAllByUser(User user);
}
