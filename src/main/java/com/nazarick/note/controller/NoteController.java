package com.nazarick.note.controller;

import com.nazarick.note.domain.bo.NoteBO;
import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.domain.vo.MenuNode;
import com.nazarick.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/{id}")
    RespDTO<NoteBO> getById(@PathVariable Integer id) {
        return RespDTO.success(noteService.getById(id));
    }

    @PostMapping
    RespDTO<Integer> create(@RequestBody Note note) {
        return RespDTO.successIfNotNull(noteService.create(note));
    }

    @PutMapping
    RespDTO<?> update(@RequestBody Note note) {
        noteService.update(note);
        return RespDTO.success();
    }

    @PutMapping("/batch")
    RespDTO<?> updateBatch(@RequestBody List<Note> notes) {
        noteService.updateBatch(notes);
        return RespDTO.success();
    }

    @DeleteMapping("/{id}")
    RespDTO<?> deleteById(@PathVariable Integer id) {
        noteService.deleteById(id);
        return RespDTO.success();
    }

    @GetMapping("/menuTree")
    RespDTO<List<MenuNode>> getMenuTreeByUserId(@RequestParam Integer userId) {
        return RespDTO.success(noteService.getMenuTreeByUserId(userId));
    }
}
