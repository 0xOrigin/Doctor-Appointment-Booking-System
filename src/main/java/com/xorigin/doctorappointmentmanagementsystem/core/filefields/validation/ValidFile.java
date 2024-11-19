package com.xorigin.doctorappointmentmanagementsystem.core.filefields.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {FileValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFile {

    String message() default "Invalid file";

    long maxSize() default 1048576; // Default 1 MB

    String[] allowedExtensions() default {}; // e.g., {"txt", "pdf"}

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
