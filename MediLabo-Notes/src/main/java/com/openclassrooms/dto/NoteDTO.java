package com.openclassrooms.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NoteDTO {
    private String id;
    private UUID patId;
    private String patient;
    private String note;
}
