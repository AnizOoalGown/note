package com.nazarick.note.service.impl;

import com.nazarick.note.entity.Note;
import com.nazarick.note.mapper.NoteMapper;
import com.nazarick.note.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public Note getById(Integer id) {
        return noteMapper.findById(id);
    }

    @Override
    public Note create(Note note) {
        noteMapper.insert(note);
        return note;
    }

    @Override
    public boolean update(Note note) {
        return noteMapper.update(note) == 1;
    }

    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
