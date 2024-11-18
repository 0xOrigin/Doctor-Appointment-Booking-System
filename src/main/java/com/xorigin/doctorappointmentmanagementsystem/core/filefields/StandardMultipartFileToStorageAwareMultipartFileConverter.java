package com.xorigin.doctorappointmentmanagementsystem.core.filefields;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

public class StandardMultipartFileToStorageAwareMultipartFileConverter implements Converter<MultipartFile, StorageAwareMultipartFile> {

    @Override
    public StorageAwareMultipartFile convert(MultipartFile source) {
        return new StorageAwareMultipartFile(source);
    }

}
