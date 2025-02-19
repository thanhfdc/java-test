package com.metasol.utils;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class I18n {
    private static final Logger log = LoggerFactory.getLogger(I18n.class);
    private static MessageSource source;
    @Autowired
    private MessageSource messageSource;

    public I18n() {
    }

    public static String get(Locale locale, String code, @Nullable Object[] args) {
        try {
            return source.getMessage(code, args, locale);
        } catch (Exception var4) {
            log.info(var4.getMessage());
            return code;
        }
    }

    public static String get(String code, @Nullable Object... args) {
        return get(LocaleContextHolder.getLocale(), code, args);
    }

    @PostConstruct
    private void setMessageSource() {
        source = this.messageSource;
    }
}
