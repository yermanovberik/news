package portal.news.services;

import portal.news.dto.news.NewsRequestDto;
import portal.news.dto.news.NewsResponseDto;
import portal.news.models.News;

public interface NewsService extends CrudService<News, NewsRequestDto, NewsResponseDto> {
}
