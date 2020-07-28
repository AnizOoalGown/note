package com.nazarick.note.exception.handler;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public RespDTO<Object> handleCustomException(CustomException e) {
        log.error(e.getMessage(), e);
        return RespDTO.failure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public RespDTO<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return RespDTO.error(e.getMessage());
    }
}
