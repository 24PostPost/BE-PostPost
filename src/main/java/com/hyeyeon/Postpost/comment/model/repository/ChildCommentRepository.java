package com.hyeyeon.Postpost.comment.model.repository;

import com.hyeyeon.Postpost.comment.model.entity.ChildComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildCommentRepository extends JpaRepository<ChildComment, Long> {
}
