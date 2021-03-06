package com.nazarick.note.security.handler;

import com.nazarick.note.domain.dto.LoginSuccessDTO;
import com.nazarick.note.domain.dto.RespDTO;
import com.nazarick.note.domain.entity.User;
import com.nazarick.note.domain.vo.UserVO;
import com.nazarick.note.security.service.TokenService;
import com.nazarick.note.service.APILogService;
import com.nazarick.note.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private APILogService apiLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserVO userVO = new UserVO(user);
        String token = tokenService.setToken(user);
        LoginSuccessDTO loginSuccessDTO = new LoginSuccessDTO(userVO, token);
        ServletUtil.write(response, RespDTO.success(loginSuccessDTO));
        loginSuccessDTO.setToken("******");
       apiLogService.asyncSaveAPILog(request, null, RespDTO.success(loginSuccessDTO));
    }
}
