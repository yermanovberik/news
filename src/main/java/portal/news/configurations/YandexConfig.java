package portal.news.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "yandex")
public class YandexConfig {
    private String KEY;
}
