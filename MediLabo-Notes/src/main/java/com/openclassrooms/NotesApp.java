package com.openclassrooms;

import com.openclassrooms.dao.NoteRepository;
import com.openclassrooms.model.Note;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class NotesApp implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(NotesApp.class);

    @Autowired
    private NoteRepository noteRepository;

    public static void main(String[] args) {
        SpringApplication.run(NotesApp.class, args);
    }

    @Override
    public void  run(String... args) throws Exception {
        logger.info("Starting Notes Service");
        ObjectId objectId = new ObjectId("652d01c51253b8befc43756f");
        Optional<Note> note = noteRepository.findById(objectId);
        if (note.isPresent()) {
            logger.info(note.get().getNote());
        } else {
            logger.info("Note not found");
        }
    }
}