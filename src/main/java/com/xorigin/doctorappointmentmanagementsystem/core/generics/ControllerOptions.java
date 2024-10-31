package com.xorigin.doctorappointmentmanagementsystem.core.generics;

public interface ControllerOptions {

    int getPageSize();

    int getMaxPageSize();

    boolean isPaginationEnabled();

    boolean isFindAllAllowed();

    boolean isFindOneAllowed();

    boolean isCreateAllowed();

    boolean isUpdateAllowed();

    boolean isDeleteAllowed();

}
