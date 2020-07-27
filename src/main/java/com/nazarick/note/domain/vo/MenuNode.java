package com.nazarick.note.domain.vo;

import com.nazarick.note.domain.entity.Note;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuNode {
    Integer id;
    String type;
    String name;
    List<MenuNode> children;

    public MenuNode(Note note) {
        this.id = note.getId();
        this.type = note.getType();
        this.name = note.getName();
        this.children = new ArrayList<>();
    }
}
