package com.xorigin.doctorappointmentmanagementsystem.core.generics;

public interface GenericMapper<T, DTO> {

    T toEntity(DTO dto);

    DTO toDto(T entity);

}
