package portal.news.services.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import portal.news.dto.file.FileRequestDto;
import portal.news.dto.file.FileResponseDto;
import portal.news.dto.news.NewsPhotoResponseDto;
import portal.news.dto.news.NewsRequestDto;
import portal.news.dto.news.NewsResponseDto;
import portal.news.dto.translate.TranslateNewsDto;
import portal.news.mappers.NewsMapper;
import portal.news.models.Category;
import portal.news.models.News;
import portal.news.models.User;
import portal.news.repositories.CategoryRepository;
import portal.news.repositories.NewsRepository;
import portal.news.repositories.UserRepository;
import portal.news.services.FileService;
import portal.news.services.NewsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final UserRepository userRepository;
    private final NewsTranslationService translationService;
    private final CategoryRepository categoryRepository;
    private final YandexTranslationService yandexTranslationService;
    private final FileService fileService;

    @Override
    public NewsResponseDto getById(long id) {
        News news = getEntityById(id);
        NewsResponseDto responseDto = newsMapper.toDto(news);
        return responseDto;
    }


    @Override
    @Transactional
    public NewsResponseDto create(NewsRequestDto requestDto) {
        Category category;
        Optional<Category> existingCategory = categoryRepository.findByName(requestDto.categoryName());
        if (existingCategory.isPresent()) {
            category = existingCategory.get();
        } else {
            category = Category.builder()
                    .name(requestDto.categoryName())
                    .build();
            categoryRepository.save(category);
        }
        News news = News.builder()
                .tittle(requestDto.tittle())
                .newsText(requestDto.newsText())
                .publicationDate(LocalDateTime.now())
                .category(category)
                .build();


        news = newsRepository.save(news);
        NewsResponseDto responseDto = newsMapper.toDto(news);

        log.info("News created successfully. ID: {}", news.getId());

        return responseDto;
    }

    @Override
    @Transactional
    public NewsResponseDto update(long id, NewsRequestDto requestDto) {
        News news = getEntityById(id);

        news.setNewsText(requestDto.newsText());
        news.setTittle(requestDto.tittle());

        NewsResponseDto responseDto = newsMapper.toDto(news);
        log.info("News with ID {} updated successfully.", id);

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
                .orElseThrow(() -> new portal.news.exceptions.entity.EntityNotFoundException("News with ID " + id + " does not exist"));
    }

    public TranslateNewsDto translateNews(@PathVariable Long userId, @PathVariable Long newsId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " does not exist"));

        News news = getEntityById(newsId);

        TranslateNewsDto translatedNewsDTO = new TranslateNewsDto();

        if (user.isSubscription_paid()) {
            translationService.translateNewsBySubscription(user, news);
            List<TranslateNewsDto> translateNewsDtoList = yandexTranslationService.translate("en", "ru", news.getNewsText());
            if (!translateNewsDtoList.isEmpty()) {
                translatedNewsDTO = translateNewsDtoList.get(0);
            }
        } else {
            translatedNewsDTO.setTranslatedText("News translation is not available for users without a paid subscription");
        }
        log.info("News translation performed successfully for User ID: {}, News ID: {}", userId, newsId);

        return translatedNewsDTO;
    }

    @Override
    @Transactional
    public NewsPhotoResponseDto addNewsPhoto(Long id, MultipartFile avatarFile) {
        log.info("Creating User with Avatar");
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new portal.news.exceptions.entity.EntityNotFoundException("News with id " + id + " does not exists"));
        FileRequestDto fileRequestDto = FileRequestDto.builder()
                .file(avatarFile)
                .build();
        FileResponseDto fileResponseDto = fileService.create(fileRequestDto);
        System.out.println(fileResponseDto);
        news.setPhotoUrl(fileResponseDto.path());

        news = newsRepository.save(news);

        log.info("News photo created with ID: {}", news.getId());
        NewsPhotoResponseDto responseDto = new NewsPhotoResponseDto(fileResponseDto.path());
        return responseDto;
    }

    public List<News> getAllNewsForCategory(Long categoryId){
        log.info("Retrieved all news for Category ID: {}", categoryId);

        return newsRepository.findByCategory_Id(categoryId);
    }

}
