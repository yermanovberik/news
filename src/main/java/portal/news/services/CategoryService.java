package portal.news.services;

import portal.news.dto.category.CategoryRequestDto;
import portal.news.dto.category.CategoryResponseDto;
import portal.news.models.Category;

public interface CategoryService extends CrudService<Category, CategoryRequestDto, CategoryResponseDto> {
}
