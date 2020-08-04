package com.nazarick.note.security.service;

import com.nazarick.note.security.domain.LoginUser;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    String setToken(LoginUser loginUser);
    boolean checkToken(HttpServletRequest request);
    void deleteToken(HttpServletRequest request);
}
