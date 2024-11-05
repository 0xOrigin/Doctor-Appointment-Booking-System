package com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface BaseGenericRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QueryByExampleExecutor<T> {

}