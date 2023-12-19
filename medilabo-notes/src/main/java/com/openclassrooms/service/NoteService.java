package com.openclassrooms.service;

import com.openclassrooms.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> findAll();

    NoteDTO findById(String id);

    List<NoteDTO> findByPatientId(String id);

    void add(NoteDTO noteDTO);

    void update(NoteDTO savedNoteDTO, NoteDTO updateDataDTO);

    void delete(String id);

    List<NoteDTO> getData(String patId);
}