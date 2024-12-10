package com.xorigin.doctorappointmentmanagementsystem.core.filefields.listeners;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.storage.FlexStorageAdapter;
import jakarta.persistence.PreRemove;

import java.io.IOException;
import java.lang.reflect.Field;

// TODO: handle file deletion on update
public class FileDeletionListener {

    private final FlexStorageAdapter flexStorageAdapter;

    public FileDeletionListener(FlexStorageAdapter flexStorageAdapter) {
        this.flexStorageAdapter = flexStorageAdapter;
    }

    @PreRemove
    public void preRemove(Object entity) throws IOException, IllegalAccessException {
        for(Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value instanceof StorageAwareMultipartFile) {
                flexStorageAdapter.deleteFile(((StorageAwareMultipartFile) value).getFilePath());
            }
        }
    }

}
