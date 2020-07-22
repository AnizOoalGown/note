package com.nazarick.note.entity;

import lombok.Data;

@Data
public class Note {
    Integer id;
    Integer userId;
    String content;
}
