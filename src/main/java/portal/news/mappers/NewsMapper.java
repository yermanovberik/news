package portal.news.mappers;

import org.mapstruct.Mapper;
import portal.news.dto.news.NewsRequestDto;
import portal.news.dto.news.NewsResponseDto;
import portal.news.models.News;


@Mapper(componentModel = "spring")
public interface NewsMapper extends Mappable<News, NewsRequestDto, NewsResponseDto> {

}
