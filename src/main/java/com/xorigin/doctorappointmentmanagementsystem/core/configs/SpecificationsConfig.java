package com.xorigin.doctorappointmentmanagementsystem.core.configs;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.GenericSpecification;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.Specification;

@Configuration
public class SpecificationsConfig {

    @Bean
    public <T> Specification<T> getSpec() {
        return new GenericSpecification<>();
    }

}
