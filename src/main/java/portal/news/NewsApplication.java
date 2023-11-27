package portal.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import portal.news.configurations.YandexConfig;

@SpringBootApplication
@ConfigurationPropertiesScan("portal.news.configurations.properties")
@EnableConfigurationProperties(YandexConfig.class)
@EnableFeignClients
public class NewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}

}
