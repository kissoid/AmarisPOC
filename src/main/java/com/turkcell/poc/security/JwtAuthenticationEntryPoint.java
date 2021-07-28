package com.turkcell.poc.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.poc.util.StringUtil;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException ex) throws IOException, ServletException {
        logger.error("Responding with unauthorized error. Message - {}", ex.getMessage());
        logger.error(httpServletRequest.getRequestURI());

        if (isBrowserRequestWithoutAjax(httpServletRequest)) {
            if (isAjaxRequest(httpServletRequest)) {
                errorResponse(httpServletResponse, ex);
            } else {
                RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
            }
        } else {
            errorResponse(httpServletResponse, ex);
        }
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxHeader = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(ajaxHeader);
    }

    private boolean isBrowserRequestWithoutAjax(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        return !StringUtil.isBlankOrNull(userAgent);
    }

    private void errorResponse(HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setStatus(401);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message;
        if (exception.getCause() != null) {
            message = exception.getCause().getMessage();
        } else {
            message = exception.getMessage();
        }
        byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap(response.getStatus(), message));
        response.getOutputStream().write(body);
    }

}
