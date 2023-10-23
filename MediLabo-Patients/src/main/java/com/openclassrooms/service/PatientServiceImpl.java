package com.openclassrooms.service;

import com.openclassrooms.dao.PatientRepository;
import com.openclassrooms.dto.PatientDTO;
import com.openclassrooms.dto.converter.PatientDTOConverter;
import com.openclassrooms.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    private PatientDTOConverter patientDTOConverter = new PatientDTOConverter();
    public void PatientDTOConverter(PatientDTOConverter patientDTOConverter) {}

    @Override
    public List<PatientDTO> findAll() {
        List<Patient> allPatients = new ArrayList<>();
        patientRepository.findAll()
                .iterator()
                .forEachRemaining(allPatients::add);

        return patientDTOConverter.getDTOsFromEntities(allPatients);
    }

    @Override
    public PatientDTO findById(String id) {
        Optional<Patient> patient = patientRepository.findById(UUID.fromString(id));

        if (patient.isPresent()) {
            return patientDTOConverter.getDTOFromEntity(patient.get());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with id " + id + " doesn't exists.");
    }

    @Override
    public PatientDTO findByFirstNameAndLastName(String firstName, String lastName) {
        Patient patient = patientRepository.findByFirstNameAndLastName(firstName, lastName);
return null;
        /*if (patient.isPresent()) {
            return patientDTOConverter.getDTOFromEntity(patient.get());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with full name " + firstName + " " + lastName + " doesn't exists.");*/
    }

    @Override
    public void add(PatientDTO patientDTO) {
        // Check if the entered patient already exists
        if (findById(patientDTO.getId()) != null) {
            patientRepository.save(patientDTOConverter.getEntityFromDTO(patientDTO));
        } else
            throw new ResponseStatusException(HttpStatus.IM_USED, patientDTO.getFirstName() + " " + patientDTO.getLastName() + " already exists.");
    }

    @Override
    public void update(PatientDTO patientDTO) {
        PatientDTO patientToSaveDTO = new PatientDTO();

        PatientDTO savedPatientDTO = findById(patientDTO.getId());
        if (savedPatientDTO != null)
            patientToSaveDTO = savedPatientDTO;

        if (patientDTO.getFirstName() != null)
            patientToSaveDTO.setFirstName(patientDTO.getFirstName());
        if (patientDTO.getLastName() != null)
            patientToSaveDTO.setLastName(patientDTO.getLastName());

        if (patientDTO.getBirthdate() != null)
            patientToSaveDTO.setBirthdate(patientDTO.getBirthdate());
        if (patientDTO.getGender() != null)
            patientToSaveDTO.setGender(patientDTO.getGender());

        if (patientDTO.getPhoneNumber() != patientToSaveDTO.getPhoneNumber())
            patientToSaveDTO.setPhoneNumber(patientDTO.getPhoneNumber());
        if (patientDTO.getAddress() != null)
            patientToSaveDTO.setAddress(patientDTO.getAddress());

        patientRepository.save(patientDTOConverter.getEntityFromDTO(patientToSaveDTO));
    }

    @Override
    public void delete(PatientDTO patientDTO) {
        patientRepository.delete(patientDTOConverter.getEntityFromDTO(patientDTO));
    }
}
