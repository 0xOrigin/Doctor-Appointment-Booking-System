package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.*;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.registries.FilterOperatorRegistry;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.registries.FilterRegistry;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilterBuilder<T> implements QueryFilterBuilder<T> {

    private final Parser filterParser;
    private final PathGenerator<T> filterPathGenerator;
    private final FilterValidator filterValidator;
    private final Map<String, Set<Operator>> fieldOperators = new HashMap<>();

    public FilterBuilder(FilterParser filterParser, PathGenerator<T> filterPathGenerator, FilterValidator filterValidator) {
        this.filterParser = filterParser;
        this.filterPathGenerator = filterPathGenerator;
        this.filterValidator = filterValidator;
    }

    public QueryFilterBuilder<T> addFilter(String fieldName, Operator... operators) {
        filterValidator.validateFilterAddition(fieldName, operators);
        fieldOperators.put(fieldName, new HashSet<>(Arrays.asList(operators)));
        return this;
    }

    public Predicate buildFilterPredicate(Root<T> root, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        List<FilterWrapper> filterWrappers = filterParser.parse();

        for (FilterWrapper filterWrapper : filterWrappers) {
            if (!fieldOperators.containsKey(filterWrapper.getField()))
                continue;

            if (!fieldOperators.get(filterWrapper.getField()).contains(filterWrapper.getOperator()))
                continue;

            Predicate predicate = createPredicate(root, cb, filterWrapper);
            if (predicate != null)
                predicates.add(predicate);

        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate createPredicate(Root<T> root, CriteriaBuilder builder, FilterWrapper filterWrapper) {
        Path<T> path = getPath(root, filterWrapper.getField());
        Class<? extends Comparable<?>> dataType = getFieldDataType(path);
        AbstractFilterField<?> filterClass = getFieldFilter(dataType);
        FilterOperator filterOperator = FilterOperatorRegistry.getOperator(filterWrapper.getOperator());

        filterValidator.validateFilterFieldAndOperator(filterClass, filterOperator, filterWrapper);

        List<?> values = filterWrapper.getValues().stream().map(filterClass::cast).toList();
        return filterOperator.apply(path, builder, values);
    }

    private Path<T> getPath(Root<T> root, String field) {
        return filterPathGenerator.generate(root, field);
    }

    @SuppressWarnings("unchecked")
    private Class<? extends Comparable<?>> getFieldDataType(Path<T> path) {
        return (Class<? extends Comparable<?>>) path.getJavaType();
    }

    private AbstractFilterField<?> getFieldFilter(Class<? extends Comparable<?>> dataType) {
        return FilterRegistry.getFieldFilter(dataType);
    }

}
