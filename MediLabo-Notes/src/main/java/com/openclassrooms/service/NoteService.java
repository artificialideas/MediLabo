package com.openclassrooms.service;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.dto.NoteLightDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> findAll();
    NoteDTO findById(String id);
    List<NoteDTO> findByPatientId(String id);
    List<NoteLightDTO> findByPatientAndOrderByDateDesc(String id);
    void add(NoteDTO noteDTO);
    void update(NoteDTO noteDTO);
    void delete(String id);
}