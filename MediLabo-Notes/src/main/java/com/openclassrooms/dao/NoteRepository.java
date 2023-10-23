package com.openclassrooms.dao;

import com.openclassrooms.dto.NoteLightDTO;
import com.openclassrooms.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NoteRepository extends MongoRepository<Note, ObjectId> {
    Iterable<Note> findByPatId(UUID patId);
    List<NoteLightDTO> findByPatIdOrderByDateDesc();
}