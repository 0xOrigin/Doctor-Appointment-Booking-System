package com.xorigin.doctorappointmentmanagementsystem.core.filefields.configs;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.storage.FlexStorageAdapter;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.converters.StandardMultipartFileToStorageAwareMultipartFileConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final FlexStorageAdapter flexStorageAdapter;

    public WebConfig(FlexStorageAdapter flexStorageAdapter) {
        this.flexStorageAdapter = flexStorageAdapter;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        flexStorageAdapter.addResourceHandlers(registry);
    }

    @Bean
    public StandardMultipartFileToStorageAwareMultipartFileConverter customMultipartFileConverter() {
        return new StandardMultipartFileToStorageAwareMultipartFileConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(customMultipartFileConverter());
    }

}
