package com.openclassrooms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO {
    private String id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String gender;
    private String phoneNumber;
    private String address;
}