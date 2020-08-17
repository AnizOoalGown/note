package com.nazarick.note.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    Integer id;
    Integer userId;
    String type;
    String name;
    Integer parentId;
    Integer orderNo;
    String content;
}
