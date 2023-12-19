package com.openclassrooms.model;

import lombok.Data;

@Data
public class Assessment {
    public enum Risk {
        NONE,
        BORDERLINE,
        DANGER,
        EARLY_ONSET
    }

    private String patId;

    private Risk status;
}
