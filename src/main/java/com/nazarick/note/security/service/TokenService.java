package com.nazarick.note.security.service;

import com.nazarick.note.domain.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    String setToken(User user);
    User getUser(HttpServletRequest request);
    boolean checkToken(HttpServletRequest request);
    void deleteToken(HttpServletRequest request);
    void deleteUser(HttpServletRequest request);
}
