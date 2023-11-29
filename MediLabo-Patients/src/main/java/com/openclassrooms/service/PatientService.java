package com.openclassrooms.service;

import com.openclassrooms.dto.PatientDTO;

import java.text.ParseException;
import java.util.List;

public interface PatientService {
    List<PatientDTO> findAll();

    PatientDTO findByFirstNameAndLastName(String firstName, String lastName);

    void add(PatientDTO patientDTO) throws ParseException;

    void update(PatientDTO savedPatientDTO, PatientDTO updateDataDTO) throws ParseException;

    void delete(PatientDTO patientDTO) throws ParseException;
}
