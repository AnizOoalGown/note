package com.nazarick.note.service;

import com.nazarick.note.domain.bo.ImageBO;
import com.nazarick.note.domain.entity.Image;

import java.util.List;

/**
 * 图片 业务层
 */
public interface ImageService {
    /**
     * 获取id为noteId的笔记的所有图片
     * @param noteId 笔记id
     * @return 图片列表
     */
    List<ImageBO> getListByNoteId(Integer noteId);

    /**
     * 新上传图片
     * @param noteId 笔记id
     * @param data 图片数据
     * @return 成功则返回含序号的图片对象，不成功则返回空值
     */
    Image create(Integer noteId, byte[] data);

    /**
     * 删除图片
     * @param noteId 笔记id
     * @param no 图片序号
     * @return 是否删除成功
     */
    boolean delete(Integer noteId, Integer no);

    /**
     * 删除id为noteId的笔记的所有图片
     * @param noteId 笔记id
     */
    void deleteByNoteId(Integer noteId);

    void deleteByUserId(Integer userId);
}
