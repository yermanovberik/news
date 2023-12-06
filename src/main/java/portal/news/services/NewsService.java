package portal.news.services;

import org.springframework.web.multipart.MultipartFile;
import portal.news.dto.avatar.AvatarResponseDto;
import portal.news.dto.news.NewsPhotoResponseDto;
import portal.news.dto.news.NewsRequestDto;
import portal.news.dto.news.NewsResponseDto;
import portal.news.dto.translate.TranslateNewsDto;
import portal.news.models.News;

import java.util.List;

public interface NewsService extends CrudService<News, NewsRequestDto, NewsResponseDto> {

    List<News> getAllNewsForCategory(Long categoryId);

    TranslateNewsDto translateNews(Long userId, Long newsId);
    NewsPhotoResponseDto addNewsPhoto(Long id, MultipartFile avatarFile);


}
