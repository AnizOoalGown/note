package com.nazarick.note.security.service.impl;

import com.nazarick.note.domain.entity.User;
import com.nazarick.note.security.service.TokenService;

import javax.servlet.http.HttpServletRequest;

public class TokenServiceImpl implements TokenService {

    @Override
    public String setToken(User user) {
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        return false;
    }

    @Override
    public void deleteToken(HttpServletRequest request) {

    }
}
