package com.nazarick.note.service;

import com.nazarick.note.entity.Image;

public interface ImageService {
    Image getById(Integer id);
    Image create(Image image);
    Image update(Image image);
    boolean deleteById(Integer id);
}
