package com.nazarick.note.service;

import com.nazarick.note.domain.bo.NoteBO;
import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.domain.vo.MenuNode;

import java.util.List;

/**
 * 笔记 业务层
 */
public interface NoteService {
    /**
     * 根据笔记id获取笔记
     * @param id 笔记id
     * @return 成功则返回笔记及其图片，不成功则返回空值
     */
    NoteBO getById(Integer id);

    /**
     * 创建笔记
     * @param note 笔记
     * @return 成功则返回新建笔记的id，不成功则返回空值
     */
    Integer create(Note note);

    /**
     * 更新笔记
     * @param note 笔记
     */
    void update(Note note);

    /**
     * 批量更新目录
     * @param notes 目录列表
     */
    void updateBatch(List<Note> notes);

    /**
     * 删除笔记
     * @param id 笔记id
     */
    void deleteById(Integer id);

    /**
     * 删除某用户所有笔记
     * @param id 用户id
     */
    void deleteByUserId(Integer id);

    /**
     * 根据用户id获取目录树
     * @param userId 用户id
     * @return 目录树
     */
    List<MenuNode> getMenuTreeByUserId(Integer userId);
}
