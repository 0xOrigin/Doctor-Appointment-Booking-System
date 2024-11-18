package com.xorigin.doctorappointmentmanagementsystem.core.filefields;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileSystemStorage implements FlexStorageAdapter {

    private final String baseDir;
    private final String urlPrefix;

    public FileSystemStorage(
            @Value("${flexstorage.base-dir:}") final String baseDir,
            @Value("${flexstorage.url-prefix:}") final String urlPrefix
    ) {
        this.baseDir = baseDir;
        this.urlPrefix = urlPrefix;
        System.out.println(baseDir + urlPrefix);
    }

    @Override
    public String storeFile(StorageAwareMultipartFile file, String path) throws IOException {
        if (file == null)
            return null;

        if (file.getUrl() != null)
            return file.getUrl();

        if (file.getDelegate() == null)
            return null;


        Path storagePath = Paths.get(baseDir, path);
        Files.createDirectories(storagePath);

        Path filePath = storagePath.resolve(getFileName(file));
        Files.write(filePath, file.getBytes());
//        Path relativePath = filePath.relativize(Paths.get(baseDir));
//        System.out.println(relativePath.toAbsolutePath());
//        System.out.println(relativePath.toString());
        file.setUri(getUriPrefix());
        file.setFilePath(filePath.toString());
        return filePath.toString();
    }

    @Override
    public StorageAwareMultipartFile loadFileAsStorageAwareMultipartFile(String identifier) throws IOException {
        if (identifier == null || identifier.isEmpty())
            return null;

        Path filePath = Paths.get(baseDir, identifier);

        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + identifier);
        }

        return new StorageAwareMultipartFile(getUriPrefix(), filePath.toString());
    }

    @Override
    public Boolean deleteFile(String identifier) throws IOException {
        Path path = Paths.get(baseDir, identifier);
        return Files.deleteIfExists(path);
    }

    @Override
    public String getUriPrefix() {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .replacePath(null)
                .build()
                .toUriString();
    }

    @Override
    public String getFileName(MultipartFile file) throws IOException {
        return System.currentTimeMillis() + "-" + Objects.requireNonNull(file.getOriginalFilename());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String urlPrefix = "/" + this.urlPrefix + "/**";
        String baseDir = "file:/" + this.baseDir + "/";
        System.out.println(urlPrefix + " " + baseDir);
        registry.addResourceHandler(urlPrefix)
                .addResourceLocations("file:uploads/");
    }

}
