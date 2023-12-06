package portal.news.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import portal.news.dto.comment.CommentRequestDto;
import portal.news.dto.comment.CommentResponseDto;
import portal.news.models.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper extends Mappable<Comment, CommentRequestDto , CommentResponseDto> {
/*
    @Mapping(target = "news_id", source = "news.id")
    @Mapping(target = "user_id", source = "user.id")
    @Mapping(target = "comment_date",source = "commentDate")
    CommentResponseDto toDto(Comment comment);

 */
}
