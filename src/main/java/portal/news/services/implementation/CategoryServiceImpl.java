package portal.news.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portal.news.dto.category.CategoryRequestDto;
import portal.news.dto.category.CategoryResponseDto;
import portal.news.exceptions.entity.EntityAlreadyExistsException;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.mappers.CategoryMapper;
import portal.news.models.Category;
import portal.news.repositories.CategoryRepository;
import portal.news.services.CategoryService;

import java.util.Objects;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto getById(long id) {
        log.info("Retrieving Category by ID: {}", id);

        Category category = getEntityById(id);
        CategoryResponseDto responseDto = categoryMapper.toDto(category);

        log.info("Finished retrieving Category by ID: {}", category.getId());

        return responseDto;
    }

    @Override
    @Transactional
    public CategoryResponseDto create(CategoryRequestDto requestDto) {
        log.info("Creating new Category with name: {}", requestDto.name());

        throwExceptionIfCategoryExists(requestDto.name());

        Category category = categoryMapper.toEntity(requestDto);
        category = categoryRepository.save(category);
        CategoryResponseDto responseDto = categoryMapper.toDto(category);

        log.info("Created new Category with ID: {}", category.getId());

        return responseDto;
    }

    @Override
    @Transactional
    public CategoryResponseDto update(long id, CategoryRequestDto categoryRequestDto) {
        log.info("Updating Category with ID: {}", id);

        Category category = getEntityById(id);

        String oldName = category.getName();
        String newName = categoryRequestDto.name();

        if (!Objects.equals(oldName, newName)) {
            throwExceptionIfCategoryExists(newName);
            category.setName(newName);
            category = categoryRepository.save(category);
        }

        CategoryResponseDto responseDto = categoryMapper.toDto(category);

        log.info("Updated Category with ID: {}", category.getId());

        return responseDto;
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info("Deleting Category with ID: {}", id);

        Category category = getEntityById(id);
        categoryRepository.delete(category);

        log.info("Deleted Category with ID: {}", category.getId());
    }

    @Override
    public Category getEntityById(long id) {
        return categoryRepository.findById(id)

                .orElseThrow(() -> new EntityNotFoundException("Category with ID : " + id + " does not exists."));
    }

    private void throwExceptionIfCategoryExists(String categoryName) {
        categoryRepository.findByName(categoryName)
                .ifPresent(foundCategory -> {
                    throw new EntityAlreadyExistsException(
                            "Category with the name " + foundCategory.getName() + " already exists"
                    );
                });
    }


}