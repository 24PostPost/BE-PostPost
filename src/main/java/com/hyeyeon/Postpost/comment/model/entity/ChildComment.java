package com.hyeyeon.Postpost.comment.model.entity;

import com.hyeyeon.Postpost.user.model.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "childComment")
public class ChildComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childCommentId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private Long parentCommentId;

    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public ChildComment(Long childCommentId, User user, Long parentCommentId, String content, Timestamp createdAt) {
        this.childCommentId = childCommentId;
        this.user = user;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.createdAt = createdAt;
    }

    // 대댓글 수정
    public void updateChildComment(String content) {
        this.content = content;
    }
}
