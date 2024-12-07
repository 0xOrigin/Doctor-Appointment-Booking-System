package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFilterField<T> implements FilterField<T> {

    private final Set<Operator> supportedOperators = new HashSet<>();

    @Override
    public Set<Operator> getSupportedOperators() {
        return supportedOperators;
    }

    protected void setSupportedOperators(Set<Operator> operators) {
        supportedOperators.addAll(operators);
    }

}
