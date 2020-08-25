package com.nazarick.note.exception;

import org.springframework.security.core.AuthenticationException;

public class InviteCodeException extends AuthenticationException {
    public InviteCodeException() {
        super("邀请码错误");
    }
}
