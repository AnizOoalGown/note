package com.nazarick.note.service.impl;

import com.nazarick.note.domain.entity.User;
import com.nazarick.note.exception.CustomException;
import com.nazarick.note.mapper.UserMapper;
import com.nazarick.note.service.UserService;
import com.nazarick.note.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User create(User user) {
        user.setId(IdUtil.genUserId());
        userMapper.insert(user);
        return user;
    }

    @Override
    public boolean updateUsername(Integer id, String username) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return userMapper.update(user) == 1;
    }

    @Override
    public boolean updatePassword(Integer id, String password, String newPassword, String checkPassword) {
        // todo: check password
        if (!newPassword.equals(checkPassword)) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "密码与确认密码不一致");
        }
        User user = new User();
        user.setId(id);
        user.setPassword(newPassword);
        return userMapper.update(user) == 1;
    }

    @Override
    public boolean deleteById(Integer id) {
        return userMapper.deleteById(id) == 1;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = getByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + s + "' not found");
        }
        return user;
    }
}
