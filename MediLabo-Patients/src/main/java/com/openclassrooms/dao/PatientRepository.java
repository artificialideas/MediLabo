package com.openclassrooms.dao;

import com.openclassrooms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    Patient findByFirstNameAndLastName(String firstName, String lastName);
}
