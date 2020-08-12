package com.nazarick.note.service;

import com.nazarick.note.domain.entity.User;
import com.nazarick.note.domain.vo.UserVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    /**
     * 根据id获取用户展示
     * @param id 用户id
     * @return 用户展示
     */
    UserVO getById(Integer id);

    /**
     * 创建用户
     * @param user 用户
     */
    void create(User user);

    /**
     * 更新用户名
     * @param id 用户id
     * @param username 用户名
     */
    void updateUsername(Integer id, String username);

    /**
     * 更新密码
     * @param id 用户id
     * @param password 原密码
     * @param newPassword 新密码
     */
    void updatePassword(Integer id, String password, String newPassword);

    /**
     * 删除用户
     * @param id 用户id
     */
    void deleteById(Integer id);
}
