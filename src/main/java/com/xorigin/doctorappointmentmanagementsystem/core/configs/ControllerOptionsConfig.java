package com.xorigin.doctorappointmentmanagementsystem.core.configs;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.CrudControllerOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerOptionsConfig {

    @Bean
    public ControllerOptions controllerOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(false)
                .build();
    }

}
