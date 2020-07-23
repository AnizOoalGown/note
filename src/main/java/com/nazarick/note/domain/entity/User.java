package com.nazarick.note.domain.entity;

import lombok.Data;

@Data
public class User {
    Integer id;
    String username;
    String password;
    String menuTree;
    Integer lastViewNoteId;
}