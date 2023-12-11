package com.openclassrooms.service;

import com.openclassrooms.model.Assessment;
import reactor.core.publisher.Mono;

public interface AssessmentService {
    Mono<Assessment> getPatientRisk(String patId);
}