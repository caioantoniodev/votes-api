package edu.mentorship.votes.application.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;

import java.util.Locale;

public interface EndpointsTranslator {
    MessageSource getMessageSource();

    default String getMessage(String codeMessage, Locale locale, String ...args) {
        return getMessageSource().getMessage(codeMessage, args, locale);
    }

    default Locale getLocale(String language) {
        if (StringUtils.isBlank(language) || "*".equals(language)) {
            return Locale.US;
        }

        if (language.contains(",")) {
            var languages = language.split(",");

            return Locale.forLanguageTag(languages[0]);
        }

        return Locale.forLanguageTag(language);
    }
}
