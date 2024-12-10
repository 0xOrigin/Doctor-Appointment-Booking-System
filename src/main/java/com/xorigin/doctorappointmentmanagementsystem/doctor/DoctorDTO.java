package com.xorigin.doctorappointmentmanagementsystem.doctor;

import com.xorigin.doctorappointmentmanagementsystem.users.User;

import java.time.OffsetDateTime;

public class DoctorDTO {

    private String bio;

    private String speciality;

    private OffsetDateTime availabilityFrom;

    private OffsetDateTime availabilityTo;

    private User user;

}
