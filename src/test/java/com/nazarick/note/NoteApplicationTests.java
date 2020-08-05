package com.nazarick.note;

import com.alibaba.fastjson.JSONObject;
import com.nazarick.note.domain.entity.User;
import com.nazarick.note.redis.LoginUserRepository;
import com.nazarick.note.redis.RedisCache;
import com.nazarick.note.security.domain.LoginUser;
import com.nazarick.note.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class NoteApplicationTests {

    @Autowired
    private RedisUtil<LoginUser> redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        User user = new User();
        user.setId(124);
        user.setUsername("Bob");
        LoginUser loginUser = new LoginUser(user);
        loginUser.setToken("token4");
        redisUtil.set("token4", loginUser, 1, TimeUnit.MINUTES);
        System.out.println(redisUtil.get("token4"));
    }
}
