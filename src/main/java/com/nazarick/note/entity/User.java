package com.nazarick.note.entity;

import lombok.Data;

@Data
public class User {
    Integer id;
    String username;
    String password;
    String menuTree;
    Integer lastViewNoteId;
}
