package com.nazarick.note.service.impl;

import com.nazarick.note.domain.bo.NoteBO;
import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.domain.vo.MenuNode;
import com.nazarick.note.exception.CustomException;
import com.nazarick.note.mapper.NoteMapper;
import com.nazarick.note.service.ImageService;
import com.nazarick.note.service.NoteService;
import com.nazarick.note.util.AuthUtil;
import com.nazarick.note.util.IdUtil;
import com.nazarick.note.util.MenuTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteMapper noteMapper;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AuthUtil authUtil;

    @Override
    public NoteBO getById(Integer id) {
        Note note = authUtil.accessNote(id);
        if ("folder".equals(note.getType())) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), id + "类型不为笔记");
        }
        return new NoteBO(note, imageService.getListByNoteId(id));
    }

    @Override
    public Integer create(Note note) {
        Integer curUserId = authUtil.getCurUserId();
        note.setUserId(curUserId);
        Integer id = IdUtil.genNoteId(curUserId);
        note.setId(id);
        if ("document".equals(note.getType())) {
            note.setContent("");
        }
        int rows = noteMapper.insert(note);
        return rows == 1 ? id : null;
    }

    @Override
    public void update(Note note) {
        authUtil.accessNote(note.getId());
        noteMapper.update(note);
    }

    @Override
    public void updateBatch(List<Note> notes) {
        authUtil.accessNotes(notes);
        noteMapper.updateBatch(notes);
    }

    @Override
    public void deleteById(Integer id) {
        Note deletedNote = authUtil.accessNote(id);
        imageService.deleteByNoteId(id);
        noteMapper.deleteById(id);
        List<Note> updateNotes = new ArrayList<>();
        List<Note> notes = noteMapper.findNotesByUserIdParentId(deletedNote.getUserId(), deletedNote.getParentId());
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (i != note.getOrderNo()) {
                updateNotes.add(Note.builder().id(note.getId()).orderNo(i).build());
            }
        }
        if (updateNotes.size() != 0) {
            noteMapper.updateBatch(updateNotes);
        }
    }

    @Override
    public void deleteByUserId(Integer userId) {
        noteMapper.deleteByUserId(userId);
    }

    @Override
    public List<MenuNode> getMenuTreeByUserId(Integer userId) {
        authUtil.accessUser(userId);
        return MenuTreeUtil.buildMenuTree(noteMapper.findNotesByUserId(userId));
    }
}
