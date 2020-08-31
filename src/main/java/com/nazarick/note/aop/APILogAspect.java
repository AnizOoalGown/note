package com.nazarick.note.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.nazarick.note.aop.annotation.LogAPI;
import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.APILog;
import com.nazarick.note.exception.CustomException;
import com.nazarick.note.mapper.APILogMapper;
import com.nazarick.note.util.AuthUtil;
import com.nazarick.note.util.ServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class APILogAspect {

    @Autowired
    private APILogMapper apiLogMapper;

    @Autowired
    private AuthUtil authUtil;

    @Pointcut("@annotation(com.nazarick.note.aop.annotation.LogAPI)")
    public void logAPIPointCut() {}

    @AfterReturning(pointcut = "logAPIPointCut()", returning = "respDTO")
    public void doAfterReturning(JoinPoint joinPoint, RespDTO<?> respDTO) {
        saveAPILog(joinPoint, respDTO.getCode(), respDTO.getMsg(),
                JSON.toJSONString(respDTO.getData()));
    }

    @AfterThrowing(pointcut = "logAPIPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        Integer code;
        if (e instanceof CustomException) {
            code = ((CustomException) e).getCode();
        }
        else {
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        saveAPILog(joinPoint, code, e.getMessage(), null);
    }

    private void saveAPILog(JoinPoint joinPoint, Integer code, String msg, String data) {
        HttpServletRequest request = ServletUtil.getRequest();
        if (request == null) {
            return;
        }

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LogAPI logAPI = method.getAnnotation(LogAPI.class);

        APILog apiLog = APILog.builder()
                .code(code)
                .msg(msg)
                .data(data)
                .ip(request.getRemoteAddr())
                .method(request.getMethod())
                .uri(request.getRequestURI())
                .userId(authUtil.getCurUserId())
                .build();

        if (logAPI.saveArgs()) {
            apiLog.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        }

        apiLogMapper.insert(apiLog);
    }
}
