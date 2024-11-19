package com.xorigin.doctorappointmentmanagementsystem.core.filefields.aop;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.annotations.UploadLocation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class UploadLocationAspect {

    @Before("execution(* org.springframework.data.repository.CrudRepository.save(..)) && args(entity)")
    public void setUploadLocation(Object entity) {
        if (entity == null)
            return;

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(UploadLocation.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(entity);
                    if (value instanceof StorageAwareMultipartFile) {
                        UploadLocation uploadLocation = field.getAnnotation(UploadLocation.class);
                        ((StorageAwareMultipartFile) value).setCustomLocation(uploadLocation.value());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Unable to access field during aspect processing", e);
                }
            }
        }
    }

}
