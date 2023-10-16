package com.openclassrooms;

import com.openclassrooms.dao.PatientRepository;
import com.openclassrooms.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class PatientsApp implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(PatientsApp.class);

    public static void main(String[] args) {
        SpringApplication.run(PatientsApp.class, args);
    }

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void  run(String... args) throws Exception {
        logger.info("Starting Patients Service");

        Optional<Patient> patient = patientRepository.findById(UUID.fromString("85c4901e-5ee7-43d9-a126-8a45835ad91f"));
        if (patient.isPresent()) {
            logger.info(patient.get().getFirstName() + " " + patient.get().getLastName());
        } else {
            logger.info("Patient not found");
        }
    }
}