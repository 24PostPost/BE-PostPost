package com.hyeyeon.Postpost.comment.model.entity;

import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.user.model.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parentCommentId")
    private final List<ChildComment> childComments = new ArrayList<>();

    @Builder
    public Comment(Long commentId, User user, Post post, String content, Timestamp createdAt) {
        this.commentId = commentId;
        this.user = user;
        this.post = post;
        this.content = content;
        this.createdAt = createdAt;
    }

    // 댓글 수정
    public void updateComment(String content) {
        this.content = content;
    }
}
