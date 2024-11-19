package com.xorigin.doctorappointmentmanagementsystem.core.filefields.converters;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.storage.FlexStorageAdapter;
import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;

@Converter(autoApply = true)
public class StorageAwareMultipartFileConverter implements AttributeConverter<StorageAwareMultipartFile, String> {

    private final FlexStorageAdapter flexStorageAdapter;

    public StorageAwareMultipartFileConverter(FlexStorageAdapter flexStorageAdapter) {
        this.flexStorageAdapter = flexStorageAdapter;
    }

    @Override
    public String convertToDatabaseColumn(StorageAwareMultipartFile storageAwareMultipartFile) {
        try {
            return flexStorageAdapter.storeFile(storageAwareMultipartFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StorageAwareMultipartFile convertToEntityAttribute(String identifier) {
        return flexStorageAdapter.loadFileAsStorageAwareMultipartFile(identifier);
    }

}
