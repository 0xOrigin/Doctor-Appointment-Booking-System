package com.xorigin.doctorappointmentmanagementsystem.core.entities;

import com.xorigin.doctorappointmentmanagementsystem.core.uuid.UUIDv7;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue
    @UUIDv7
    protected UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
