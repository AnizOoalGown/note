package com.nazarick.note.service.impl;

import com.nazarick.note.domain.bo.NoteBO;
import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.mapper.NoteMapper;
import com.nazarick.note.service.ImageService;
import com.nazarick.note.service.NoteService;
import com.nazarick.note.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private ImageService imageService;

    @Override
    public NoteBO getById(Integer id) {
        Note note = noteMapper.findById(id);
        return note == null ? null : new NoteBO(note, imageService.getListByNoteId(id));
    }

    @Override
    public NoteBO create(Note note) {
        note.setId(IdUtil.genNoteId(note.getUserId()));
        int rows = noteMapper.insert(note);
        return rows == 1 ? new NoteBO(note, new ArrayList<>()) : null;
    }

    @Override
    public boolean update(Note note) {
        return noteMapper.update(note) == 1;
    }

    @Override
    public boolean deleteById(Integer id) {
        return imageService.deleteByNoteId(id) && noteMapper.deleteById(id) == 1;
    }
}
