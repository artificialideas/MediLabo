package com.openclassrooms.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Document(collection = "notes")
@Getter
@Setter
public class Note {
    @Id
    private ObjectId id;

    private UUID patId;
    private String patient;

    private String note;
    private Date date = new Date();
}