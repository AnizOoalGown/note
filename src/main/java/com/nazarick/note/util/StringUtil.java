package com.nazarick.note.util;

import org.springframework.lang.Nullable;

public class StringUtil {
    public static boolean isEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(@Nullable Object str) {
        return !isEmpty(str);
    }
}
