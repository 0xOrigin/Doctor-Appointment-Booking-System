package com.xorigin.doctorappointmentmanagementsystem.core.entities;

import com.xorigin.doctorappointmentmanagementsystem.users.User;
import jakarta.persistence.*;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseUpdateAuditEntity extends BaseEntity {

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "updated_by", insertable = false)
    protected User updatedBy;

    @LastModifiedDate
    @Column(insertable = false)
    protected Instant updatedAt;

    public User getUpdatedBy() {
        return updatedBy;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
