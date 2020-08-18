package com.haoyongsys.erp.common.pojo.exception;

import com.haoyongsys.erp.common.pojo.StateCodeEnum;

/**
 * 自定义异常
 *
 */
public class AException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public AException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public AException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public AException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public AException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
	public AException(StateCodeEnum stateCode) {
		this.code = stateCode.getCode();
		this.msg = stateCode.getMsg();
	}
	
	
}
