package com.nazarick.note.security.service.impl;

import com.nazarick.note.security.domain.LoginUser;
import com.nazarick.note.security.service.TokenService;

import javax.servlet.http.HttpServletRequest;

public class TokenServiceImpl implements TokenService {
    @Override
    public String setToken(LoginUser loginUser) {
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
