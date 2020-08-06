package com.nazarick.note.security.service;

import com.nazarick.note.domain.entity.User;
import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    String setToken(User user);
    boolean checkToken(HttpServletRequest request);
    void deleteToken(HttpServletRequest request);
}
