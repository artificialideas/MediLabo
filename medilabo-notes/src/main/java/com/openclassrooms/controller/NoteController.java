package com.openclassrooms.controller;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
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

    @GetMapping("/api-data/{patId}")
    public ResponseEntity<List<NoteDTO>> getData(
            @PathVariable("patId") String patId) {
        List<NoteDTO> data = noteService.getData(patId);

        return ResponseEntity.ok(data);
    }
}
