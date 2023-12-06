package portal.news.services;

import portal.news.dto.translate.TranslateNewsDto;

import java.util.List;
/*
API_KEY :y0_AgAAAABoJlVbAATuwQAAAADy5ta6NZGvu_5MTTeqEhGGtLhTKYx-bVM

 */

public interface TranslationService {
    List<TranslateNewsDto> translate(String fromLang, String toLang, String data);
}
