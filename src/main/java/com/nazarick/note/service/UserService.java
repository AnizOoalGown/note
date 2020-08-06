package com.nazarick.note.service;

import com.nazarick.note.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getById(Integer id);
    User getByUsername(String username);
    User create(User user);
    boolean updateUsername(Integer id, String username);
    boolean updatePassword(Integer id, String password, String newPassword, String checkPassword);
    boolean deleteById(Integer id);
}
