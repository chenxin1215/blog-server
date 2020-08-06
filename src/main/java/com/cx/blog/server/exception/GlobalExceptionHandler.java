package com.cx.blog.server.exception;

import com.cx.blog.server.dto.response.StringView;
import com.cx.utils.exception.BizException;
import com.cx.utils.exception.BizRtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * 
 * @author ruoyi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public StringView handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);

        StringView view = new StringView();
        view.fail("不支持' " + e.getMethod() + "'请求");
        return view;
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public StringView notFount(RuntimeException e) {
        log.error("运行时异常:", e);

        StringView view = new StringView();
        view.fail("运行时异常:" + e.getMessage());
        return view;
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public StringView handleException(Exception e) {
        log.error(e.getMessage(), e);

        StringView view = new StringView();
        view.fail("服务器错误，请联系管理员");
        return view;
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public StringView businessException(HttpServletRequest request, BizException e) {
        log.error(e.getMessage(), e);

        StringView view = new StringView();
        view.fail(e.getMessage());
        return view;
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BizRtException.class)
    public StringView businessException(HttpServletRequest request, BizRtException e) {
        log.error(e.getMessage(), e);

        StringView view = new StringView();
        view.fail(e.getMessage());
        return view;
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public StringView validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();

        StringView view = new StringView();
        view.fail(message);
        return view;
    }
}
