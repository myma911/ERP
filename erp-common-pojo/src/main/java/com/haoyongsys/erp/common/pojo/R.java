package com.haoyongsys.erp.common.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 移动端使用的返回数据
 * 
 * code 状态码 
 * msg  返回消息 
 * data 返回数据 
 * extra 附加信息
 * 
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class R {
	
	private Integer code = 0;
	
	private String msg;
	
	private Object data;
	
	private Object extra;
	
	
	public R() {
	
	}
	
	public R(Integer code, String msg, Object data, Object extra) {
		this.code = code;
		this.msg = msg;
		this.data = data;
		this.extra = extra;
	}
	
	
	public static R OK() {
		return new R();
	}
	
	
	public static R OK(Object t) {
		R ok = R.OK();
		ok.setData(t);
		return ok;
	}

	public static R OK(String msg, Object t) {
		R ok = R.OK();
		ok.setMsg(msg);
		ok.setData(t);
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
	public static R Error() {
		R r = new R();
		r.setCode(StateCodeEnum.ERROR.getCode());
		r.setMsg(StateCodeEnum.ERROR.getMsg());
		return r;
	}
	
	public static R Error(String msg) {
		R error = R.Error();
		error.setMsg(msg);
		return error;
	}
	
	public static R Error(String msg, Object t) {
		R error = R.Error();
		error.setMsg(msg);
		error.setData(t);
		return error;
	}
}
