package com.nazarick.note.domain.bo;

import com.nazarick.note.domain.entity.Note;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NoteBO {
    Note note;
    List<ImageBO> images;
}
