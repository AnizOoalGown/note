package com.nazarick.note.domain.bo;

import com.nazarick.note.domain.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NoteBO {
    Integer id;
    Integer userId;
    String content;
    List<ImageBO> images;

    public NoteBO(Note note, List<ImageBO> images) {
        this.id = note.getId();
        this.userId = note.getUserId();
        this.content = note.getContent();
        this.images = images;
    }
}
