package portal.news.dto.news;

import java.time.LocalDateTime;

public record NewsRequestDto(
        String newsText,
        String tittle,

        LocalDateTime publicationDate
) {
}
