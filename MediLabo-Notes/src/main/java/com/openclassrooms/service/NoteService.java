package com.openclassrooms.service;

import com.openclassrooms.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> findAll();
}