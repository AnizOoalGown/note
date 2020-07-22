package com.nazarick.note.service;

import com.nazarick.note.entity.Note;

public interface NoteService {
    Note getById(Integer id);
    Note create(Note note);
    boolean update(Note note);
    boolean deleteById(Integer id);
}
