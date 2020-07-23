package com.nazarick.note.mapper;

import com.nazarick.note.domain.entity.Image;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageMapper {
    List<Image> find(Image image);
    int insert(Image image);
    int delete(Image image);
}
