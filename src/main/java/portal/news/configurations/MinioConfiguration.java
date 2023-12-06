package portal.news.configurations;


import io.minio.MinioClient;
import io.minio.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfiguration {


    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials(
                        "minioadmin",
                        "minioadmin"
                )
                .build();
    }

}
