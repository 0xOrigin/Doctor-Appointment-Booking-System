package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.*;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterConfigurationException;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.registries.FilterRegistry;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FilterValidator {

    public void validateFilterAddition(String fieldName, Operator[] operators) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException(
                    "Field name cannot be null or empty."
            );
        }

        if (operators == null || operators.length == 0) {
            throw new IllegalArgumentException(
                    "At least one operator must be specified for the field '" + fieldName + "'."
            );
        }

        Arrays.stream(operators).forEach(operator -> {
            if (operator == null) {
                throw new IllegalArgumentException(
                        "Operator cannot be null."
                );
            }
        });
    }


    public void validateCustomFilterAddition(
            String fieldName,
            Class<? extends Comparable<?>> dataType,
            CustomFilterFunction<?> filterFunction
    ) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException(
                    "Field name cannot be null or empty"
            );
        }

        if (dataType == null) {
            throw new IllegalArgumentException(
                    "Data type must be specified for the custom filter of name '" + fieldName + "'."
            );
        }

        if (FilterRegistry.getFieldFilter(dataType) == null) {
            throw new IllegalArgumentException(
                    "Data type '" + dataType.getSimpleName() + "' is not supported."
            );
        }

        if (filterFunction == null) {
            throw new IllegalArgumentException(
                    "Filter function must be specified for the custom filter of name '" + fieldName + "'."
            );
        }
    }

    public void validateFilterFieldAndOperator(
            AbstractFilterField<?> filterClass,
            FilterOperator filterOperator,
            FilterWrapper filterWrapper
    ) {
        if (filterClass == null) {
            throw new InvalidFilterConfigurationException(
                    "Field '" + filterWrapper.getField() + "' is not supported."
            );
        }

        if (filterOperator == null) {
            throw new InvalidFilterConfigurationException(
                    "Operator '" + filterWrapper.getOperator().getValue() + "' is not a valid operator."
            );
        }

        if (!filterClass.getSupportedOperators().contains(filterWrapper.getOperator())) {
            throw new InvalidFilterConfigurationException(
                    "Operator '" + filterWrapper.getOperator().getValue() +
                            "' is not a valid operator for the type of field '" + filterWrapper.getField() + "'."
            );
        }
    }

}
