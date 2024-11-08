package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base;

import org.springframework.beans.factory.annotation.Value;

public class CrudControllerOptions implements ControllerOptions {

    @Value("${spring.data.web.pageable.default-page-size}")
    private Integer defaultPageSize;
    private Integer pageSize;

    private final boolean isPaginationEnabled;
    private final boolean isFindAllAllowed;
    private final boolean isFindOneAllowed;
    private final boolean isCreateAllowed;
    private final boolean isUpdateAllowed;
    private final boolean isPartialUpdateAllowed;
    private final boolean isDeleteAllowed;

    private CrudControllerOptions(Builder builder) {
        this.pageSize = builder.getPageSize();
        this.isPaginationEnabled = builder.getIsPaginationEnabled();
        this.isFindAllAllowed = builder.getIsFindAllAllowed();
        this.isFindOneAllowed = builder.getIsFindOneAllowed();
        this.isCreateAllowed = builder.getIsCreateAllowed();
        this.isUpdateAllowed = builder.getIsUpdateAllowed();
        this.isPartialUpdateAllowed = builder.getIsPartialUpdateAllowed();
        this.isDeleteAllowed = builder.getIsDeleteAllowed();
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public boolean isPaginationEnabled() {
        return isPaginationEnabled;
    }

    public boolean isFindAllAllowed() {
        return isFindAllAllowed;
    }

    public boolean isFindOneAllowed() {
        return isFindOneAllowed;
    }

    public boolean isCreateAllowed() {
        return isCreateAllowed;
    }

    public boolean isUpdateAllowed() {
        return isUpdateAllowed;
    }

    public boolean isPartialUpdateAllowed() {
        return isPartialUpdateAllowed;
    }

    public boolean isDeleteAllowed() {
        return isDeleteAllowed;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static class Builder {
        // Default values
        private Integer pageSize;
        private boolean isPaginationEnabled = true;
        private boolean isFindAllAllowed = true;
        private boolean isFindOneAllowed = true;
        private boolean isCreateAllowed = true;
        private boolean isUpdateAllowed = true;
        private boolean isPartialUpdateAllowed = true;
        private boolean isDeleteAllowed = true;

        public Builder pageSize(Integer pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder isPaginationEnabled(boolean isPaginationEnabled) {
            this.isPaginationEnabled = isPaginationEnabled;
            return this;
        }

        public Builder isFindAllAllowed(boolean isFindAllAllowed) {
            this.isFindAllAllowed = isFindAllAllowed;
            return this;
        }

        public Builder isFindOneAllowed(boolean isFindOneAllowed) {
            this.isFindOneAllowed = isFindOneAllowed;
            return this;
        }

        public Builder isCreateAllowed(boolean isCreateAllowed) {
            this.isCreateAllowed = isCreateAllowed;
            return this;
        }

        public Builder isUpdateAllowed(boolean isUpdateAllowed) {
            this.isUpdateAllowed = isUpdateAllowed;
            return this;
        }

        public Builder isPartialUpdateAllowed(boolean isPartialUpdateAllowed) {
            this.isPartialUpdateAllowed = isPartialUpdateAllowed;
            return this;
        }

        public Builder isDeleteAllowed(boolean isDeleteAllowed) {
            this.isDeleteAllowed = isDeleteAllowed;
            return this;
        }

        public Integer getPageSize() {
            return pageSize;
        }

        public boolean getIsPaginationEnabled() {
            return isPaginationEnabled;
        }

        public boolean getIsFindAllAllowed() {
            return isFindAllAllowed;
        }

        public boolean getIsFindOneAllowed() {
            return isFindOneAllowed;
        }

        public boolean getIsCreateAllowed() {
            return isCreateAllowed;
        }

        public boolean getIsUpdateAllowed() {
            return isUpdateAllowed;
        }

        public boolean getIsPartialUpdateAllowed() {
            return isPartialUpdateAllowed;
        }

        public boolean getIsDeleteAllowed() {
            return isDeleteAllowed;
        }

        public CrudControllerOptions build() {
            return new CrudControllerOptions(this);
        }
    }
}
