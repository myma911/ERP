package com.haoyongsys.erp.common.pojo;

public enum StateCodeEnum {
	
	OK(0, "成功"),
	ERROR(20001 , "失败" ),
	LOGIN_ERROR( 20002, "用户名或密码错误"),
	ACCESS_ERROR( 20003,  "权限不足"),
	REMOTE_ERROR(20004 , "远程调用失败" ),
	REPEAT_ERROR( 20005, "重复操作" ),
	VERIFICATION_CODE_ERROR(20006 , "验证码错误" ),
	TOKEN_CANNOT_EMPTY(20007 ,  "token不能为空"),
	ExpiredJwtException(20008 ,  "token过期失效"),
	UNIQUE_CANNOT_EMPTY( 20009, "幂等性校验码不能为空" ),
	UNIQUE_INVALID( 20010, "幂等性校验码不存在或已过期" ),
	AuthorizationException( 20011, "权限" ),
	LIMIT_ACCESS( 20012, "被限制访问，一般是因为短时间访问次数过多" );
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private int code;
	
	private String msg;

	private StateCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
