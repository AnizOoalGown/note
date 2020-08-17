package com.nazarick.note.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException{

    public NotFoundException(String str) {
        super(HttpStatus.NOT_FOUND.value(), "未找到资源：" + str);
    }
}
