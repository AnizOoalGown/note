package com.nazarick.note.aop;

import com.nazarick.note.aop.annotation.APILog;
import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.util.ServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class APILogAspect {
    @Pointcut("@annotation(com.nazarick.note.aop.annotation.APILog)")
    public void logAPIPointCut() {}

    @AfterReturning(pointcut = "logAPIPointCut()", returning = "respDTO")
    public void doAfterReturning(JoinPoint joinPoint, RespDTO<?> respDTO) {
        HttpServletRequest request = ServletUtil.getRequest();
        if (request == null) {
            return;
        }

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        APILog apiLog = method.getAnnotation(APILog.class);

        System.out.println("---");
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURI());
        if (apiLog.saveParams()) {
            System.out.println(Arrays.toString(joinPoint.getArgs()));
        }
        System.out.println(request.getRemoteAddr());
        System.out.println(respDTO);
        System.out.println("---");
    }
}
