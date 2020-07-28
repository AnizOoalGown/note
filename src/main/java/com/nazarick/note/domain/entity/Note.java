package com.nazarick.note.domain.entity;

import lombok.Data;

@Data
public class Note {
    Integer id;
    Integer userId;
    String type;
    String name;
    Integer parentId;
    Integer orderNo;
    String content;
}
