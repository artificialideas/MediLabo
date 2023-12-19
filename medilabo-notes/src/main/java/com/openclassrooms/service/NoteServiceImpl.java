package com.openclassrooms.service;

import com.openclassrooms.dao.NoteRepository;
import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.dto.converter.NoteDTOConverter;
import com.openclassrooms.model.Note;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    private final NoteDTOConverter noteDTOConverter = new NoteDTOConverter();
    public void NoteDTOConverter(NoteDTOConverter noteDTOConverter) {}

    @Override
    public List<NoteDTO> findAll() {
        List<Note> allNotes = noteRepository.findAll();
        return noteDTOConverter.getDTOsFromEntities(allNotes);
    }

    @Override
    public NoteDTO findById(String id) {
        Optional<Note> note = noteRepository.findById(new ObjectId(id));

        if (note.isPresent()) {
            return noteDTOConverter.getDTOFromEntity(note.get());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Note with id " + id + " doesn't exists.");
    }

    @Override
    public List<NoteDTO> findByPatientId(String patId) {
        List<Note> patientNotes = noteRepository.findByPatId(patId);

        return noteDTOConverter.getDTOsFromEntities(patientNotes);
    }

    @Override
    public void add(NoteDTO noteDTO) {
        if (findByPatientId(noteDTO.getPatId()) != null) {
            // Set creation date
            noteDTO.setDate(new Date());
            Note note = noteDTOConverter.getEntityFromDTO(noteDTO);

            noteRepository.insert(note);
        } else
            throw new ResponseStatusException(HttpStatus.IM_USED, "Note with id " + noteDTO.getId() + " already exists.");
    }

    @Override
    public void update(NoteDTO savedNoteDTO, NoteDTO updateDataDTO) {
        // Entity validation has been done in controller with findByPatientId()
            // Patient's id and name are not updatable

        if (updateDataDTO.getNote() != null)
            savedNoteDTO.setNote(updateDataDTO.getNote());

        noteRepository.save(noteDTOConverter.getEntityFromDTO(savedNoteDTO));
    }

    @Override
    public void delete(String id) {
        noteRepository.delete(noteDTOConverter.getEntityFromDTO(findById(id)));
    }

    @Override
    public List<NoteDTO> getData(String patId) {
        return findByPatientId(patId);
    }
}
