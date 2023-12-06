package portal.news.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.news.dto.comment.CommentRequestDto;
import portal.news.dto.comment.CommentResponseDto;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.mappers.CommentMapper;
import portal.news.models.Comment;
import portal.news.models.News;
import portal.news.models.User;
import portal.news.repositories.CommentRepository;
import portal.news.repositories.NewsRepository;
import portal.news.repositories.UserRepository;
import portal.news.services.CommentService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

    @Override
    public CommentResponseDto getById(long id) {
        Comment comment = getEntityById(id);
        CommentResponseDto responseDto = commentMapper.toDto(comment);
        log.info("Comment with ID {} retrieved successfully.", id);
        return responseDto;
    }

    @Override
    @Transactional
    public CommentResponseDto create(CommentRequestDto requestDto) {

        User user = userRepository.findById(requestDto.user_id())
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + requestDto.user_id() + " does not exist"));


        News news = newsRepository.findById(requestDto.news_id())
                .orElseThrow(() -> new EntityNotFoundException("News with ID " + requestDto.news_id() + " does not exist"));


        Comment comment = Comment.builder()
                .text(requestDto.text())
                .user(user)
                .news(news)
                .commentDate(LocalDateTime.now())
                .build();

        comment = commentRepository.save(comment);
        CommentResponseDto responseDto = CommentResponseDto.builder()
                .comment_date(LocalDateTime.now())
                .news_id(requestDto.news_id())
                .user_id(requestDto.user_id())
                .text(requestDto.text())
                .build();
        log.info("Comment created successfully. ID: {}", comment.getId());

        return responseDto;
    }

    @Override
    public CommentResponseDto update(long id, CommentRequestDto requestDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        Comment comment = getEntityById(id);
        commentRepository.delete(comment);
        log.info("Comment with ID {} deleted successfully.", id);

    }

    @Override
    public Comment getEntityById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " does not exists"));
    }



    @Override
    public List<CommentResponseDto> getCommentsByNewsId(Long newsId) {
        List<Comment> comments = commentRepository.findByNews_Id(newsId);
        log.info("Retrieved {} comments for News with ID {}.", comments.size(), newsId);

        if (comments.isEmpty()) {
            return Collections.singletonList(new CommentResponseDto(null, null, "No comments for this news", null));
        }

        return comments.stream()
                .map(this::mapCommentToDto)
                .collect(Collectors.toList());
    }


    private CommentResponseDto mapCommentToDto(Comment comment) {
        return CommentResponseDto.builder()
                .news_id(comment.getNews().getId())
                .user_id(comment.getUser().getId())
                .text(comment.getText())
                .comment_date(comment.getCommentDate())
                .build();
    }


}
