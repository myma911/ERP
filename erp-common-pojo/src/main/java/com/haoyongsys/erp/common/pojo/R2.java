package com.haoyongsys.erp.common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * PC 端使用的返回数据
 * 
 * code  前端规定code必须返回0
 * state 状态码 
 * msg	返回消息 
 * data 返回数据 
 * extra 附加信息
 * 
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class R2<T> {
	
	private Integer code = 0;
	
	private Integer state = 0;
	
	private String msg;
	
	private T data;
	
	private Object extra;
	
	
	public R2() {
		this.code = 0;
		this.state = 0;
	}
	
	
	public R2(StateCodeEnum stateCodeEnum) {
		this.state = stateCodeEnum.getCode();
		this.msg = stateCodeEnum.getMsg();
	}
	
	
	public R2(Integer code, Integer state, String msg, T data, Object extra) {
		this.code = code;
		this.state = state;
		this.msg = msg;
		this.data = data;
		this.extra = extra;
	}
	
	
	public static <T> R2<T> OK() {
		return new R2<T>();
	}
	
	public static <T> R2<T> OK(Integer state, String msg) {
		R2<T> ok = R2.OK();
		ok.setState(state);
		ok.setMsg(msg);
		return ok;
	}
	
	public static <T> R2<T> OK(T t) {
		R2<T> ok = R2.OK();
		ok.setData(t);
		return ok;
	}

	public static <T> R2<T> OK(String msg, T t) {
		R2<T> ok = R2.OK();
		ok.setMsg(msg);
		ok.setData(t);
		return ok;
	}
	
	public static <T> R2<T> OK(T t, Object e) {
		R2<T> ok = R2.OK();
		ok.setData(t);
		ok.setExtra(e);
		return ok;
	}


	
	/**
	 * Error 的 code 也是 0；
	 * 通过state状态码来标识错误
	 * @Title: Error
	 * @author Aaron
	 * @Date 2020年9月1日 上午10:55:24
	 * @return R2<Object>
	 */
	public static <T> R2<T> Error() {
		R2<T> r2 = new R2<T>();
		r2.setState(StateCodeEnum.ERROR.getCode());
		r2.setMsg(StateCodeEnum.ERROR.getMsg());
		return r2;
	}
	
	public static <T> R2<T> Error(String msg) {
		R2<T> error = R2.Error();
		error.setMsg(msg);
		return error;
	}
	
	public static <T> R2<T> Error(String msg, T t) {
		R2<T> error = R2.Error();
		error.setMsg(msg);
		error.setData(t);
		return error;
	}
}
