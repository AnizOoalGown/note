package com.nazarick.note.mapper;

import com.nazarick.note.domain.entity.Note;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteMapper {
    Note findById(Integer id);
    int insert(Note note);
    int update(Note note);
    int deleteById(Integer id);
}
