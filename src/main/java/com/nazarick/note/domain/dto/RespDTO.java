package com.nazarick.note.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class RespDTO<T> {
    int code;
    boolean success;
    String msg;
    T data;

    public static <T> RespDTO<T> build(int code, boolean success, String msg, T data) {
        return new RespDTO<>(code, success, msg, data);
    }

    public static <T> RespDTO<T> success(T data) {
        return build(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> RespDTO<T> success() {
        return success(null);
    }

    public static <T> RespDTO<T> error() {
        return build(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }

    public static <T> RespDTO<T> successIf(boolean statement) {
        return statement ? success() : error();
    }

    public static <T> RespDTO<T> successIf(boolean statement, T data) {
        return statement ? success(data) : error();
    }

    public static <T> RespDTO<T> successIfNotNull(T data) {
        return successIf(data != null, data);
    }
}
