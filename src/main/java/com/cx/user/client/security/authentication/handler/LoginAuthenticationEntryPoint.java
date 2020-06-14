package com.cx.user.client.security.authentication.handler;

import com.cx.user.client.dto.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class LoginAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
        logger.info("   ############################################################################");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        BaseResp<String> resp = new BaseResp<>();
        resp.fail("请先登录", "1000");// 未登录

        // out.write(JsonUtil.toJsonNode(resp).toString());
        out.flush();

        // logger.info(JsonUtil.toJsonNode(resp).toString());
        logger.info("   ############################################################################");
    }

}
