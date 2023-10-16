package com.openclassrooms.controller;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/")
    public List<NoteDTO> getNotes() {
        return noteService.findAll();
    }
}
