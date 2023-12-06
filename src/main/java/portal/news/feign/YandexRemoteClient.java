package portal.news.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import portal.news.configurations.yandex.FeignConfig;
import portal.news.requests.YandexRequest;
import portal.news.requests.YandexResponse;

@FeignClient(
        name = "yandexRemoteClient",
        url = "https://translate.api.cloud.yandex.net/translate/v2",
        configuration = FeignConfig.class
)
public interface YandexRemoteClient {
    @PostMapping(value = "/translate")
    YandexResponse.Response fetchTranslate(@RequestBody YandexRequest.Request data);
}
