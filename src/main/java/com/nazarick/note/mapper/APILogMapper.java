package com.nazarick.note.mapper;

import com.nazarick.note.domain.entity.APILog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface APILogMapper {
    void insert(APILog apiLog);
}
