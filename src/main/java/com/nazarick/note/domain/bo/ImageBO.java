package com.nazarick.note.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageBO {
    Integer no;
    byte[] data;
}
