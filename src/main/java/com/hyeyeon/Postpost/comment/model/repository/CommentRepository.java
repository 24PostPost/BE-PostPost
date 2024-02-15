package com.hyeyeon.Postpost.comment.model.repository;

import com.hyeyeon.Postpost.comment.model.entity.Comment;
import com.hyeyeon.Postpost.post.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);
}
