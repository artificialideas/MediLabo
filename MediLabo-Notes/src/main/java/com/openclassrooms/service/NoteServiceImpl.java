package com.openclassrooms.service;

import com.openclassrooms.dao.NoteRepository;
import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.dto.NoteLightDTO;
import com.openclassrooms.dto.converter.NoteDTOConverter;
import com.openclassrooms.model.Note;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    private NoteDTOConverter noteDTOConverter = new NoteDTOConverter();
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
        Iterable<Note> allNotes = noteRepository.findByPatId(UUID.fromString(patId));

        if (allNotes.iterator().hasNext()) {
            List<Note> patientNotes = new ArrayList<>();
            allNotes.forEach(patientNotes::add);

            return noteDTOConverter.getDTOsFromEntities(patientNotes);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient with id " + patId + " doesn't have any notes.");
    }

    @Override
    /*public List<NoteLightDTO> findByPatientAndOrderByDateDesc(String id) {
        List<NoteDTO> patientNotesDTO = findByPatientId(id);
        List<NoteLightDTO> patientNotesLightDTO = new ArrayList<>();

        for (NoteDTO noteDTO : patientNotesDTO) {
            NoteLightDTO noteLightDTO = new NoteLightDTO();
                noteLightDTO.setId(noteDTO.getId());
                noteLightDTO.setNote(noteDTO.getNote());

            patientNotesLightDTO.add(noteLightDTO);
        }

        return patientNotesLightDTO;
    }*/
    public List<NoteLightDTO> findByPatientAndOrderByDateDesc(String id) {
        return noteRepository.findByPatIdOrderByDateDesc();
    }

    @Override
    public void add(NoteDTO noteDTO) {
        if (findById(noteDTO.getId()) != null) {
            Note note = noteDTOConverter.getEntityFromDTO(noteDTO);
            // Set creation date
            note.setDate(new Date());

            noteRepository.insert(note);
        } else
            throw new ResponseStatusException(HttpStatus.IM_USED, "Note with id " + noteDTO.getId() + " already exists.");
    }

    @Override
    public void update(NoteDTO noteDTO) {
        NoteDTO noteToSaveDTO = new NoteDTO();

        NoteDTO savedNoteDTO = findById(noteDTO.getId());
        if (savedNoteDTO != null)
            noteToSaveDTO = savedNoteDTO;

        if (noteDTO.getPatId() != null)
            noteToSaveDTO.setPatId(noteDTO.getPatId());
        if (noteDTO.getPatient() != null)
            noteToSaveDTO.setPatient(noteDTO.getPatient());

        if (noteDTO.getNote() != null)
            noteToSaveDTO.setNote(noteDTO.getNote());

        noteRepository.save(noteDTOConverter.getEntityFromDTO(noteToSaveDTO));
    }

    @Override
    public void delete(String id) {
        noteRepository.delete(noteDTOConverter.getEntityFromDTO(findById(id)));
    }
}
