package com.openclassrooms.dao;

import com.openclassrooms.model.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends CrudRepository<Patient, UUID> {
    Optional<Patient> findByFirstNameAndLastName(String firstName, String lastName);
}
