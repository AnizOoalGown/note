package com.nazarick.note.security.handler;

import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.security.service.TokenService;
import com.nazarick.note.service.APILogService;
import com.nazarick.note.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private APILogService apiLogService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) {
        RespDTO<?> respDTO = RespDTO.success();
        apiLogService.asyncSaveAPILog(request, null, respDTO);
        tokenService.deleteToken(request);
        ServletUtil.write(response, respDTO);
    }
}
