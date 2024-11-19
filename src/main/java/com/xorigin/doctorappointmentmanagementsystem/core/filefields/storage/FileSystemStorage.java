package com.xorigin.doctorappointmentmanagementsystem.core.filefields.storage;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileSystemStorage implements FlexStorageAdapter {

    private final String urlPrefix;
    private final String baseDir;

    public FileSystemStorage(
            @Value("${flexstorage.url-prefix:media/}") final String urlPrefix,
            @Value("${flexstorage.base-dir:media/}") final String baseDir
    ) {
        this.urlPrefix = cleanUrlPrefix(urlPrefix);
        this.baseDir = cleanBaseDir(baseDir);

        try {
            Files.createDirectories(Paths.get(baseDir));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String cleanUrlPrefix(String urlPrefix) {
        if (urlPrefix.endsWith("/"))
            urlPrefix = urlPrefix.substring(0, urlPrefix.length() - 1);
        return urlPrefix.replaceFirst("/", "");
    }

    private String cleanBaseDir(String baseDir) {
        if (baseDir.endsWith("/") || baseDir.endsWith("\\"))
            baseDir = baseDir.substring(0, baseDir.length() - 1);
        baseDir = baseDir.replaceFirst("/", "");
        baseDir = baseDir.replaceFirst("\\\\", "");
        return baseDir;
    }

    @Override
    public String storeFile(StorageAwareMultipartFile file) throws IOException {
        if (file == null)
            return null;

        if (file.getFilePath() != null)
            return file.getFilePath();

        if (file.getDelegate() == null)
            return null;

        Path storagePath = Paths.get(baseDir, file.getCustomLocation());
        Files.createDirectories(storagePath);

        Path filePath = storagePath.resolve(getFileName(file));
        Files.write(filePath, file.getBytes());

        filePath = Paths.get(baseDir).relativize(filePath);

        file.setUri(getUriPrefix());
        file.setFilePath(filePath.toString());
        return file.getFilePath();
    }

    @Override
    public StorageAwareMultipartFile loadFileAsStorageAwareMultipartFile(String identifier) {
        if (identifier == null || identifier.isEmpty())
            return null;

        Path filePath = Paths.get(identifier);
        return new StorageAwareMultipartFile(getUriPrefix(), filePath);
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
                .toUriString() + "/" + urlPrefix;
    }

    @Override
    public String getFileName(StorageAwareMultipartFile file) {
        return System.currentTimeMillis() + "-" + Objects.requireNonNull(file.getOriginalFilename());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(urlPrefix + "/**")
                .addResourceLocations(Paths.get(baseDir).toUri().toString());
    }

}
