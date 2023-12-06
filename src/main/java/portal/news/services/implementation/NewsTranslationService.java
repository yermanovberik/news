package portal.news.services.implementation;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import portal.news.models.News;
import portal.news.models.User;
import portal.news.services.TranslationService;

@Service
@RequiredArgsConstructor
public class NewsTranslationService {
    private final TranslationService translationService;

    public void translateNewsBySubscription(User user, News news) {
        if (user.isSubscription_paid()) {
            translateNews(news);
        } else {
            System.out.println("News translation is not available for users without a paid subscription.");
        }
    }
    private void translateNews(News news) {
        translationService.translate("en", "ru", news.getNewsText())
                .forEach(System.out::println);
    }
}
