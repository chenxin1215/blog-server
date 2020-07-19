package com.cx.blog.server.utils.exception;

import java.text.MessageFormat;

public class BizRtException extends RuntimeException {


    private static final long serialVersionUID = -4729161738021046262L;

    /**
     * 异常代码
     */
    private String code;

    /**
     * 本地化异常信息
     */
    private String localizedMessage;


    public BizRtException(String code) {
        this.code = code;
    }

    public BizRtException(String code, String msg) {
        super(msg);
        this.code = code;
        this.localizedMessage = msg;
    }
    
    public BizRtException(String code, Throwable t) {
        super(t);
        this.code = code;
    }

    public BizRtException(String code, String msg, Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = code;
        this.localizedMessage = msg;
    }

    public BizRtException(ExceptionCodes exceptionCodes) {
        super(exceptionCodes.getMsg());
        this.code = exceptionCodes.getCode();
        this.localizedMessage = exceptionCodes.getMsg();
    }

    public BizRtException(ExceptionCodes exceptionCodes, Throwable cause) {
        super(cause.getMessage(), cause);
        this.code = exceptionCodes.getCode();
        this.localizedMessage = exceptionCodes.getMsg();
    }

    public BizRtException(ExceptionCodes exceptionCodes, Object... replacements) {
        super(MessageFormat.format(exceptionCodes.getMsg(), replacements));
        this.code = exceptionCodes.getCode();
        this.localizedMessage = MessageFormat.format(exceptionCodes.getMsg(), replacements);
    }

    public BizRtException(ExceptionCodes exceptionCodes,Throwable cause, Object... replacements) {
        super(cause.getMessage(), cause);
        this.code = exceptionCodes.getCode();
        this.localizedMessage =MessageFormat.format(exceptionCodes.getMsg(), replacements);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return localizedMessage;
    }

    @Override
    public String toString() {
        String s = getClass().getName() + ", code=" + code;
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message) : s;
    }
}
