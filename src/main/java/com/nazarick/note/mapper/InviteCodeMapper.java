package com.nazarick.note.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InviteCodeMapper {
    int delete(String code);
}
