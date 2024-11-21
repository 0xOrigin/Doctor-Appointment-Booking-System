package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.QueryFilterBuilderSpecification;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.QueryFilterBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;

public abstract class GenericSpecification<T> extends QueryFilterBuilderSpecification<T> {

    private final HttpServletRequest request;
    private final UserProvider userProvider;

    public GenericSpecification(
            HttpServletRequest request,
            UserProvider userProvider,
            QueryFilterBuilder<T> filterBuilder
    ) {
        super(filterBuilder);
        this.request = request;
        this.userProvider = userProvider;
    }

    public GenericSpecification(HttpServletRequest request, UserProvider userProvider) {
        this(request, userProvider, null);
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public UserProvider getUserProvider() {
        return userProvider;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getRequestPathParams() {
        return (Map<String, String>) getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return cb.and(commonFilter(root, query, cb), getAdditionalPredicate(root, query, cb), super.toPredicate(root, query, cb));
    }

    protected Predicate commonFilter(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // No common filter currently, return an "always-true" predicate
        return cb.conjunction();
    }

    protected Predicate getAdditionalPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // No additional predicate currently, return an "always-true" predicate
        return cb.conjunction();
    }

}
