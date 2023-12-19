package com.openclassrooms.dao;

import com.openclassrooms.model.Note;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, ObjectId> {
    List<Note> findByPatId(String patId);
}