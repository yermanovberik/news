package portal.news.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import portal.news.dto.category.CategoryRequestDto;
import portal.news.dto.category.CategoryResponseDto;
import portal.news.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends Mappable<Category, CategoryRequestDto, CategoryResponseDto> {

}

