package com.openclassrooms.service;

import com.openclassrooms.dao.PatientRepository;
import com.openclassrooms.dto.PatientDTO;
import com.openclassrooms.dto.converter.PatientDTOConverter;
import com.openclassrooms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    private final PatientDTOConverter patientDTOConverter = new PatientDTOConverter();
    public void PatientDTOConverter(PatientDTOConverter patientDTOConverter) {}

    @Override
    public List<PatientDTO> findAll() {
        List<Patient> allPatients = new ArrayList<>();
        patientRepository.findAll().forEach(allPatients::add);

        return patientDTOConverter.getDTOsFromEntities(allPatients);
    }

    @Override
    public PatientDTO findByFirstNameAndLastName(String firstName, String lastName) {
        Optional<Patient> patient = patientRepository.findByFirstNameAndLastName(firstName, lastName);

        if (patient.isPresent()) {
            return patientDTOConverter.getDTOFromEntity(patient.get());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with full name " + firstName + " " + lastName + " doesn't exists.");
    }

    @Override
    public void add(PatientDTO patientDTO) throws ParseException {
        // Check if the entered patient already exists
        Optional<Patient> patient = patientRepository.findByFirstNameAndLastName(patientDTO.getFirstName(), patientDTO.getLastName());

        if (patient.isEmpty()) {
            patientRepository.save(patientDTOConverter.getEntityFromDTO(patientDTO));
        } else
            throw new ResponseStatusException(HttpStatus.IM_USED, patientDTO.getFirstName() + " " + patientDTO.getLastName() + " already exists.");
    }

    @Override
    public void update(PatientDTO savedPatientDTO, PatientDTO updateDataDTO) throws ParseException {
        // Entity validation has been done in controller with findByFirstNameAndLastName()
            // Firstname, lastname and birthday are not updatable

        if (updateDataDTO.getGender() != null)
            savedPatientDTO.setGender(updateDataDTO.getGender());

        if (updateDataDTO.getPhoneNumber() != null)
            savedPatientDTO.setPhoneNumber(updateDataDTO.getPhoneNumber());
        if (updateDataDTO.getAddress() != null)
            savedPatientDTO.setAddress(updateDataDTO.getAddress());

        patientRepository.save(patientDTOConverter.getEntityFromDTO(savedPatientDTO));
    }

    @Override
    public void delete(PatientDTO patientDTO) throws ParseException {
        patientRepository.delete(patientDTOConverter.getEntityFromDTO(patientDTO));
    }

    private Optional<Patient> findById(String id) {
        return patientRepository.findById(UUID.fromString(id));
    }
}
