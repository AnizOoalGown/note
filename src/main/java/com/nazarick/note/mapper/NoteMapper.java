package com.nazarick.note.mapper;

import com.nazarick.note.domain.entity.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedList;

@Mapper
public interface NoteMapper {
    Note findById(Integer id);
    LinkedList<Note> findNotesByUserId(Integer userId);
    int insert(Note note);
    int update(Note note);
    int deleteById(Integer id);
}
