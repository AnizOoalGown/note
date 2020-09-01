package com.nazarick.note.security.handler;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.service.APILogService;
import com.nazarick.note.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Autowired
    private APILogService apiLogService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        String msg;
        if (e instanceof BadCredentialsException) {
            msg = "密码错误";
        }
        else {
            msg = e.getMessage();
        }
        RespDTO<?> respDTO = RespDTO.failure(HttpStatus.BAD_REQUEST.value(), msg);
        ServletUtil.write(response, respDTO);
        apiLogService.asyncSaveAPILog(request, null, respDTO);
    }
}
