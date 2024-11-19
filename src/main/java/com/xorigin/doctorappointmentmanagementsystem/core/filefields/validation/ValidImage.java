package com.xorigin.doctorappointmentmanagementsystem.core.filefields.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {ImageValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidImage {

    String message() default "Invalid image file";

    long maxSize() default 5242880; // Default 5 MB

    String[] allowedExtensions() default {"jpeg", "png", "jpg"};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
