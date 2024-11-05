package com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.base.BaseGenericRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface UuidGenericRepository<T> extends BaseGenericRepository<T, UUID> {

}
