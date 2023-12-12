package com.openclassrooms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoteDTO {
    private String id;
    private Date date;
    private String patId;
    private String patient;
    private String note;
}
