package com.openclassrooms.model;

import lombok.Data;

@Data
public class PatientResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String birthdate; //"yyyy-MM-dd"
    private String gender;
    private String phoneNumber;
    private String address;
}