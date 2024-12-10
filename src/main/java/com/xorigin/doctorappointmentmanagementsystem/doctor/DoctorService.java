package com.xorigin.doctorappointmentmanagementsystem.doctor;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.providers.UserProvider;
import com.xorigin.doctorappointmentmanagementsystem.core.generics.services.UuidSingleDtoGenericService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DoctorService extends UuidSingleDtoGenericService<Doctor, DoctorRepository, DoctorMapper, DoctorDTO> {

    public DoctorService(UserProvider userProvider, DoctorRepository repository, DoctorMapper mapper, Specification<Doctor> spec) {
        super(userProvider, repository, mapper, spec);
    }

}
