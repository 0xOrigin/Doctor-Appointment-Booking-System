package com.xorigin.doctorappointmentmanagementsystem.doctor;

import com.xorigin.doctorappointmentmanagementsystem.core.generics.repositories.UuidGenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends UuidGenericRepository<Doctor> {

}
