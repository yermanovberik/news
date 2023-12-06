package portal.news.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import portal.news.dto.category.CategoryRequestDto;
import portal.news.dto.category.CategoryResponseDto;
import portal.news.services.CategoryService;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto getCategoryById(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto createCategory(
            @RequestBody @Valid CategoryRequestDto requestDto
    ) {
        return categoryService.create(requestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponseDto updateCategory(
            @PathVariable @Positive(message = "Id must be positive") long id,
            @RequestBody @Valid CategoryRequestDto requestDto
    ) {
        return categoryService.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(
            @PathVariable @Positive(message = "Id must be positive") long id
    ) {
        categoryService.delete(id);
    }


}
