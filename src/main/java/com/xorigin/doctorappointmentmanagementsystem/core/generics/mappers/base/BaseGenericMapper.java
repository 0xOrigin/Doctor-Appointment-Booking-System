package com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.base;

public interface BaseGenericMapper<Entity, ListDTO, RetrieveDTO, CreateDTO, UpdateDTO, PartialUpdateDTO>
        extends BaseResponseMapper<Entity, ListDTO, RetrieveDTO>, BaseCreateMapper<Entity, CreateDTO>, BaseUpdateMapper<Entity, UpdateDTO, PartialUpdateDTO> {

}
