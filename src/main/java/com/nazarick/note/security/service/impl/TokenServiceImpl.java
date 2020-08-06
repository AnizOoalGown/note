package com.nazarick.note.security.service.impl;

import com.nazarick.note.domain.entity.User;
import com.nazarick.note.security.service.TokenService;
import com.nazarick.note.util.IdUtil;
import com.nazarick.note.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private int expireTime;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String setToken(User user) {
        String token = IdUtil.genToken();
        redisUtil.setIfAbsent(token, user, expireTime, TimeUnit.DAYS);
        return token;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        return !StringUtils.isEmpty(token) && redisUtil.exists(token);
    }

    @Override
    public void deleteToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        redisUtil.delete(token);
    }
}
