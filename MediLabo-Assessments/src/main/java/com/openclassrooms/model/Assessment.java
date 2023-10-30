package com.openclassrooms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Assessments")
@Getter
@Setter
public class Assessment {
    public enum Risk {
        NONE,
        BORDERLINE,
        DANGER,
        EARLY_ONSET
    }

    @OneToOne
    @JoinColumn(name = "id_patient",
            nullable = false,
            unique = true)
    private String patId;

    @Column(name = "patient_status",
            nullable = false)
    private Risk status;

    /**
     *  Helper methods
     **/
}
