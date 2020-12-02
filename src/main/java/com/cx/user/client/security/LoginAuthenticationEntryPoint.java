package com.cx.user.client.security;

import com.cx.blog.server.dto.response.common.SimpleView;
import com.cx.utils.util.PrintWriterUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) {

        System.out.println("请求被拦截");
        SimpleView view = new SimpleView();
        view.setRspCode("1000");
        view.setRspMsg("请重新登录系统");

        PrintWriterUtils.flush(response, view);
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
