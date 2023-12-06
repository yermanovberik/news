package portal.news.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import portal.news.dto.comment.CommentRequestDto;
import portal.news.dto.comment.CommentResponseDto;
import portal.news.services.CommentService;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto getCommentById(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        return commentService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(
            @RequestBody @Valid CommentRequestDto requestDto
    ) {
       return commentService.create(requestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentResponseDto updateComment(
            @PathVariable @Positive(message = "Id must be positive") long id,
            @RequestBody @Valid CommentRequestDto requestDto
    ) {
        return commentService.update(id ,requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        commentService.delete(id);
    }

    @GetMapping("/news/{newsId}")
    public List<CommentResponseDto> getCommentsByNewsId(
            @PathVariable Long newsId
    ) {
        return commentService.getCommentsByNewsId(newsId);
    }

}
