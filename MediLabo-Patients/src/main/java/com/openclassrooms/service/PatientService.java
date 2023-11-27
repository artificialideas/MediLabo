package com.openclassrooms.service;

import com.openclassrooms.dto.PatientDTO;

import java.util.List;

public interface PatientService {
    List<PatientDTO> findAll();

    PatientDTO findByFirstNameAndLastName(String firstName, String lastName);

    void add(PatientDTO patientDTO);

    void update(PatientDTO savedPatientDTO, PatientDTO updateDataDTO);

    void delete(PatientDTO patientDTO);
}
