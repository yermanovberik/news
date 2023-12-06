package portal.news.dto.comment;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;
@Builder
public record CommentResponseDto(
        Long news_id,
        Long user_id,
        String text,
        LocalDateTime comment_date
) {
}
