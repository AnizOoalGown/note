package com.nazarick.note.domain.entity;

import lombok.Data;

@Data
public class Note {
    Integer id;
    Integer userId;
    String content;
}
