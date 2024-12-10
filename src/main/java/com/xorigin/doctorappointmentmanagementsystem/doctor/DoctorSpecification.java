package com.xorigin.doctorappointmentmanagementsystem.doctor;

import io.github._0xorigin.FilterContext;
import io.github._0xorigin.base.Operator;
import io.github._0xorigin.base.QueryFilterBuilder;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.GenericSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class DoctorSpecification extends GenericSpecification<Doctor> {

    public DoctorSpecification(HttpServletRequest request, UserProvider userProvider, QueryFilterBuilder<Doctor> filterBuilder) {
        super(request, userProvider, filterBuilder);
    }

    @Override
    protected Predicate getFilterPredicate(Root<Doctor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return getFilterBuilder().buildFilterPredicate(
                root,
                query,
                cb,
                new FilterContext<Doctor>()
                        .addFilter("createdAt", Operator.BETWEEN)
                        .addFilter("createdBy__id", Operator.EQ)
                        .addFilter("createdBy__firstName", Operator.EQ)
                        .addFilter("lastLogin", Operator.EQ, Operator.GTE, Operator.BETWEEN)
                        .addFilter("createdBy__lastLogin__createdBy", Operator.EQ, Operator.GTE, Operator.BETWEEN)
        );
    }

}
