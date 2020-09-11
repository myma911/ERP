package com.haoyongsys.erp.api.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.haoyongsys.erp.common.pojo.R2;
import com.haoyongsys.erp.common.pojo.StateCodeEnum;
import com.haoyongsys.erp.common.pojo.exception.AException;


/**
 * 异常处理器
 *
 */
@RestControllerAdvice
public class AExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(AException.class)
	public R2<?> handleAException(AException e){
		logger.error(e.getMsg(), e);
		return R2.OK(e.getCode(), e.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public R2<?> handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R2.OK(StateCodeEnum.ERROR.getCode(), e.getMessage());
	}
	
}
