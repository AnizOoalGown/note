package com.nazarick.note.mapper;

import com.nazarick.note.domain.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;

@Mapper
public interface NoteMapper {
    Note findById(Integer id);
    LinkedList<Note> findNotesByUserId(Integer userId);
    List<Note> findNotesByUserIdParentId(@Param("userId") Integer userId, @Param("parentId") Integer parentId);
    int insert(Note note);
    int update(Note note);
    void updateBatch(List<Note> notes);
    int deleteById(Integer id);
    void deleteByUserId(Integer userId);
}
