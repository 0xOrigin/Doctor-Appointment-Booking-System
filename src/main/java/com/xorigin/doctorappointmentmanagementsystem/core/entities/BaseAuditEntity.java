package com.xorigin.doctorappointmentmanagementsystem.core.entities;

import com.xorigin.doctorappointmentmanagementsystem.users.User;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseAuditEntity extends BaseEntity {

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    protected User createdBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected Instant createdAt;

    @LastModifiedBy
    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "updated_by", insertable = false)
    protected User updatedBy;

    @LastModifiedDate
    @Column(insertable = false)
    protected Instant updatedAt;

    public User getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

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
