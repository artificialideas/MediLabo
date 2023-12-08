package com.openclassrooms.service;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.dto.NoteLightDTO;

import java.text.ParseException;
import java.util.List;

public interface NoteService {
    List<NoteDTO> findAll();
    NoteDTO findById(String id);
    List<NoteDTO> findByPatientId(String id);
    List<NoteLightDTO> findByPatientAndOrderByDateDesc(String id);
    void add(NoteDTO noteDTO) throws ParseException;
    void update(NoteDTO savedNoteDTO, NoteDTO updateDataDTO) throws ParseException;
    void delete(String id) throws ParseException;
}