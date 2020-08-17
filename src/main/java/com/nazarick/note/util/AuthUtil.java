package com.nazarick.note.util;

import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.exception.ForbiddenException;
import com.nazarick.note.exception.NotFoundException;
import com.nazarick.note.mapper.NoteMapper;
import com.nazarick.note.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 鉴权工具类
 */
@Component
public class AuthUtil {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private NoteMapper noteMapper;

    /**
     * 获取当前用户id
     * @return 当前用户id
     */
    public Integer getCurUserId() {
        return tokenService.getUser(ServletUtil.getRequest()).getId();
    }

    /**
     * 判断是否有权限访问用户
     * @param userId 用户id
     */
    public void accessUser(Integer userId) {
        Integer curUserId = getCurUserId();
        if (curUserId == null || !curUserId.equals(userId)) {
            throw new ForbiddenException();
        }
    }

    /**
     * 判断是否有权限访问笔记
     * @param noteId 笔记id
     * @return 笔记
     */
    public Note accessNote(Integer noteId) {
        Note note = noteMapper.findById(noteId);
        if (note == null) {
            throw new NotFoundException("note-" + noteId);
        }
        accessUser(note.getUserId());
        return note;
    }

    /**
     * 判断是否有权限访问笔记列表
     * @param notes 笔记列表
     */
    public void accessNotes(List<Note> notes) {
        Integer curUserId = getCurUserId();

        for (Note note1 : notes) {
            Note note = noteMapper.findById(note1.getId());
            if (note == null) {
                throw new NotFoundException("note-" + note1.getId());
            }
            if (!curUserId.equals(note.getUserId())) {
                throw new ForbiddenException();
            }
        }
    }
}
