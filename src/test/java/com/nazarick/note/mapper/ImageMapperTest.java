package com.nazarick.note.mapper;

import com.nazarick.note.domain.entity.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageMapperTest {
    @Autowired
    private ImageMapper imageMapper;

    @Test
    void testFind() {
    }
}