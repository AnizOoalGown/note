package com.nazarick.note.service.impl;

import com.nazarick.note.domain.bo.ImageBO;
import com.nazarick.note.domain.entity.Image;
import com.nazarick.note.mapper.ImageMapper;
import com.nazarick.note.service.ImageService;
import com.nazarick.note.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public List<ImageBO> getListByNoteId(Integer noteId) {
        Image image = new Image(noteId, null, null);
        return imageMapper.find(image).stream().map(image1 ->
                new ImageBO(image1.getNo(), image1.getData())).collect(Collectors.toList());
    }

    @Override
    public Image create(Integer noteId, byte[] data) {
        Image image = new Image(noteId, IdUtil.genImageNo(), data);
        return imageMapper.insert(image) == 1 ? image : null;
    }

    @Override
    public boolean delete(Integer noteId, Integer no) {
        Image image = new Image(noteId, no, null);
        return imageMapper.delete(image) == 1;
    }

    @Override
    public boolean deleteByNoteId(Integer noteId) {
        Image image = new Image(noteId, null, null);
        return imageMapper.delete(image) > 0;
    }
}
