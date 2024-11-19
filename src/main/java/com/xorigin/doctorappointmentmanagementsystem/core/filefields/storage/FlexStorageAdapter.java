package com.xorigin.doctorappointmentmanagementsystem.core.filefields.storage;

import com.xorigin.doctorappointmentmanagementsystem.core.filefields.StorageAwareMultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.io.IOException;

public interface FlexStorageAdapter {

    /**
     * Stores the given StorageAwareMultipartFile and returns the path or identifier where the file is stored.
     *
     * @param file The file to store.
     * @return The path or identifier of the stored file.
     * @throws IOException if an error occurs during file storage.
     */
    String storeFile(StorageAwareMultipartFile file) throws IOException;

    /**
     * Retrieves the file as a StorageAwareMultipartFile based on the given path or identifier.
     *
     * @param identifier The path or identifier of the stored file.
     * @return The file as a StorageAwareMultipartFile.
     */
    StorageAwareMultipartFile loadFileAsStorageAwareMultipartFile(String identifier);

    /**
     * Deletes the file based on the given path or identifier.
     *
     * @param identifier The path or identifier of the file to delete.
     * @return True if the file was successfully deleted, false otherwise.
     * @throws IOException if an error occurs during deletion.
     */
    Boolean deleteFile(String identifier) throws IOException;

    /**
     * Gets the file name for the given StorageAwareMultipartFile.
     *
     * @param file The StorageAwareMultipartFile to get the name for.
     * @return The new file name.
     */
    String getFileName(StorageAwareMultipartFile file);

    /**
     * Gets the URI prefix for the file storage.
     *
     * @return The URI prefix for the file storage.
     */
    String getUriPrefix();

    /**
     * Adds resource handlers to the given ResourceHandlerRegistry.
     *
     * @param registry The ResourceHandlerRegistry to add resource handlers to.
     */
    default void addResourceHandlers(ResourceHandlerRegistry registry) {}

}
