package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class GenericSpecification<T> implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate basePredicate = commonFilter(root, cb);

        return cb.and(basePredicate, getAdditionalPredicate(root, query, cb));
    }

    protected Predicate commonFilter(Root<T> root, CriteriaBuilder cb) {
        // No common filter currently, return an "always-true" predicate
        return cb.conjunction();
    }

    protected Predicate getAdditionalPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // No additional predicate currently, return an "always-true" predicate
        return cb.conjunction();
    }
}
