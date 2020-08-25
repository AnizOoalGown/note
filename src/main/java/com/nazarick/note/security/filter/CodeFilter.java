package com.nazarick.note.security.filter;

import com.nazarick.note.exception.InviteCodeException;
import com.nazarick.note.exception.VerifyCodeException;
import com.nazarick.note.security.service.InviteCodeService;
import com.nazarick.note.security.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CodeFilter extends OncePerRequestFilter {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private InviteCodeService inviteCodeService;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if ((uri.equals("/login") || uri.equals("/users"))
                && request.getMethod().equalsIgnoreCase("post")) {
            if (!verifyCodeService.validateVerifyCode(request.getParameter("uuid"),
                    request.getParameter("verify_code"))) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new VerifyCodeException());
                return;
            }
            if (uri.equals("/users") && !inviteCodeService.delete(request.getParameter("invite_code"))) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, new InviteCodeException());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
