package com.nazarick.note.service.impl;

import com.nazarick.note.domain.bo.NoteBO;
import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.domain.vo.MenuNode;
import com.nazarick.note.mapper.NoteMapper;
import com.nazarick.note.service.ImageService;
import com.nazarick.note.service.NoteService;
import com.nazarick.note.util.AuthUtil;
import com.nazarick.note.util.IdUtil;
import com.nazarick.note.util.MenuTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return new NoteBO(authUtil.accessNote(id), imageService.getListByNoteId(id));
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
    public boolean update(Note note) {
        authUtil.accessNote(note.getId());
        return noteMapper.update(note) == 1;
    }

    @Override
    public List<MenuNode> updateBatch(List<Note> notes, Integer userId) {
        notes.forEach(note -> authUtil.accessNote(note.getId()));
        noteMapper.updateBatch(notes);
        return getMenuTreeByUserId(userId);
    }

    @Override
    public boolean deleteById(Integer id) {
        authUtil.accessNote(id);
        imageService.deleteByNoteId(id);
        return noteMapper.deleteById(id) == 1;
    }

    @Override
    public void deleteByUserId(Integer userId) {
        authUtil.accessUser(userId);
        noteMapper.deleteByUserId(userId);
    }

    @Override
    public List<MenuNode> getMenuTreeByUserId(Integer userId) {
        authUtil.accessUser(userId);
        return MenuTreeUtil.buildMenuTree(noteMapper.findNotesByUserId(userId));
    }
}
