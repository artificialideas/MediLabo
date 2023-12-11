package com.openclassrooms.service;

import com.openclassrooms.PatientDTO;
import com.openclassrooms.model.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<PatientDTO> findAll();

    Optional<Patient> findById(String id);

    PatientDTO findByFirstNameAndLastName(String firstName, String lastName);

    void add(PatientDTO patientDTO);

    void update(PatientDTO savedPatientDTO, PatientDTO updateDataDTO);

    void delete(PatientDTO patientDTO);

    PatientDTO getData(String id);
}
