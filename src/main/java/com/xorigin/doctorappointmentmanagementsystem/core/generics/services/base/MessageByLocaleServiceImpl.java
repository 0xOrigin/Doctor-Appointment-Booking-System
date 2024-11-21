package com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageByLocaleServiceImpl implements MessageByLocaleService{

    private final MessageSource messageSource;

    public MessageByLocaleServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Override
    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    @Override
    public String getMessage(String identifier) {
        return messageSource.getMessage(identifier, null, getLocale());
    }

    @Override
    public String getMessage(String identifier, Object... args) {
        return messageSource.getMessage(identifier, args, getLocale());
    }

}
