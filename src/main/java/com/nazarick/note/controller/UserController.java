package com.nazarick.note.controller;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.User;
import com.nazarick.note.domain.vo.UserVO;
import com.nazarick.note.exception.CustomException;
import com.nazarick.note.service.UserService;
import com.nazarick.note.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    RespDTO<UserVO> getById(@PathVariable Integer id) {
        return RespDTO.success(userService.getById(id));
    }

    @GetMapping
    RespDTO<UserVO> getByUsername(@RequestParam String username) {
        return RespDTO.success(userService.getByUsername(username));
    }

    @PostMapping
    RespDTO<?> create(@RequestParam String username, @RequestParam String password) {
        userService.create(new User(null, username, password));
        return RespDTO.success();
    }

    @PutMapping("/{id}")
    RespDTO<?> updateUser(@PathVariable Integer id,
                          @RequestParam(required = false) String username,
                          @RequestParam(required = false) String password,
                          @RequestParam(required = false) String newPassword) {
        if (StringUtil.isNotEmpty(username)) {
            userService.updateUsername(id, username);
            return RespDTO.success();
        }
        if (StringUtil.isNotEmpty(password) && StringUtil.isNotEmpty(newPassword)) {
            userService.updatePassword(id, password, newPassword);
            return RespDTO.success();
        }
        throw new CustomException(HttpStatus.BAD_REQUEST.value(), "更新用户参数不正确");
    }

    @DeleteMapping("/{id}")
    RespDTO<?> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return RespDTO.success();
    }
}
