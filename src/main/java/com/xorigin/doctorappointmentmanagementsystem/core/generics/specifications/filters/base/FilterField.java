package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

import java.util.Set;

public interface FilterField<T> {

    Set<Operator> getSupportedOperators();

    T cast(Object value);

}
