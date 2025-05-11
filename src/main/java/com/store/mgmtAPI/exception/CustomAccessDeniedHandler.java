package com.store.mgmtAPI.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

enum ErrorCode {FORBIDDEN, UNAUTHORIZED, NOT_FOUND}
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(String.format("""
                {
                    "apiPath": "uri=%s",
                    "errorCode": "%s",
                    "errorMessage": "Access Denied: You do not have permission to perform this action."
                }
                """, request.getRequestURI(), ErrorCode.FORBIDDEN.name()));
    }
}
