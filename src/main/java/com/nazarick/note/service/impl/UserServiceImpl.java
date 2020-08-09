package com.nazarick.note.service.impl;

import com.nazarick.note.domain.entity.User;
import com.nazarick.note.domain.vo.UserVO;
import com.nazarick.note.exception.CustomException;
import com.nazarick.note.mapper.UserMapper;
import com.nazarick.note.security.service.TokenService;
import com.nazarick.note.service.ImageService;
import com.nazarick.note.service.NoteService;
import com.nazarick.note.service.UserService;
import com.nazarick.note.util.IdUtil;
import com.nazarick.note.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NoteService noteService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserVO getById(Integer id) {
        User user = userMapper.findById(id);
        return user == null ? null : new UserVO(user);
    }

    @Override
    public UserVO getByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user == null ? null : new UserVO(user);
    }

    @Override
    public void create(User user) {
        user.setId(IdUtil.genUserId());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Override
    public void updateUsername(Integer id, String username) {
        User user = new User(id, username, null);
        userMapper.update(user);
    }

    @Override
    public void updatePassword(Integer id, String password, String newPassword) {
        if (!new BCryptPasswordEncoder().matches(password, userMapper.findById(id).getPassword())) {
            throw new CustomException(HttpStatus.BAD_REQUEST.value(), "原密码错误");
        }
        User user = new User(id, null, newPassword);
        userMapper.update(user);
    }

    @Override
    public void deleteById(Integer id) {
        // todo: delete namespace
        tokenService.deleteToken(ServletUtil.getRequest());
        imageService.deleteByUserId(id);
        noteService.deleteByUserId(id);
        userMapper.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + s + "' not found");
        }
        return user;
    }
}
