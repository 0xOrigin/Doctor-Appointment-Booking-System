package com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base;

public interface BaseCreateMapper<Entity, CreateDTO> extends BaseMapper {

    Entity toEntity(CreateDTO dto);

}
