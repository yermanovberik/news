package portal.news.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import portal.news.dto.file.FileRequestDto;
import portal.news.dto.file.FileResponseDto;
import portal.news.services.FileService;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {

    private final FileService fileService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FileResponseDto getFileById(
            @PathVariable @Positive(message = "Id must be greater than zero") long id
    ) {
        return fileService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileResponseDto createFile(
            @ModelAttribute @Valid FileRequestDto fileRequestDto
    ) {
        return fileService.create(fileRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FileResponseDto updateFile(
            @PathVariable @Positive(message = "Id must be positive") long id,
            @ModelAttribute @Valid FileRequestDto fileRequestDto
    ) {
        return fileService.update(id, fileRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFile(
            @PathVariable @Positive(message = "Id must be greater than zero") long id
    ) {
        fileService.delete(id);
    }

}
