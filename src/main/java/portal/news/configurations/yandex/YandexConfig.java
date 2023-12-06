package portal.news.configurations.yandex;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "yandex")
@Getter
@Setter
public class YandexConfig {
    private String KEY;
}
