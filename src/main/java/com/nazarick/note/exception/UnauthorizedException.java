package com.nazarick.note.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED.value(), "无权限访问或操作资源");
    }
}
