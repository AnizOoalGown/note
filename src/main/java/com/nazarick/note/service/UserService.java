package com.nazarick.note.service;

import com.nazarick.note.domain.entity.User;
import com.nazarick.note.domain.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserVO getById(Integer id);
    UserVO getByUsername(String username);
    void create(User user);
    void updateUsername(Integer id, String username);
    void updatePassword(Integer id, String password, String newPassword);
    void deleteById(Integer id);
}
