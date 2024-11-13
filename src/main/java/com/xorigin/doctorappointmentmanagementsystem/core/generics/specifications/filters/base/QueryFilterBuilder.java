package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface QueryFilterBuilder<T> {

    QueryFilterBuilder<T> addFilter(String fieldName, Operator... operators);

    Predicate buildFilterPredicate(Root<T> root, CriteriaBuilder cb);

}
