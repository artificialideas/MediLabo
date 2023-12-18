package com.openclassrooms.model;

import lombok.Data;

import java.util.Date;

@Data
public class NoteResponse {
    private String id;
    private Date date;
    private String patId;
    private String patient;
    private String note;
}
