package com.openclassrooms.dto;

import lombok.Data;

@Data
public class NoteDTO {
    private String id;
    private String patId;
    private String patient;
    private String note;
}
