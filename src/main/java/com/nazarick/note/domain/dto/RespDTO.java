package com.nazarick.note.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class RespDTO {
    int code;
    boolean success;
    String msg;
    Object data;

    public static RespDTO build(int code, boolean success, String msg, Object data) {
        return new RespDTO(code, success, msg, data);
    }

    public static RespDTO success(Object data) {
        return build(HttpStatus.OK.value(), true, HttpStatus.OK.getReasonPhrase(), data);
    }

    public static RespDTO success() {
        return success(null);
    }

    public static RespDTO error() {
        return build(HttpStatus.INTERNAL_SERVER_ERROR.value(), false,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }

    public static RespDTO successIf(boolean statement) {
        return statement ? success() : error();
    }

    public static RespDTO successIf(boolean statement, Object data) {
        return statement ? success(data) : error();
    }

    public static RespDTO successIfNotNull(Object data) {
        return successIf(data != null, data);
    }
}
