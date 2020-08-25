package com.nazarick.note.exception;

import org.springframework.security.core.AuthenticationException;

public class VerifyCodeException extends AuthenticationException {
    public VerifyCodeException() {
        super("验证码错误或过期");
    }
}
