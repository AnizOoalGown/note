package com.nazarick.note.controller;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/{id}")
    RespDTO getById(@PathVariable Integer id) {
        return RespDTO.success(noteService.getById(id));
    }

    @PostMapping
    RespDTO create(@RequestBody Note note) {
        return RespDTO.successIfNotNull(noteService.create(note));
    }

    @PutMapping
    RespDTO update(@RequestBody Note note) {
        return RespDTO.successIf(noteService.update(note));
    }

    @DeleteMapping("/{id}")
    RespDTO deleteById(@PathVariable Integer id) {
        return RespDTO.successIf(noteService.deleteById(id));
    }
}
