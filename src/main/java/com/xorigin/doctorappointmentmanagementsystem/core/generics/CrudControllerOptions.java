package com.xorigin.doctorappointmentmanagementsystem.core.generics;


public class CrudControllerOptions implements ControllerOptions {

    private final int pageSize;
    private final int maxPageSize;
    private final boolean isPaginationEnabled;
    private final boolean isFindAllAllowed;
    private final boolean isFindOneAllowed;
    private final boolean isCreateAllowed;
    private final boolean isUpdateAllowed;
    private final boolean isDeleteAllowed;

    private CrudControllerOptions(Builder builder) {
        this.pageSize = builder.pageSize;
        this.maxPageSize = builder.maxPageSize;
        this.isPaginationEnabled = builder.isPaginationEnabled;
        this.isFindAllAllowed = builder.isFindAllAllowed;
        this.isFindOneAllowed = builder.isFindOneAllowed;
        this.isCreateAllowed = builder.isCreateAllowed;
        this.isUpdateAllowed = builder.isUpdateAllowed;
        this.isDeleteAllowed = builder.isDeleteAllowed;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getMaxPageSize() {
        return maxPageSize;
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

    public boolean isDeleteAllowed() {
        return isDeleteAllowed;
    }

    public static class Builder {
        // Default values
        private int pageSize = 10;
        private int maxPageSize = 100;
        private boolean isPaginationEnabled = true;
        private boolean isFindAllAllowed = true;
        private boolean isFindOneAllowed = true;
        private boolean isCreateAllowed = true;
        private boolean isUpdateAllowed = true;
        private boolean isDeleteAllowed = true;

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder maxPageSize(int maxPageSize) {
            this.maxPageSize = maxPageSize;
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

        public Builder isDeleteAllowed(boolean isDeleteAllowed) {
            this.isDeleteAllowed = isDeleteAllowed;
            return this;
        }

        public CrudControllerOptions build() {
            return new CrudControllerOptions(this);
        }
    }
}
