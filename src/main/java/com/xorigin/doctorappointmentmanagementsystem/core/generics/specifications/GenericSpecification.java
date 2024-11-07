package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

@Service
public class GenericSpecification<T> implements Specification<T> {

    private final UserProvider userProvider;
    private final HttpServletRequest request;

    public GenericSpecification(UserProvider userProvider, HttpServletRequest request) {
        this.userProvider = userProvider;
        this.request = request;
    }

    public UserProvider getUserProvider() {
        return userProvider;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public Map<String, String> getRequestPathParams() {
        return (Map<String, String>) getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    public Map<String, String[]> getRequestQueryParams() {
        return getRequest().getParameterMap();
    }

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
