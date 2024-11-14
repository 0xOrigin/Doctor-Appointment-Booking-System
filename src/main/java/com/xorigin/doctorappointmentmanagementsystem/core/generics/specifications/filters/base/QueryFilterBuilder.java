package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface QueryFilterBuilder<T> {

    QueryFilterBuilder<T> addFilter(String fieldName, Operator... operators);

    QueryFilterBuilder<T> addFilter(String fieldName, Class<? extends Comparable<?>> dataType, CustomFilterFunction<T> filter);

    Predicate buildFilterPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb);

}
