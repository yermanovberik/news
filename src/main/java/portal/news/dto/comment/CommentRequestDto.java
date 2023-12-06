package portal.news.dto.comment;

public record CommentRequestDto(
        String text,
        Long news_id,
        Long user_id
) {
}
