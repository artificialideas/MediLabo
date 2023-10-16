package com.openclassrooms.service;

import com.openclassrooms.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> findAll();

    PatientDTO findById(String id);

    PatientDTO findByFirstNameAndLastName(String firstName, String lastName);

    void add(PatientDTO patientDTO);

    void update(PatientDTO patientDTO);

    void delete(PatientDTO patientDTO);
}
