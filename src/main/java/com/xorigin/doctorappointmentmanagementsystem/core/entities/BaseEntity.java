package com.xorigin.doctorappointmentmanagementsystem.core.entities;

import com.xorigin.doctorappointmentmanagementsystem.core.uuid.UUIDv7;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;

import java.util.UUID;

@MappedSuperclass
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @UUIDv7
    private UUID id;

}
