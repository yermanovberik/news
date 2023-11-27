package portal.news.services.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.news.dto.news.NewsRequestDto;
import portal.news.dto.news.NewsResponseDto;
import portal.news.mappers.NewsMapper;
import portal.news.models.News;
import portal.news.repositories.NewsRepository;
import portal.news.services.NewsService;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public NewsResponseDto getById(long id) {
        News news = getEntityById(id);
        NewsResponseDto responseDto = newsMapper.toDto(news);
        System.out.println(news);
        System.out.println(responseDto);
        return responseDto;
    }


    @Override
    @Transactional
    public NewsResponseDto create(NewsRequestDto requestDto) {
        News news = newsMapper.toEntity(requestDto);
        news = newsRepository.save(news);

        NewsResponseDto responseDto = newsMapper.toDto(news);

        return responseDto;
    }

    @Override
    @Transactional
    public NewsResponseDto update(long id, NewsRequestDto requestDto) {
        News news = getEntityById(id);

        news.setNewsText(requestDto.newsText());
        news.setTittle(requestDto.tittle());
        news.setPublicationDate(requestDto.publicationDate());

        NewsResponseDto responseDto = newsMapper.toDto(news);

        return responseDto;
    }

    @Override
    @Transactional
    public void delete(long id) {
        News news = getEntityById(id);
        newsRepository.delete(news);
    }

    @Override
    public News getEntityById(long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News with ID " + id + " does not exist"));
    }


}
