package portal.news.dto.file;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;
@Builder
public record FileRequestDto (

        @NotNull(message = "File cannot be null")
        MultipartFile file

) { }
