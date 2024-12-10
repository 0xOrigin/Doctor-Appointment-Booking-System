package com.xorigin.doctorappointmentmanagementsystem.doctor;

import com.xorigin.doctorappointmentmanagementsystem.core.entities.BaseAuditEntity;
import com.xorigin.doctorappointmentmanagementsystem.core.entities.BaseCreationAuditEntity;
import com.xorigin.doctorappointmentmanagementsystem.users.User;
import jakarta.persistence.AssociationOverride;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@RequiredArgsConstructor
@Getter
@Setter

public class Doctor extends BaseCreationAuditEntity {

    private String bio;

    private String speciality;

    private OffsetDateTime availabilityFrom;

    private OffsetDateTime availabilityTo;

    @OneToOne(mappedBy = "doctor")
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private User user;

}
