package com.xorigin.doctorappointmentmanagementsystem.core.entities;

import com.xorigin.doctorappointmentmanagementsystem.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseUpdateAuditEntity extends BaseEntity {

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "updated_by", insertable = false)
    protected User updatedBy;

    @LastModifiedDate
    @Column(insertable = false)
    protected Instant updatedAt;

}
