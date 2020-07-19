package com.cx.blog.server.utils.exception;

/**
 * 异常接口代码实现类
 * @author bealon
 *
 */
public interface ExceptionCodes {
	
	/**
	 * 成功, 对应前端会回调
	 */
	public static final String SUCCESS = "0000";

	/**
	 * 失败, 对应前端会提示错误
	 */
	public static final String NOTIFY = "0001";

	/**
	 * 失败通知, 不会提示错误
	 */
	public static final String SILENCE = "0002";

	/**
	 * 失败, 对应前端还是会进入回调
	 */
	public static final String FORCE = "0003";
	
   
    public String getCode();

    public String getMsg();
}
