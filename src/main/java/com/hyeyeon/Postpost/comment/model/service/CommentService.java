package com.hyeyeon.Postpost.comment.model.service;

import com.hyeyeon.Postpost.comment.model.dto.CommentResponseDto;
import com.hyeyeon.Postpost.comment.model.entity.Comment;
import com.hyeyeon.Postpost.comment.model.repository.CommentRepository;
import com.hyeyeon.Postpost.exception.NotFoundException;
import com.hyeyeon.Postpost.notification.model.entity.Notification;
import com.hyeyeon.Postpost.notification.model.entity.NotificationType;
import com.hyeyeon.Postpost.notification.model.repository.NotificationRepository;
import com.hyeyeon.Postpost.post.model.entity.Post;
import com.hyeyeon.Postpost.post.model.repository.PostRepository;
import com.hyeyeon.Postpost.user.model.entity.User;
import com.hyeyeon.Postpost.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;

    public List<CommentResponseDto> getPostComments(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));

        List<Comment> commentsList = commentRepository.findAllByPost(post);

        // 프론트 측 확인 후 수정
        // if) 년원일시분만 넘겨주면 되면 원래대로 변경
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment : commentsList) {
            if (comment.getDate().equals(LocalDate.now())) {
                if (comment.getTime().getHour() == LocalTime.now().getHour()) {
                    int min = LocalTime.now().getMinute() - comment.getTime().getMinute();
                    CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                            .commentId(comment.getCommentId())
                            .nickname(comment.getUser().getNickname())
                            .content(comment.getContent())
                            .min(min)
                            .build();

                    commentResponseDtoList.add(commentResponseDto);
                }
                else {
                    int hour = LocalTime.now().getHour() - comment.getTime().getHour();
                    CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                            .commentId(comment.getCommentId())
                            .nickname(comment.getUser().getNickname())
                            .content(comment.getContent())
                            .hour(hour)
                            .build();

                    commentResponseDtoList.add(commentResponseDto);
                }
            }
            else {
                LocalDate date1 = comment.getDate();
                LocalDate date2 = LocalDate.now();

                int days = Period.between(date1, date2).getDays();

                CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                        .commentId(comment.getCommentId())
                        .nickname(comment.getUser().getNickname())
                        .content(comment.getContent())
                        .date(days)
                        .build();

                commentResponseDtoList.add(commentResponseDto);
            }
        }

        return commentResponseDtoList;
    }

    public void newComment(Long postId, Long userId, String content) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.USER));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.POST));

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .content(content)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .build();

        Notification notification = Notification.builder()
                .user(post.getUser())
                .post(post)
                .notiType(NotificationType.Comment)
                .isRead('N')
                .build();

        commentRepository.save(comment);
        notificationRepository.save(notification);
    }

    public void editComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(NotFoundException.COMMENT));
        comment.updateComment(content);
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
