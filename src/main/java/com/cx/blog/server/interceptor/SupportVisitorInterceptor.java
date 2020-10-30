package com.cx.blog.server.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class SupportVisitorInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(SupportVisitorInterceptor.class);

    private @Autowired SupportVisitorLoader supportVisitorLoader;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        String servletPath = request.getServletPath();
        LOGGER.info("当前操作servletPath:{}", servletPath);

        List<String> visitorUrls = supportVisitorLoader.getVisitorUrls();

        for (String url : visitorUrls) {
            // 游客模式无需报错
            if (servletPath.startsWith(url)) {
                return true;
            }
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
