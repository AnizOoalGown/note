package com.nazarick.note.security.handler;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.User;
import com.nazarick.note.domain.vo.UserVO;
import com.nazarick.note.security.service.TokenService;
import com.nazarick.note.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) {
        Object curUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVO userVO = new UserVO((User) curUser);
        tokenService.setToken(userVO);
        ServletUtil.write(httpServletResponse, RespDTO.success(userVO));
    }
}
