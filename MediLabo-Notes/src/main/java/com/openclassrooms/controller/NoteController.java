package com.openclassrooms.controller;

import com.openclassrooms.dto.NoteDTO;
import com.openclassrooms.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")
    public NoteDTO getNote(
            @PathVariable("patId") String id) {
        return noteService.findById(id);
    }

    @PostMapping("/add")
    public String addNote(
            @RequestBody NoteDTO noteDTO) {
        noteService.add(noteDTO);
        return "redirect:/notes/";
    }

    @PutMapping("/{id}")
    public String updateNote(
            @PathVariable("id") String id,
            @RequestBody NoteDTO noteToUpdateDTO) {
        NoteDTO noteDTO = noteService.findById(id);
        if (noteDTO != null)
            noteService.update(noteToUpdateDTO);

        return "redirect:/notes/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteNote(
            @PathVariable("id") String id) {
        NoteDTO noteDTO = noteService.findById(id);
        if (noteDTO != null)
            noteService.delete(id);

        return "redirect:/notes/";
    }
}
