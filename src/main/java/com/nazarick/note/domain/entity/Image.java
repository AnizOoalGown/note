package com.nazarick.note.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Image {
    Integer noteId;
    Integer no;
    byte[] data;
}
