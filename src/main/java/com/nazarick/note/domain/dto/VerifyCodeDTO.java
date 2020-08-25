package com.nazarick.note.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyCodeDTO {
    String uuid;
    String image;
}
