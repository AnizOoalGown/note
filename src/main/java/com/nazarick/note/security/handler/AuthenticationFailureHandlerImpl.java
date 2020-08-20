package com.nazarick.note.security.handler;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.util.ServletUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
        String msg = null;
        if (e instanceof BadCredentialsException) {
            msg = "密码错误";
        }
        else if (e instanceof UsernameNotFoundException) {
            msg = e.getMessage();
        }
        ServletUtil.write(httpServletResponse, RespDTO.failure(HttpStatus.BAD_REQUEST.value(), msg));
    }
}
