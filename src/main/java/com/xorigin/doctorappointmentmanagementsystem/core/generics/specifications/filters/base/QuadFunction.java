package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

@FunctionalInterface
public interface QuadFunction<T, U, W, V, R> {

    R apply(T t, U u, W w, V v);

}
