package com.openclassrooms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name = "id_patient")
    private String patId;

    @Column(name = "patient_status",
            nullable = false)
    private Risk status;
}
