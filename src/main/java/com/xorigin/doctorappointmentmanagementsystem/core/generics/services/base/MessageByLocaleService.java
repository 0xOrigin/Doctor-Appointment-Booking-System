package com.xorigin.doctorappointmentmanagementsystem.core.generics.services.base;

import org.springframework.context.MessageSource;

import java.util.Locale;

public interface MessageByLocaleService {

    MessageSource getMessageSource();

    Locale getLocale();

    String getMessage(String identifier);

    String getMessage(String identifier, Object... args);

}
