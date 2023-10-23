package com.openclassrooms.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

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

    @OneToOne
    @JoinColumn(name = "id_patient",
            nullable = false,
            unique = true)
    private UUID patId;

    @Column(name = "patient_status",
            nullable = false)
    private Risk status;

    /**
     *  Helper methods
     **/
}
