package com.openclassrooms.controller;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/{patId}")
    public List<NoteDTO> getPatientNotes(
            @PathVariable("patId") String patId) {
        return noteService.findByPatientId(patId);
    }

    @PostMapping("/add")
    public void addNote(
            @RequestBody NoteDTO noteDTO) {
        noteService.add(noteDTO);
    }
}
