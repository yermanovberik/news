package portal.news.requests;

import java.util.List;


public interface YandexRequest {
    record Request(String sourceLanguageCode, String targetLanguageCode, String format, List<String> texts) {
        public Request(String sourceLanguageCode, String targetLanguageCode, List<String> texts) {
            this(sourceLanguageCode, targetLanguageCode, "PLAIN_TEXT", texts);
        }
    }
}