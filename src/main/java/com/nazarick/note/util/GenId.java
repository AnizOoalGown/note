package com.nazarick.note.util;

import java.util.Date;

public class GenId {
    public static int genNoteId(int userId) {
        return (int)((userId % 1e3) * 1e5) + (int)(new Date().getTime() % 1e5);
    }
}
