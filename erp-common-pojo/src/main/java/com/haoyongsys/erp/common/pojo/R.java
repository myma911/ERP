package com.haoyongsys.erp.common.pojo;

import java.util.HashMap;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回数据
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class R<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 0);
		put("state", StateCodeEnum.OK.getCode());
		put("msg", StateCodeEnum.OK.getMsg());
	}

	public R(Integer state, String message) {
		this(0, state, message);
	}

	public R(StateCodeEnum stateCodeEnum) {
		this(0, stateCodeEnum.getCode(), stateCodeEnum.getMsg());
	}

	public R(Integer code, Integer state, String message) {
		this(code, state, message, null);
	}

	public R(Integer state, String message, T data) {
		this(0, state, message, data);
	}

	public R(Integer code, Integer state, String message, T data) {
		put("code", code);
		put("state", state);
		put("msg", message);
		put("data", data);
	}

	public static R<Object> OK() {
		return new R<>();
	}

	public static <T> R<Object> OK(T t) {
		return R.OK().put("data", t);
	}

	public static <T> R<Object> OK(String msg, T t) {
		return R.OK().put("msg", msg).put("data", t);
	}

	public static R<Object> Error() {
		return new R<>().put("state", StateCodeEnum.ERROR.getCode()).put("msg", "失败");
	}
	
	public static <T> R<Object> Error(String msg) {
		return R.Error().put("msg", msg);
	}
	
	public static <T> R<Object> Error(String msg, T t) {
		return R.Error().put("msg", msg).put("data", t);
	}

	public static <T> R<Object> Error(T t) {
		return R.Error().put("data", t);
	}

	@Override
	public R<T> put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
