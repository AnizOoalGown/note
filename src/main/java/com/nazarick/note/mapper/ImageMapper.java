package com.nazarick.note.mapper;

import com.nazarick.note.entity.Image;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageMapper {
    Image find(Image image);
    int insert(Image image);
    int update(Image image);
    int delete(Image image);
}
