package com.xorigin.doctorappointmentmanagementsystem.users;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.GenericSpecification;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.Operator;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.QueryFilterBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSpecification extends GenericSpecification<User> {

    public UserSpecification(HttpServletRequest request, UserProvider userProvider, QueryFilterBuilder<User> filterBuilder) {
        super(request, userProvider, filterBuilder);
    }

    @Override
    protected void initializeFilterBuilder() {
        getQueryFilterBuilder()
                .addFilter("id", Operator.EQ, Operator.IS_NOT_NULL, Operator.IN, Operator.NOT_IN)
                .addFilter("createdBy__firstName", Operator.EQ, Operator.IS_NOT_NULL, Operator.NEQ)
                .addFilter("createdBy__createdBy", Operator.EQ, Operator.IS_NOT_NULL, Operator.NEQ)
                .addFilter("createdBy", Operator.EQ, Operator.IS_NOT_NULL)
                .addFilter("createdAt", Operator.EQ, Operator.GTE, Operator.BETWEEN)
                .addFilter("dateOfBirth", Operator.EQ, Operator.GTE, Operator.BETWEEN)
                .addFilter("lastLogin", Operator.EQ, Operator.GTE, Operator.BETWEEN)
                .addFilter("enTime", Operator.GTE)
                .addFilter("hie", Boolean.class, this::getIsActivePredicate)
                .addFilter("isActive", Operator.EQ, Operator.IS_NOT_NULL, Operator.BETWEEN);
    }

    public Predicate getIsActivePredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb, List<?> values) {
        return cb.equal(root.get("isActive"), values.getFirst());
    }

}
