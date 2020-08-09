package com.nazarick.note;

import com.nazarick.note.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        redisUtil.deleteNamespace("login_user");
    }
}
