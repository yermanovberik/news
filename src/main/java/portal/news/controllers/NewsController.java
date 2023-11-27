package portal.news.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import portal.news.dto.news.NewsRequestDto;
import portal.news.dto.news.NewsResponseDto;
import portal.news.services.NewsService;

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
    public NewsResponseDto updateTeam(
            @PathVariable @Positive(message = "Id must be positive") long id,
            @RequestBody @Valid NewsRequestDto requestDto
    ) {
        return newsService.update(id, requestDto);
    }



}
