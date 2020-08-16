package com.nazarick.note.util;

import com.nazarick.note.constants.Constants;

import java.util.Date;
import java.util.UUID;

public class IdUtil {
    public static int genNoteId(int userId) {
        int noteId;
        do {
            noteId = (int)((userId % 1e3) * 1e5) + (int)(new Date().getTime() % 1e5);
        }
        while (noteId == Constants.PARENT_ID);
        return noteId;
    }

    public static int genImageNo() {
        return (int)(new Date().getTime() % 1e6);
    }

    public static int genUserId() {
        return (int)(new Date().getTime() % 1e6);
    }

    public static String genUUID() {
        return UUID.randomUUID().toString();
    }
}
