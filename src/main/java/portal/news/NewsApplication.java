package portal.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import portal.news.configurations.properties.MinioProperties;
import portal.news.configurations.yandex.YandexConfig;
import portal.news.producer.RabbitMQProducer;
import portal.news.services.TranslationService;

@SpringBootApplication
@ConfigurationPropertiesScan("portal.news.configurations.properties")
@EnableConfigurationProperties({YandexConfig.class, MinioProperties.class})
@EnableFeignClients
public class NewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

}
