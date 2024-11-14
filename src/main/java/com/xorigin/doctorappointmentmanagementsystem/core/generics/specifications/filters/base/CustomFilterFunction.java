package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public interface CustomFilterFunction<T> extends QuadFunction<Root<T>, CriteriaQuery<?>, CriteriaBuilder, List<?>, Predicate> {

}
