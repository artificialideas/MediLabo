package com.openclassrooms.service;

import com.openclassrooms.dao.NoteRepository;
import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.dto.converter.NoteDTOConverter;
import com.openclassrooms.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
