package com.nazarick.note;

import com.nazarick.note.domain.entity.User;
import com.nazarick.note.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class NoteApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        User user = new User();
        user.setId(124);
        user.setUsername("Bob");
        redisUtil.set("token4", user, 1, TimeUnit.MINUTES);
        user = redisUtil.get("token4", User.class);
        System.out.println(user);
    }
}
