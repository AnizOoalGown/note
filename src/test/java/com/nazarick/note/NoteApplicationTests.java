package com.nazarick.note;

import com.nazarick.note.redis.LoginUserRepository;
import com.nazarick.note.security.domain.LoginUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteApplicationTests {

    @Autowired
    private LoginUserRepository loginUserRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername("Test");
        loginUser.setId(1);
        loginUser.setToken("token");
        loginUserRepository.save(loginUser);
    }
}
