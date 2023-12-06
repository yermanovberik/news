package portal.news.services.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import portal.news.dto.file.FileRequestDto;
import portal.news.dto.file.FileResponseDto;
import portal.news.exceptions.entity.EntityAlreadyExistsException;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.mappers.FileMapper;
import portal.news.models.File;
import portal.news.repositories.FileRepository;
import portal.news.services.FileService;
import portal.news.services.StorageService;
import portal.news.utils.FileUtils;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final StorageService storageService;
    private final FileMapper fileMapper;

    private final String FILE_FORMAT = "file/%s/%s";
    private int counter = 0;

    @Override
    public FileResponseDto getById(long id) {
        log.info("Retrieving File by ID: {}", id);

        File file = getEntityById(id);
        FileResponseDto responseDto = fileMapper.toDto(file);

        log.info("Finished retrieving File by ID: {}", file.getId());

        return responseDto;
    }

    @Override
    @Transactional
    public FileResponseDto create(FileRequestDto requestDto) {
        log.info("Creating File");
        MultipartFile multipartFile = requestDto.file();
        String filePath = String.format(
                FILE_FORMAT,
                counter,
                FileUtils.generateFileName(multipartFile)
        );
        counter+=1;

        throwExceptionIfFileExists(filePath);
        filePath = storageService.uploadFile(
                filePath,
                FileUtils.getInputStreamOrElseThrow(multipartFile)
        );
        File file = fileMapper.toEntity(requestDto);
        file.setPath(filePath);

        file = fileRepository.save(file);
        FileResponseDto responseDto = fileMapper.toDto(file);

        return responseDto;
    }



    @Override
    public FileResponseDto update(long id, FileRequestDto requestDto) {
        return null;
    }

    @Override
    @Transactional
    public void delete(long id) {
        log.info("Deleting File with ID: {}", id);

        File file = getEntityById(id);
        fileRepository.delete(file);

        storageService.deleteFile(file.getPath());

        log.info("Deleted File with ID: {}", file.getId());
    }


    @Override
    public File getEntityById(long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "File with ID " + id + " does not exist"
                ));
    }

    private void throwExceptionIfFileExists(String path) {
        fileRepository.findByPath(path)
                .ifPresent(foundFile -> {
                    throw new EntityAlreadyExistsException(
                            "File with the path '" + foundFile.getPath() + "' already exists"
                    );
                });
    }
}
