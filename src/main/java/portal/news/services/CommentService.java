package portal.news.services;

import portal.news.dto.comment.CommentRequestDto;
import portal.news.dto.comment.CommentResponseDto;
import portal.news.models.Comment;

import java.util.List;

public interface CommentService extends CrudService<Comment, CommentRequestDto, CommentResponseDto> {

    List<CommentResponseDto> getCommentsByNewsId(Long newsId);
}
