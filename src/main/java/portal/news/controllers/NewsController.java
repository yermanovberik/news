package portal.news.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portal.news.dto.news.NewsPhotoResponseDto;
import portal.news.dto.news.NewsRequestDto;
import portal.news.dto.news.NewsResponseDto;
import portal.news.dto.translate.TranslateNewsDto;
import portal.news.models.News;
import portal.news.services.NewsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@ResponseBody
@RequestMapping(value = "/api/v1/news")
public class NewsController {

    private final NewsService newsService;


    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsResponseDto getByNewsId(@PathVariable("id") long id){
        return newsService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsResponseDto createNews(
            @RequestBody NewsRequestDto requestDto
    ){
      return newsService.create(requestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NewsResponseDto updateNews(
            @PathVariable @Positive(message = "Id must be positive") long id,
            @RequestBody @Valid NewsRequestDto requestDto
    ) {
        return newsService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        newsService.delete(id);
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<News>> getNewsByCategory(@PathVariable Long categoryId) {
        List<News> categoryNews = newsService.getAllNewsForCategory(categoryId);
        return ResponseEntity.ok(categoryNews);
    }

    @GetMapping("/{userId}/{newsId}")
    public TranslateNewsDto translateNews(@PathVariable Long userId, @PathVariable Long newsId) {
        return newsService.translateNews(userId, newsId);
    }

    @PostMapping("/{id}/add-photo")
    @ResponseStatus(HttpStatus.OK)
    public NewsPhotoResponseDto addNewsPhoto(
            @PathVariable Long id,
            @RequestParam("avatarFile") MultipartFile avatarFile
    ) {
       return newsService.addNewsPhoto(id, avatarFile);
    }

}
