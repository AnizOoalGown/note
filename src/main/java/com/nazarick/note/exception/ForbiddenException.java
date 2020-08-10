package com.nazarick.note.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends CustomException {

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN.value(), "无权限访问或操作资源");
    }
}
