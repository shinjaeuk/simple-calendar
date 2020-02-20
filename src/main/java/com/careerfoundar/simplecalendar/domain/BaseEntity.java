package com.careerfoundar.simplecalendar.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdDate;

    @Column(nullable = false, updatable = false)
    private String  createdUser;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp modifiedDate;

    @Column(nullable = false)
    private String  modifiedUser;
}
