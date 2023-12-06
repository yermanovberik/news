package portal.news.services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portal.news.dto.file.FileRequestDto;
import portal.news.dto.file.FileResponseDto;
import portal.news.models.File;

public interface FileService extends CrudService<File, FileRequestDto, FileResponseDto> {


}
