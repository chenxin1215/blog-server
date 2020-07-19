package com.cx.blog.server.utils;

import com.cx.user.client.security.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class PrintWriterUtils {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);

    public static void flush(HttpServletResponse response, Object object) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        try (PrintWriter out = response.getWriter();) {
            out.write(JsonUtil.stringify(object));
            out.flush();
        } catch (Exception e) {
            logger.error("flush返回数据报错", e);
        }
    }
}
