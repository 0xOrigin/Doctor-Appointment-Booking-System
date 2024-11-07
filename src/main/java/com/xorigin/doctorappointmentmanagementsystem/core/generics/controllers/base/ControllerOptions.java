package com.xorigin.doctorappointmentmanagementsystem.core.generics.controllers.base;

public interface ControllerOptions {

    Integer getDefaultPageSize();

    Integer getPageSize();

    void setPageSize(Integer pageSize);

    boolean isPaginationEnabled();

    boolean isFindAllAllowed();

    boolean isFindOneAllowed();

    boolean isCreateAllowed();

    boolean isUpdateAllowed();

    boolean isDeleteAllowed();

}
