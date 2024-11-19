package com.xorigin.doctorappointmentmanagementsystem.core.filefields.validation;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class ImageValidator implements ConstraintValidator<ValidImage, StorageAwareMultipartFile> {

    private long maxSize;
    private String[] allowedExtensions;

    @Override
    public void initialize(ValidImage constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
        this.allowedExtensions = Arrays.stream(constraintAnnotation.allowedExtensions()).map(String::toLowerCase).toArray(String[]::new);
    }

    @Override
    public boolean isValid(StorageAwareMultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty())
            return false;

        if (file.getSize() > maxSize)
            return false;

        String extension = getExtension(file.getOriginalFilename());
        return Arrays.asList(allowedExtensions).contains(extension);
    }

    private String getExtension(String filename) {
        if (filename != null && filename.contains("."))
            return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();

        return "";
    }

}
