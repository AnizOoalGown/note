package com.nazarick.note.mapper;

import com.nazarick.note.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findById(Integer id);
    User findByUsername(String username);
    int insert(User user);
    int update(User user);
    int deleteById(Integer id);
}
