package com.xorigin.doctorappointmentmanagementsystem.core.configs;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.ControllerOptions;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.CrudControllerOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ControllerOptionsConfig {

    @Bean
    @Primary
    public ControllerOptions defaultPaginatedOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(true)
                .build();
    }

    @Bean
    public ControllerOptions readOnlyPaginatedOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(true)
                .isCreateAllowed(false)
                .isUpdateAllowed(false)
                .isDeleteAllowed(false)
                .build();
    }

    @Bean
    public ControllerOptions defaultUnPaginatedOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(false)
                .build();
    }

    @Bean
    public ControllerOptions readOnlyUnPaginatedOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(false)
                .isCreateAllowed(false)
                .isUpdateAllowed(false)
                .isDeleteAllowed(false)
                .build();
    }

    @Bean
    public ControllerOptions findOneOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(false)
                .isFindAllAllowed(false)
                .isCreateAllowed(false)
                .isUpdateAllowed(false)
                .isDeleteAllowed(false)
                .build();
    }

    @Bean
    public ControllerOptions createOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(false)
                .isFindAllAllowed(false)
                .isFindOneAllowed(false)
                .isUpdateAllowed(false)
                .isDeleteAllowed(false)
                .build();
    }

    @Bean
    public ControllerOptions updateOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(false)
                .isFindAllAllowed(false)
                .isFindOneAllowed(false)
                .isCreateAllowed(false)
                .isDeleteAllowed(false)
                .build();
    }

    @Bean
    public ControllerOptions deleteOptions() {
        return CrudControllerOptions
                .builder()
                .isPaginationEnabled(false)
                .isFindAllAllowed(false)
                .isFindOneAllowed(false)
                .isCreateAllowed(false)
                .isUpdateAllowed(false)
                .build();
    }

}
