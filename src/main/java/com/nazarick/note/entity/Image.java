package com.nazarick.note.entity;

import lombok.Data;

@Data
public class Image {
    Integer userId;
    Integer noteId;
    Integer no;
    byte[] data;
}
