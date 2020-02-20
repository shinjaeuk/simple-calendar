package com.careerfoundar.simplecalendar.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class Calendar extends BaseEntity{

    @Id
    @Column( unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Boolean available;

    @Column
    private Timestamp dateTime;

    @ManyToOne
    @JoinColumn(name = "MENTOR_ID")
    private Mentor mentor;

    @Column
    private String reason;
}
