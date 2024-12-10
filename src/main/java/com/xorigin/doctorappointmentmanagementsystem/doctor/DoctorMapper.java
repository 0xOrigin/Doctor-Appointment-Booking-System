package com.xorigin.doctorappointmentmanagementsystem.doctor;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.mappers.SingleDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper extends SingleDtoMapper<Doctor, DoctorDTO> {

}
