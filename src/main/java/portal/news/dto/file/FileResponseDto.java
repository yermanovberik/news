package portal.news.dto.file;

import lombok.Builder;

@Builder
public record FileResponseDto (
        long id,
        String path
) { }
