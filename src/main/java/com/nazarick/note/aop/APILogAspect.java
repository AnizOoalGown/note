package com.nazarick.note.aop;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.service.APILogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class APILogAspect {

    @Autowired
    private APILogService apiLogService;

    @Pointcut("@annotation(com.nazarick.note.aop.annotation.LogAPI)")
    public void logAPIPointCut() {}

    @AfterReturning(pointcut = "logAPIPointCut()", returning = "respDTO")
    public void doAfterReturning(JoinPoint joinPoint, RespDTO<?> respDTO) {
        apiLogService.asyncSaveAPILog(joinPoint, respDTO);
    }

    @AfterThrowing(pointcut = "logAPIPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        apiLogService.asyncSaveAPILog(joinPoint, e);
    }
}
