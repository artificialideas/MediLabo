package com.openclassrooms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "Assessments")
public class Assessment {
    public enum Risk {
        NONE,
        BORDERLINE,
        DANGER,
        EARLY_ONSET
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_assessment")
    private long id;

    @Column(name = "id_patient")
    private String patId;

    @Column(name = "patient_status",
            nullable = false)
    private Risk status;
}
