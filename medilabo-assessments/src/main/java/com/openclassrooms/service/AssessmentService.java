package com.openclassrooms.service;

import com.openclassrooms.model.Assessment;

public interface AssessmentService {
    Assessment getPatientRisk(String patId);
}