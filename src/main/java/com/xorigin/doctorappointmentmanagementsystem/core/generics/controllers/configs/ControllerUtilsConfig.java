package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.configs;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.ControllerUtils;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base.CrudControllerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerUtilsConfig {

    @Bean
    public ControllerUtils controllerUtils() {
        return new CrudControllerUtils();
    }

}
