package com.openclassrooms.service;

import com.openclassrooms.model.Assessment;

import java.util.List;

public interface AssessmentService {
    List<Assessment> findAll();
}