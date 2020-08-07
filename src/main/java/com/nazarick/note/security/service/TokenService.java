package com.nazarick.note.security.service;

import com.nazarick.note.domain.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    String setToken(UserVO user);
    boolean checkToken(HttpServletRequest request);
    void deleteToken(HttpServletRequest request);
}
