package com.nazarick.note.util;

import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.exception.ForbiddenException;
import com.nazarick.note.mapper.NoteMapper;
import com.nazarick.note.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            throw new ForbiddenException();
        }
        accessUser(note.getUserId());
        return note;
    }
}
