package portal.news.dto.news;


import org.springframework.web.multipart.MultipartFile;

public record NewsRequestDto(
        String newsText,
        String tittle,
        String categoryName
) {
}
