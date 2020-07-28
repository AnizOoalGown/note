package com.nazarick.note.exception;

public class CustomException extends RuntimeException {
    private final int code;
    private final String message;

    public CustomException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
