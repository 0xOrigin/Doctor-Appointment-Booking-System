package com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.base.PathGenerator;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.specifications.filters.exceptions.InvalidFilterConfigurationException;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.hibernate.query.sqm.TerminalPathException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FilterPathGenerator<T> implements PathGenerator<T> {

    private final String FIELD_DELIMITER;
    private final String DEFAULT_ID_FIELD;

    public FilterPathGenerator(
            @Value("${query-filter-builder.defaults.field-delimiter:__}") final String FIELD_DELIMITER,
            @Value("${query-filter-builder.defaults.id-field:id}") final String DEFAULT_ID_FIELD
    ) {
        this.FIELD_DELIMITER = FIELD_DELIMITER;
        this.DEFAULT_ID_FIELD = DEFAULT_ID_FIELD;
    }

    @Override
    public Path<T> generate(Root<T> root, String field) {
        String[] parts = field.split(FIELD_DELIMITER);
        Path<T> path = root;

        for (int i = 0; i < parts.length - 1; i++) {
            String part = parts[i];

            if (!root.getModel().getAttribute(part).isAssociation()) {
                path = path.get(part);
                break;
            }

            path = (path instanceof Root) ? ((Root<?>) path).join(part, JoinType.LEFT)
                    : ((From<?, ?>) path).join(part, JoinType.LEFT);
        }

        // Get the final field for the condition (e.g., "name" in "user__manager__department__name")
        String finalPart = parts[parts.length - 1];
        try {
            return root.getModel().getAttribute(finalPart).isAssociation()
                    ? path.get(finalPart).get(DEFAULT_ID_FIELD)
                    : path.get(finalPart);
        } catch (TerminalPathException e) {
            throw new InvalidFilterConfigurationException(e.getLocalizedMessage(), e);
        }
    }

}
