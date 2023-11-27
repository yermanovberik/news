package portal.news.services.implementation;
/*
import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import portal.news.exceptions.entity.EntityNotFoundException;
import portal.news.exceptions.server.InternalServerErrorException;
import portal.news.services.StorageService;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class MinioService implements StorageService {
    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;


    @Override
    public String uploadFile(String filePath, InputStream inputStream) {
        try {
            log.info("Uploading file with file name: {}", filePath);
            ObjectWriteResponse objectWriteResponse = minioClient.putObject(
                    PutObjectArgs.builder()
                            .stream(inputStream, inputStream.available(), -1)
                            .bucket(bucketName)
                            .object(filePath)
                            .build()
            );

            log.info("Uploaded file: {}", filePath);

            return objectWriteResponse.object();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to upload file: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] getFile(String filePath) {
        try {
            throwExceptionIfFileDoesNotExists(filePath);

            log.info("Downloading file with file name: {}", filePath);

            GetObjectResponse getObjectResponse = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filePath)
                            .build()
            );

            log.info("Downloaded file: {}", filePath);

            return getObjectResponse.readAllBytes();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to download file: " + e.getMessage(), e);
        }
    }

    @Override
    public String renameFile(String oldPath, String newPath) {
        try {
            throwExceptionIfFileDoesNotExists(oldPath);

            log.info("Renaming file from {} to {}", oldPath, newPath);

            ObjectWriteResponse objectWriteResponse = minioClient.copyObject(
                    CopyObjectArgs.builder()
                            .bucket(bucketName)
                            .object(oldPath)
                            .source(
                                    CopySource.builder()
                                            .bucket(bucketName)
                                            .object(newPath)
                                            .build()
                            )
                            .build()
            );

            deleteFile(oldPath);

            log.info("File renamed from {} to {}", oldPath, newPath);

            return objectWriteResponse.object();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to rename file: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteFile(String path) {
        try {
            throwExceptionIfFileDoesNotExists(path);

            log.info("Deleting file with file name: {}", path);

            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(path)
                            .build()
            );

            log.info("Deleted file: {}", path);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to delete file: " + e.getMessage(), e);
        }
    }

    private void throwExceptionIfFileDoesNotExists(String fileName) throws Exception {
        StatObjectResponse statObjectResponse = minioClient.statObject(
                StatObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );

        if (statObjectResponse == null) {
            throw new EntityNotFoundException("File with name " + fileName + " does not exist");
        }
    }

    @PostConstruct
    private void createBucketIfDoesNotExists() throws Exception {

        boolean isBucketExists = minioClient.bucketExists(
                BucketExistsArgs.builder()
                        .bucket(bucketName)
                        .build()
        );

        if (!isBucketExists) {
            minioClient.makeBucket(
                    MakeBucketArgs.builder()
                            .bucket(bucketName)
                            .build()
            );

            log.warn("Created new backed with name: {}", bucketName);
        }
    }
}
*/