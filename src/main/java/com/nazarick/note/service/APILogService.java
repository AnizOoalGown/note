package com.nazarick.note.service;

import com.nazarick.note.domain.dto.RespDTO;
import org.aspectj.lang.JoinPoint;

import javax.servlet.http.HttpServletRequest;

public interface APILogService {
    void asyncSaveAPILog(JoinPoint joinPoint, RespDTO<?> respDTO);
    void asyncSaveAPILog(JoinPoint joinPoint, Exception e);
    void asyncSaveAPILog(HttpServletRequest request, String args, RespDTO<?> respDTO);
}
