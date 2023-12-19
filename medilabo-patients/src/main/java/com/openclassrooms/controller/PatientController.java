package com.openclassrooms.controller;

import com.openclassrooms.dto.PatientDTO;
import com.openclassrooms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    public List<PatientDTO> getPatients() {
        return patientService.findAll();
    }

    @GetMapping("/{firstName}-{lastName}")
    public PatientDTO getPatient(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        return patientService.findByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping("/add")
    public void addPatient(
            @RequestBody PatientDTO patientDTO) {
        patientService.add(patientDTO);
    }

    @PutMapping("/{firstName}-{lastName}")
    public void updatePatient(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @RequestBody PatientDTO updateDataDTO) {
        PatientDTO patientDTO = patientService.findByFirstNameAndLastName(firstName, lastName);
        if (patientDTO != null)
            patientService.update(patientDTO, updateDataDTO);
    }

    @DeleteMapping("/{firstName}-{lastName}")
    public List<PatientDTO> deletePatient(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        PatientDTO patientDTO = patientService.findByFirstNameAndLastName(firstName, lastName);
        if (patientDTO != null)
            patientService.delete(patientDTO);

        return patientService.findAll();
    }

    @GetMapping("/api-data/{id}")
    public ResponseEntity<PatientDTO> getData(
            @PathVariable("id") String id) {
        PatientDTO data = patientService.getData(id);

        return ResponseEntity.ok(data);
    }
}
