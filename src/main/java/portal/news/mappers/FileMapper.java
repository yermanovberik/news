package portal.news.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import portal.news.dto.file.FileRequestDto;
import portal.news.dto.file.FileResponseDto;
import portal.news.models.File;

@Mapper(componentModel = "spring")
public interface FileMapper extends Mappable<File, FileRequestDto, FileResponseDto> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "path", ignore = true)
    File toEntity(FileRequestDto request);
}
