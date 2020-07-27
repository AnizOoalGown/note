package com.nazarick.note;

import com.nazarick.note.domain.entity.Note;
import com.nazarick.note.domain.vo.MenuNode;
import com.nazarick.note.mapper.NoteMapper;
import com.nazarick.note.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NoteApplicationTests {

    @Autowired
    private NoteService noteService;

    @Test
    void contextLoads() {
    }

    @Test
    void test() {
        System.out.println(noteService.getMenuTreeByUserId(1));
    }
}
