package portal.news.requests;

import java.util.List;

public interface YandexResponse {
    record Response(List<ResponseRow> translations){}
    record ResponseRow(String text, String detectedLanguageCode){}
}
