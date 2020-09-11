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
public class R3<T> extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R3() {
		put("code", 0);
		put("state", StateCodeEnum.OK.getCode());
		put("msg", StateCodeEnum.OK.getMsg());
	}

	public R3(Integer state, String message) {
		this(0, state, message);
	}

	public R3(StateCodeEnum stateCodeEnum) {
		this(0, stateCodeEnum.getCode(), stateCodeEnum.getMsg());
	}

	public R3(Integer code, Integer state, String message) {
		this(code, state, message, null);
	}

	public R3(Integer state, String message, T data) {
		this(0, state, message, data);
	}

	public R3(Integer code, Integer state, String message, T data) {
		put("code", code);
		put("state", state);
		put("msg", message);
		put("data", data);
	}

	public static R3<Object> OK() {
		return new R3<>();
	}

	public static <T> R3<Object> OK(T t) {
		return R3.OK().put("data", t);
	}

	public static <T> R3<Object> OK(String msg, T t) {
		return R3.OK().put("msg", msg).put("data", t);
	}

	public static R3<Object> Error() {
		return new R3<>().put("state", StateCodeEnum.ERROR.getCode()).put("msg", "失败");
	}
	
	public static <T> R3<Object> Error(String msg) {
		return R3.Error().put("msg", msg);
	}
	
	public static <T> R3<Object> Error(String msg, T t) {
		return R3.Error().put("msg", msg).put("data", t);
	}

	public static <T> R3<Object> Error(T t) {
		return R3.Error().put("data", t);
	}

	@Override
	public R3<T> put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
