package com.xorigin.doctorappointmentmanagementsystem.core.filefields;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class StorageAwareMultipartFile implements MultipartFile, Serializable {

    private transient final MultipartFile delegate;
    private String uri;
    private String filePath;

    public StorageAwareMultipartFile(MultipartFile file) {
        this.delegate = file;
        this.uri = null;
        this.filePath = null;
    }

    public StorageAwareMultipartFile(String filePath) {
        this.delegate = null;
        this.uri = null;
        this.filePath = filePath;
    }

    public StorageAwareMultipartFile(String uri, String filePath) {
        this.delegate = null;
        this.uri = uri;
        this.filePath = filePath;
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public String getOriginalFilename() {
        return delegate.getOriginalFilename();
    }

    @Override
    public String getContentType() {
        return delegate.getContentType();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public long getSize() {
        return delegate.getSize();
    }

    @Override
    public byte[] getBytes() throws IOException {
        return delegate.getBytes();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return delegate.getInputStream();
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        delegate.transferTo(dest);
    }

    public MultipartFile getDelegate() {
        return delegate;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    @JsonValue
    public String getUrl() {
        if (uri == null || uri.isEmpty() || filePath == null || filePath.isEmpty())
            return null;

        return uri + "/" + filePath;
    }

}
