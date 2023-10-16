package com.openclassrooms.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PatientDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String gender;
    private int phoneNumber;
    private String address;
}