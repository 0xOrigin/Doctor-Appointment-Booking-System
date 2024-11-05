package com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.base.BaseGenericRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StringGenericRepository<T> extends BaseGenericRepository<T, String> {

}
