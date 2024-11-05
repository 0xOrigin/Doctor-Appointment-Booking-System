package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base;

public interface ControllerOptions {

    int getPageSize();

    int getMaxPageSize();

    void setPageSize(int pageSize);

    void setMaxPageSize(int maxPageSize);

    boolean isPaginationEnabled();

    boolean isFindAllAllowed();

    boolean isFindOneAllowed();

    boolean isCreateAllowed();

    boolean isUpdateAllowed();

    boolean isDeleteAllowed();

}
