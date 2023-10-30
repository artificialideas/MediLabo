package com.openclassrooms.controller;

import com.openclassrooms.dto.PatientDTO;
import com.openclassrooms.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
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
    public String addPatient(
            @RequestBody PatientDTO patientDTO) {
        patientService.add(patientDTO);
        return "redirect:/patients/";
    }

    @PutMapping("/{firstName}-{lastName}")
    public String updatePatient(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName,
            @RequestBody PatientDTO patientToUpdateDTO) {
        PatientDTO patientDTO = patientService.findByFirstNameAndLastName(firstName, lastName);
        if (patientDTO != null)
            patientService.update(patientToUpdateDTO);

        return "redirect:/patients/{firstName}-{lastName}";
    }

    @DeleteMapping("/{firstName}-{lastName}")
    public String deletePatient(
            @PathVariable("firstName") String firstName,
            @PathVariable("lastName") String lastName) {
        PatientDTO patientDTO = patientService.findByFirstNameAndLastName(firstName, lastName);
        if (patientDTO != null)
            patientService.delete(patientDTO);

        return "redirect:/patients/";
    }
}
