package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

public class CustomFilterWrapper<T> {

    private final AbstractFilterField<?> filterField;
    private final CustomFilterFunction<T> customFilterFunction;

    public CustomFilterWrapper(AbstractFilterField<?> filterField, CustomFilterFunction<T> customFilterFunction) {
        this.filterField = filterField;
        this.customFilterFunction = customFilterFunction;
    }

    public AbstractFilterField<?> getFilterField() {
        return filterField;
    }

    public CustomFilterFunction<T> getCustomFilterFunction() {
        return customFilterFunction;
    }

}