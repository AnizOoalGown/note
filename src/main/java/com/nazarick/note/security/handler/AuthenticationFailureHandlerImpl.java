package com.nazarick.note.security.handler;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.util.ServletUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
        ServletUtil.write(httpServletResponse, RespDTO.failure(HttpStatus.UNAUTHORIZED.value(), e.getMessage()));
    }
}
