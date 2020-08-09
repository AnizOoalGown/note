package com.nazarick.note.security.service.impl;

import com.nazarick.note.domain.entity.User;
import com.nazarick.note.security.service.TokenService;
import com.nazarick.note.util.IdUtil;
import com.nazarick.note.util.RedisUtil;
import com.nazarick.note.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {

    private static final String TOKEN_NAMESPACE = "login_user:";

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
        // todo: namespace
        String key = IdUtil.genUUID();
        String token = Jwts.builder()
                .setId(key).setSubject(user.getUsername()).signWith(SignatureAlgorithm.HS512, secret).compact();
        redisUtil.setIfAbsent(addNamespacePrefix(key), user, expireTime, TimeUnit.DAYS);
        return token;
    }

    @Override
    public User getUser(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtil.isEmpty(token)) {
            return null;
        }
        return redisUtil.get(parseKey(token), User.class);
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        return StringUtil.isNotEmpty(token) && redisUtil.exists(parseKey(token));
    }

    @Override
    public void deleteToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtil.isEmpty(token)) {
            return;
        }
        redisUtil.delete(parseKey(token));
    }

    private String addNamespacePrefix(String key) {
        return TOKEN_NAMESPACE + key;
    }

    private String parseKey(String token) {
        token = token.replaceFirst("Bearer", "");
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String key = claims.getId();
        return addNamespacePrefix(key);
    }
}
