package com.xorigin.doctorappointmentmanagementsystem.core.configs;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.CrudControllerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerUtilsConfig {

    @Bean
    public ControllerUtils controllerUtils() {
        return new CrudControllerUtils();
    }

}
