package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

import java.util.List;

public class FilterWrapper {

    private final String field;
    private final Operator operator;
    private final List<?> values;

    public FilterWrapper(String field, Operator operator, List<?> values) {
        this.field = field;
        this.operator = operator;
        this.values = values;
    }

    public String getField() {
        return field;
    }

    public Operator getOperator() {
        return operator;
    }

    public List<?> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "FilterWrapper{" +
                "field='" + field + '\'' +
                ", operator=" + operator +
                ", values=" + values +
                '}';
    }

}
