package com.nazarick.note.service.impl;

import com.alibaba.fastjson.JSON;
import com.nazarick.note.aop.annotation.LogAPI;
import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.APILog;
import com.nazarick.note.domain.entity.User;
import com.nazarick.note.exception.CustomException;
import com.nazarick.note.mapper.APILogMapper;
import com.nazarick.note.security.service.TokenService;
import com.nazarick.note.service.APILogService;
import com.nazarick.note.util.ServletUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Service
public class APILogServiceImpl implements APILogService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private APILogMapper apiLogMapper;

    @Override
    public void asyncSaveAPILog(JoinPoint joinPoint, RespDTO<?> respDTO) {
        HttpServletRequest request = ServletUtil.getRequest();
        if (request == null) {
            return;
        }

        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        LogAPI logAPI = method.getAnnotation(LogAPI.class);

        String args = null;
        if (logAPI.saveArgs()) {
            args = JSON.toJSONString(joinPoint.getArgs());
        }

        asyncSaveAPILog(request, args, respDTO);
    }

    @Override
    public void asyncSaveAPILog(JoinPoint joinPoint, Exception e) {
        RespDTO<?> respDTO;
        if (e instanceof CustomException) {
            respDTO = RespDTO.failure(((CustomException) e).getCode(), e.getMessage());
        }
        else {
            respDTO = RespDTO.error(e.getMessage());
        }
        asyncSaveAPILog(joinPoint, respDTO);
    }

    @Override
    public void asyncSaveAPILog(HttpServletRequest request, String args, RespDTO<?> respDTO) {
        User user = tokenService.getUser(request);
        String ip = request.getRemoteAddr();
        Object data = respDTO.getData();
        APILog apiLog = APILog.builder()
                .code(respDTO.getCode())
                .msg(respDTO.getMsg())
                .data(data == null ? null : JSON.toJSONString(data))
                .ip(ip == null ? "unknown" : ip)
                .method(request.getMethod())
                .uri(request.getRequestURI())
                .userId(user == null ? null : user.getId())
                .args(args)
                .build();
        new Thread(() -> apiLogMapper.insert(apiLog)).start();
    }
}
