package com.cx.user.client.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class AuthenticationFailureHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) {

        logger.info("鉴权失败:{}", accessDeniedException.getMessage());
    }

}
