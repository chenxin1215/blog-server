package com.cx.user.client.security;

import com.cx.blog.server.dto.response.StringView;
import com.cx.blog.server.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class LogoutHandler implements LogoutSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(LogoutHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {

        logger.info("登出成功");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        StringView view = new StringView();
        view.success("登出成功");

        out.write(JsonUtil.stringify(view));
        out.flush();
    }
}
