package com.cx.blog.server.utils.exception;

public enum BizExceptionEnum implements ExceptionCodes {

	USERNAME_NOT_FOUND("0001", "用户找不到"), 
	ACCOUNT_NOT_MATCH("0001", "用户名密码错误"), 
	LOCKED("0001", "账户锁定"),
	DISABLED("0001", "账户不可用"),
	CUSTOMER_COUPON_ERROR("ATOM.0001", "客户数大于优惠券总数"),
	CUSTOMER_COUPON__DATE_ERROR("ATOM.0002", "优惠券时间只能选择一样")
	;

	private String code;
	private String msg;

	BizExceptionEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
